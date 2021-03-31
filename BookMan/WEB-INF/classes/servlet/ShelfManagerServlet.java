package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserBookInfoDAO;
import dao.UserShelfDAO;
import model.Account;
import model.UserBookInfo;
import model.UserShelf;

/**
 * Servlet implementation class ShelfManagerServlet
 */
@WebServlet("/ShelfManagerServlet")
public class ShelfManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShelfManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserBookInfo> userBookList = null;

        // ログインチェック+情報取得
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = "/bookMan/ShelfMasterServlet";
        if (loginUser == null) {
            response.sendRedirect(url);
            return;
        }

        // 蔵書登録の検索結果削除
        session.removeAttribute("bookList");

        // ログインしている場合DB接続
        UserBookInfoDAO ubiDao = new UserBookInfoDAO();
        userBookList = ubiDao.searchUserBookInfoForUserId(loginUser.getUserId());

        url = "/WEB-INF/jsp/myShelf.jsp";
        request.setAttribute("userBookList", userBookList);
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserBookInfo> userBookList = null;
        request.setCharacterEncoding("UTF-8");
        int states = 0;

        // ログインチェック+情報取得
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = "/bookMan/ShelfMasterServlet";
        if (loginUser == null) {
            response.sendRedirect(url);
            return;
        }

        String modeStr = request.getParameter("mode");
        String key = null;
        if (!modeStr.matches("[0-9]+")) {
            doGet(request, response);
            return;
        }
        // DB接続
        UserBookInfoDAO ubiDao = new UserBookInfoDAO();
        UserShelfDAO usDao = new UserShelfDAO();

        switch (Integer.parseInt(modeStr)) {
        case 2:     // タイトル検索
            key = request.getParameter("key");
            if (key == null || key.length() <= 0) {
                key="";
            }
            userBookList = ubiDao.searchUserBookInfoForTitle(loginUser.getUserId(), key);
            // 状態検索
            states = 0;
            int[] statesArray = {0,0,0};
            key = request.getParameter("have");
//            System.out.println("have"+key);
            if(key != null) {
                statesArray[0] = Integer.parseInt(key);
            }
            key = request.getParameter("wish");
//            System.out.println("wish"+key);
            if(key != null) {
                statesArray[1] = Integer.parseInt(key);
            }
            key = request.getParameter("read");
//            System.out.println("read"+key);
            if(key != null) {
                statesArray[2] = Integer.parseInt(key);
            }
            List<UserBookInfo> searchedList = new ArrayList<>();
            for(UserBookInfo tmp:userBookList) {
                int i=0;
                for(; i < statesArray.length; i++) {
                    if((statesArray[i] != -1) &&
                            ((tmp.getStates()>>i)&1) != statesArray[i]) {
                        break;
                    }
                }
                if(i == statesArray.length) {
                    searchedList.add(tmp);
                }
            }
            //request.setAttribute("userBookList", userBookList);
            request.setAttribute("userBookList", searchedList);
            url = "/WEB-INF/jsp/myShelf.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            break;
        case 3:     // 詳細
            key = request.getParameter("isbn");
            if (key == null || key.length() <= 0) {
                doGet(request, response);
                break;
            }
            ubiDao = new UserBookInfoDAO();
            userBookList = ubiDao.searchUserBookInfoForIsbn(loginUser.getUserId(), key);
            session.setAttribute("userBookInfo", userBookList.get(0));
            url = "/WEB-INF/jsp/detailBookLogin.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            break;
        case 4:     // 編集確定
            UserBookInfo userBook = (UserBookInfo)session.getAttribute("userBookInfo");
            String isbn = userBook.getIsbn();
            UserShelf userShelf = usDao.searchUserShelfForUserIdAndIsbn(loginUser.getUserId(), userBook.getIsbn()).get(0);
            states = 0;
            key = request.getParameter("have");
            if(key != null) {
                states = 1;
            }
            key = request.getParameter("wish");
            if(key != null) {
                states |= 2;
            }
            key = request.getParameter("read");
            if(key != null) {
                states |= 4;
            }
            userShelf.setStates(states);
            key = request.getParameter("star");
            if(key == null) {
                key = "0";
            }
            userShelf.setReview(Integer.parseInt(key));
            key = request.getParameter("comment");
            if(key == null) {
                key = "";
            }
            userShelf.setComment(key);
            Date date = new Date();
            userShelf.setUpdateDate(date);
            //System.out.println(userShelf.getStates() + userShelf.getComment());
            usDao.updateUserShelf(userShelf);
//            userBookList = ubiDao.searchUserBookInfoForIsbn(loginUser.getUserId(), isbn);
//            session.setAttribute("userBookInfo", userBookList.get(0));
//            url = "/WEB-INF/jsp/detailBookLogin.jsp";
//            request.getRequestDispatcher(url).forward(request, response);
//            System.out.println(userBookList.get(0).getStates() + "state");
            doGet(request, response);

            break;
        default:
            doGet(request, response);
            break;

        }

    }

}
