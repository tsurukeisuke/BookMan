package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;
import model.UserBookInfo;

/**
 * Servlet implementation class AccountManagerServlet
 */
@WebServlet("/AccountManagerServlet")
public class AccountManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountManagerServlet() {
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
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = null;

        if (loginUser == null) {
            goTop(request, response);
            return;
        }

        // 編集中情報があれば削除
        session.removeAttribute("updateAccount");
        // ログインしている場合
        url = "/WEB-INF/jsp/accountInfo.jsp";
        request.setAttribute("userBookList", userBookList);
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
        Account manageUser = null;
        AccountDAO accountDao = null;
        String url = null;
        String confStr = request.getParameter("confirm");
        if(confStr == null) {
            confStr = "0";
        }
        if (loginUser == null || !confStr.matches("[0-9]+")) {
            goTop(request, response);
            return;
        }

        switch (Integer.parseInt(confStr)) {
        case 1:
            // はい
            // DB接続
            accountDao = new AccountDAO();
            manageUser = (Account)session.getAttribute("updateAccount");
            if(manageUser != null) {
                accountDao.updateAccount(manageUser);
            }else {
                // 編集後情報がない アカウント詳細画面へ
                url = "/bookMan/AccountManagerServlet";
                response.sendRedirect(url);
                break;
            }

            // セッションスコープの処理後マイページへ
            session.removeAttribute("updateAccount");
            session.setAttribute("account", manageUser);
            url = "/bookMan/ShelfManagerServlet";
            response.sendRedirect(url);
            break;
        case 2: //いいえ
            // 編集用のセッション破棄して編集画面へ
            session.removeAttribute("updateAccount");
            doGet(request, response);
            break;
        default:
            String tmp = null;
            // DB接続
            manageUser = new Account();
            // 各アカウント情報セット
            manageUser.setName(loginUser.getName());
            manageUser.setUserId(loginUser.getUserId());
            tmp = request.getParameter("passward");
            if (tmp == null || tmp.length() <= 0) {
                invalidParameter(request, response, "不正な入力値です");
                break;
            }
            manageUser.setPass(tmp);
            tmp = request.getParameter("email-address");
            if (tmp == null || tmp.length() <= 0) {
                invalidParameter(request, response, "不正な入力値です");
                break;
            }
            manageUser.setMail(tmp);
            // 誕生日
            tmp = request.getParameter("date");
//            tmp = request.getParameter("birthdayYear") + "-" + request.getParameter("birthdayMonth") + "-"
//                    + request.getParameter("birthdayDay");
            try {
                Date date;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                date = dateFormat.parse(tmp);
                manageUser.setBirthDay(date);
            } catch (ParseException e) {
                e.printStackTrace();
                invalidParameter(request, response, "不正な入力値です");
                break;
            }

            tmp = request.getParameter("gender");
            if (!tmp.matches("[0-9]+")) {
                invalidParameter(request, response, "不正な入力値です");
                break;
            }
            manageUser.setGender(Integer.parseInt(tmp));
            session.setAttribute("updateAccount", manageUser);
            // 確認画面へ
            url = "/WEB-INF/jsp/confirmDetail.jsp";
            request.getRequestDispatcher(url).forward(request, response);

            break;
        }

    }

    private void goTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/").forward(request, response);
    }

    private void invalidParameter(HttpServletRequest request, HttpServletResponse response, String errMsg)
            throws ServletException, IOException {
        request.setAttribute("errMsg", errMsg);
        doGet(request, response);
    }
}
