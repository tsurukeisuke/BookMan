package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookInfoDAO;
import model.Account;
import model.BookInfo;

/**
 * Servlet implementation class ShelfMasterServlet
 */
@WebServlet("/ShelfMasterServlet")
public class ShelfMasterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShelfMasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // DB接続
        BookInfoDAO bookDao = new BookInfoDAO();
        List<BookInfo> bookList = bookDao.findAll();
        String url = "/WEB-INF/jsp/top.jsp";
        request.setAttribute("bookList", bookList);
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String modeStr = request.getParameter("mode");
        String url = "/WEB-INF/jsp/top.jsp";
        BookInfoDAO bookDao = null;
        List<BookInfo> bookList = null;
        if (!modeStr.matches("[0-9]+")) {
            doGet(request, response);
            return;
        }
        switch (Integer.parseInt(modeStr)) {
        case 1:
            break;
        case 2:
            String key = request.getParameter("key");
            if (key == null) {
                doGet(request, response);
                break;
            }
            bookDao = new BookInfoDAO();
            bookList = bookDao.searchBookInfoForTitle(key);
            request.setAttribute("bookList", bookList);
            request.getRequestDispatcher(url).forward(request, response);
            break;
        case 3:
            String isbnStr = request.getParameter("isbn");
            bookDao = new BookInfoDAO();
            bookList = bookDao.searchBookInfoForIsbn(isbnStr);
            BookInfo detailBook = bookList.get(0);
            request.setAttribute("bookInfo", detailBook);
            url = "/WEB-INF/jsp/detailBookLogout.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            break;
        default:
            doGet(request, response);
            break;
        }

    }

    public static boolean goTop(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginUser = (Account) session.getAttribute("account");
        String url = "/bookMan/ShelfMasterServlet";
        if (loginUser == null) {
            response.sendRedirect(url);
            return true;
        }
        return false;
    }
}
