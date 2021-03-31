package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class UnregisterAccountServlet
 */
@WebServlet("/UnregisterAccountServlet")
public class UnregisterAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnregisterAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        goTop(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // ログインしているか確認するため、セッションスコープからユーザー情報を取得
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = null;
        String confStr = request.getParameter("confirm");

        if (loginUser == null) {
            // ログインタイムアウト
            goTop(request, response);
            return;
        }

        if (confStr == null) {
            url = "/WEB-INF/jsp/confirmInfo.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }
        if (!confStr.matches("[0-9]+")) {
            // 不正アクセス
            goTop(request, response);
            return;
        }
        switch (Integer.parseInt(confStr)) {
        case 1:     // はい
            // DB接続 アカウント削除
            AccountDAO accountDao = new AccountDAO();
            if(accountDao.deleteAccount(loginUser)>0) {
                session.removeAttribute("account");
            }
            // トップへ
            url = "/bookMan/ShelfMasterServlet";
            response.sendRedirect(url);
            break;
        case 2:     // いいえ
            // アカウント詳細画面へ
            url = "/bookMan/AccountManagerServlet";
            response.sendRedirect(url);
            break;
        default:
            goTop(request, response);
            return;
        }
    }

    private void goTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }
}
