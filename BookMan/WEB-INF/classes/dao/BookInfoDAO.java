package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.BookInfo;

public class BookInfoDAO {
    //private final String JDBC_URL = "jdbc:mysql://localhost:3306/bookman";
//    private final String DB_USER = "user07";
//    private final String DB_PASS = "root07pass";

    private BookInfo bookInfo;
    private String isbn;
    private String title;
    private int price;
    private String authors;
    private String publisher;
    private Date publishDate;
    private String description;
    private String thumbnail;
    private String smallThumbnail;

    public BookInfoDAO() {
        // クラスのロード。JDBCドライバをロードする
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        }
    }

	public List<BookInfo> searchBookInfoForTitle(String inputTitle) {
        List<BookInfo> bookInfoList = new ArrayList<>();
        String sql = "SELECT * FROM BOOKINFO WHERE Title like ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, "%" + inputTitle + "%");

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                isbn = rs.getString("isbn");
                title = rs.getString("title");
                price = rs.getInt("price");
                authors = rs.getString("authors");
                publisher = rs.getString("publisher");
                publishDate = rs.getDate("publishDate");
                description = rs.getString("description");
                thumbnail = rs.getString("thumbnail");
                smallThumbnail = rs.getString("smallThumbnail");

                bookInfo = new BookInfo(isbn, title, price, authors, publisher, publishDate, description, thumbnail,
                        smallThumbnail);
                bookInfoList.add(bookInfo);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return bookInfoList;
    }

    public List<BookInfo> searchBookInfoForIsbn(String inputIsbn) {
        List<BookInfo> bookInfoList = new ArrayList<>();
        String sql = "SELECT * FROM BOOKINFO WHERE isbn = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, inputIsbn);

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                isbn = rs.getString("isbn");
                title = rs.getString("title");
                price = rs.getInt("price");
                authors = rs.getString("authors");
                publisher = rs.getString("publisher");
                publishDate = rs.getDate("publishDate");
                description = rs.getString("description");
                thumbnail = rs.getString("thumbnail");
                smallThumbnail = rs.getString("smallThumbnail");

                bookInfo = new BookInfo(isbn, title, price, authors, publisher, publishDate, description, thumbnail,
                        smallThumbnail);
                bookInfoList.add(bookInfo);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return bookInfoList;
    }

    public int insertBookInfo(BookInfo bookInfo) {
        int count = 0;
        String sql = "INSERT INTO BOOKINFO VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, bookInfo.getIsbn());
            pSmt.setString(2, bookInfo.getTitle());
            pSmt.setInt(3, bookInfo.getPrice());
            pSmt.setString(4, bookInfo.getAuthors());
            pSmt.setString(5, bookInfo.getPublisher());
            java.sql.Date sqlDate = new java.sql.Date(bookInfo.getPublishDate().getTime());
            pSmt.setDate(6, sqlDate);
            pSmt.setString(7, bookInfo.getDescription());
            pSmt.setString(8, bookInfo.getThumnail());
            pSmt.setString(9, bookInfo.getSmallThumnail());

            count = pSmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }

    public int deleteBookInfo(BookInfo bookInfo) {
        int count = 0;
        String sql = "DELETE FROM BOOKINFO WHERE isbn = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, bookInfo.getIsbn());

            count = pSmt.executeUpdate();

            pSmt.close();
            con.close();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }

    public int updateBookInfo(BookInfo bookInfo) {
        int count = 0;
        String sql = "UPDATE BOOKINFO SET isbn = ?,title = ?,price = ?,authors = ?,publisher = ?,publishDate = ?"
                + ",description = ?,thumbnail = ?,smallThumbnail = ? WHERE isbn = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, bookInfo.getIsbn());
            pSmt.setString(2, bookInfo.getTitle());
            pSmt.setInt(3, bookInfo.getPrice());
            pSmt.setString(4, bookInfo.getAuthors());
            pSmt.setString(5, bookInfo.getPublisher());
            java.sql.Date sqlDate = new java.sql.Date(bookInfo.getPublishDate().getTime());
            pSmt.setDate(6, sqlDate);
            pSmt.setString(7, bookInfo.getDescription());
            pSmt.setString(8, bookInfo.getThumnail());
            pSmt.setString(9, bookInfo.getSmallThumnail());
            pSmt.setString(10, bookInfo.getIsbn());

            count = pSmt.executeUpdate();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }

    public List<BookInfo> findAll() {
        List<BookInfo> bookInfoList = new ArrayList<>();
        String sql = "SELECT * FROM BOOKINFO";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                isbn = rs.getString("isbn");
                title = rs.getString("title");
                price = rs.getInt("price");
                authors = rs.getString("authors");
                publisher = rs.getString("publisher");
                publishDate = rs.getDate("publishDate");
                description = rs.getString("description");
                thumbnail = rs.getString("thumbnail");
                smallThumbnail = rs.getString("smallThumbnail");

                bookInfo = new BookInfo(isbn, title, price, authors, publisher, publishDate, description, thumbnail,
                        smallThumbnail);
                bookInfoList.add(bookInfo);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return bookInfoList;
    }

}
