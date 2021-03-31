package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserShelfDAO;
import model.Account;
import model.UserBookInfo;
import model.UserShelf;

/**
 * Servlet implementation class DeleteBookManager
 */
@WebServlet("/DeleteBookManager")
public class DeleteBookManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // ログインチェック
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = "/bookMan/ShelfMasterServlet";
        if (loginUser == null) {
            response.sendRedirect(url);
            return;
        }

        url = "/WEB-INF/jsp/confirmMyshelf.jsp";
        request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // ログインしているか確認するため、セッションスコープからユーザー情報を取得
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = "/bookMan/ShelfMasterServlet";
        String confStr = request.getParameter("confirm");

        if (loginUser == null) {
            // ログインタイムアウト
            response.sendRedirect(url);
            return;
        }

        if (confStr == null) {
            url = "/WEB-INF/jsp/confirmInfo.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!confStr.matches("[0-9]+")) {
            // 不正アクセス
            response.sendRedirect(url);
            return;
        }
        switch (Integer.parseInt(confStr)) {
        case 1:
            // DB接続 蔵書削除
            UserBookInfo ubInfo = (UserBookInfo)session.getAttribute("userBookInfo");
            UserShelfDAO usDao = new UserShelfDAO();
            UserShelf uShelf = usDao.searchUserShelfForUserIdAndIsbn(loginUser.getUserId(), ubInfo.getIsbn()).get(0);
            if(usDao.deleteUserShelf(uShelf)>0) {
                session.removeAttribute("userBookInfo");
            }
            // マイページへ
            url = "/bookMan/ShelfManagerServlet";
            response.sendRedirect(url);
            break;
        case 2:
            url = "/WEB-INF/jsp/detailBookLogin.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            break;
        default:
            response.sendRedirect(url);
            return;
        }
	}

}
