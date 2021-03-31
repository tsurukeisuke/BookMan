package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class RegisterAccountServlet
 */
@WebServlet("/RegisterAccountServlet")
public class RegisterAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/bookMan/ShelfMasterServlet");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String modeStr = request.getParameter("mode");
        if (!modeStr.matches("[0-9]+")) {
            goTop(request, response);
            return;
        }
        int mode = Integer.parseInt(modeStr);
        String url = null;
        String tmp = null;
        Account account;
        switch (mode) {
        case 1:
            url = "/WEB-INF/jsp/createAccount.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            break;
        case 2:
            // DB接続
            AccountDAO accountDao = new AccountDAO();
            // 各アカウント情報セット
            account = new Account();
            account.setName("");

            // ID
            tmp = request.getParameter("ID");
            if (tmp == null || tmp.length() <= 0) {
                invalidParameter(request, response, "不正な入力値です");
                break;
            }
            if (accountDao.searchAccountForId(tmp).size() > 0) {
                invalidParameter(request, response, "そのIDはすでに使われています");
                break;
            }
            account.setUserId(tmp);
            // パスワード
            tmp = request.getParameter("passward");
            if (tmp == null || tmp.length() <= 0) {
                invalidParameter(request, response, "不正な入力値です");
                break;
            }
            account.setPass(tmp);
            // メールアドレス
            tmp = request.getParameter("email-address");
            if (tmp == null || tmp.length() <= 0) {
                invalidParameter(request, response, "不正な入力値です");
                break;
            }
            account.setMail(tmp);
            // 誕生日
            tmp = request.getParameter("date");
            Date date;
            try {
                //tmp = String.format("%04d-%02d-%02d", Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                date = dateFormat.parse(tmp);
                account.setBirthDay(date);
            } catch (ParseException e) {
                e.printStackTrace();
                invalidParameter(request, response, "不正な入力値です");
            }

            // 性別
            tmp = request.getParameter("gender");
            if (!tmp.matches("[0-9]+")) {
                invalidParameter(request, response, "不正な入力値です");
            }
            account.setGender(Integer.parseInt(tmp));


            // データベース登録

            if (accountDao.insertAccount(account) > 0) {    // OK
                // セッションにログイン情報保持
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                url = "/bookMan/ShelfManagerServlet";
                response.sendRedirect(url);
            } else { // NG
                invalidParameter(request, response, "予期せぬエラーが発生しました");
            }

            break;
        default:
            goTop(request, response);
            break;
        }

    }

    private void goTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }

    private void invalidParameter(HttpServletRequest request, HttpServletResponse response, String errMsg)
            throws ServletException, IOException {
        request.setAttribute("errMsg", errMsg);
        String url = "/WEB-INF/jsp/createAccount.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

}
