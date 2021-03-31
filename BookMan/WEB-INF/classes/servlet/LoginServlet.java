package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/WEB-INF/jsp/login.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // アクセスチェック
        request.setCharacterEncoding("UTF-8");

        AccountDAO accountDao = new AccountDAO();
        String url = null;
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");

        System.out.println("ID:"+ id + "pass:" + pass);
        List<Account> accountList = accountDao.searchAccountForIdAndPass(id, pass);
        System.out.println("アカウント量"+accountList.size());
        if (accountList.size() > 0) {
            // ログイン情報保持
            HttpSession session = request.getSession();
            session.setAttribute("account", accountList.get(0));
            // マイページに遷移
            url = "/bookMan/ShelfManagerServlet";
            response.sendRedirect(url);
        } else {
            // NG
            url = "/WEB-INF/jsp/login.jsp";
            request.setAttribute("errMsg", "ログインに失敗");
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    private void goTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }
}
