package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookInfoDAO;
import dao.UserShelfDAO;
import model.Account;
import model.BookInfo;
import model.SearchBooksForApiLogic;
import model.UserShelf;

/**
 * Servlet implementation class SearchBookServlet
 */
@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = null;

        if (loginUser == null) {
            goTop(request, response);
            return;
        }

        // アプリ内の全書籍取得
        BookInfoDAO bookDao = new BookInfoDAO();
        session.setAttribute("bookList", bookDao.findAll());

        // 書籍追加画面へ
        url = "/WEB-INF/jsp/searchBook.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String modeStr = request.getParameter("mode");
        if (loginUser == null || !modeStr.matches("[0-9]+")) {
            goTop(request, response);
            return;
        }
        List<BookInfo> bookList = null;
        String url;
        String isbn, title;
        BookInfoDAO bookDao = new BookInfoDAO();
        UserShelfDAO userDao = new UserShelfDAO();
        UserShelf userShelf = new UserShelf();
        int states = 0;


        switch (Integer.parseInt(modeStr)) {
        case 1:
            // ネットで検索
            String tmp = request.getParameter("isbn");
            isbn = tmp==null?"":tmp;
            tmp = request.getParameter("title");
            title = tmp==null?"":tmp;
            SearchBooksForApiLogic api = new SearchBooksForApiLogic(isbn, title);
            bookList = api.execute();

            // データをセッションスコープへ
            session.setAttribute("bookList", bookList);
            url = "/WEB-INF/jsp/searchBook.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            break;
        case 2:
            // DB接続して追加
            bookList = (List<BookInfo>)session.getAttribute("bookList");
            isbn = request.getParameter("isbn");
            for(BookInfo book:bookList) {
                if(book.getIsbn().equals(isbn)) {
                    userShelf = makeUserShelf(loginUser.getUserId(), isbn);
                    String key = request.getParameter("have");
                    states = 0;
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
                    if(userDao.searchUserShelfForUserIdAndIsbn(userShelf.getUserId(), isbn).size() <= 0) {
                        userDao.insertUserShelf(userShelf);
                    }
                    if(bookDao.searchBookInfoForIsbn(isbn).size() <= 0) {
                        bookDao.insertBookInfo(book);
                    }
                    break;
                }
            }


            // セッションから削除
            session.removeAttribute("bookList");
            // マイページへ
            url = "/bookMan/ShelfManagerServlet";
            response.sendRedirect(url);
            break;
        default:
            goTop(request, response);
            return;
        }

    }

    private void goTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/bookMan/ShelfMasterServlet");
    }

    private UserShelf makeUserShelf(String userId, String isbn) {
        UserShelf data = new UserShelf();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmSS");
        data = new UserShelf(sdf.format(date), isbn, userId, date, date, 0, 0, "", 0);

        return data;
    }
}
