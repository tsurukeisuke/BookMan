package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserBookInfo;

public class UserBookInfoDAO {
//    private final String JDBC_URL = "jdbc:mysql://localhost:3306/bookman";
//    private final String DB_USER = "user07";
//    private final String DB_PASS = "roost07pass";

    public UserBookInfoDAO() {
        // クラスのロード。JDBCドライバをロードする
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        }
    }

    public List<UserBookInfo> searchUserBookInfoForTitle(String inputUserId, String inputTitle) {
        List<UserBookInfo> userBookInfoList = null;
        String sql = "SELECT * FROM USERBOOKINFO WHERE userId = ? AND title like ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, inputUserId);
            pSmt.setString(2, "%" + inputTitle + "%");

            ResultSet rs = pSmt.executeQuery();

            userBookInfoList = makeListForResult(rs);

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userBookInfoList;

    }

    public List<UserBookInfo> searchUserBookInfoForUserId(String inputUserId) {
        List<UserBookInfo> userBookInfoList = null;
        String sql = "SELECT * FROM USERBOOKINFO WHERE userId = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, inputUserId);

            ResultSet rs = pSmt.executeQuery();

            userBookInfoList = makeListForResult(rs);

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userBookInfoList;

    }


    public List<UserBookInfo> searchUserBookInfoForIsbn(String inputUserId, String inputIsbn) {
        List<UserBookInfo> userBookInfoList = null;
        String sql = "SELECT * FROM USERBOOKINFO WHERE userId = ? and isbn = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, inputUserId);
            pSmt.setString(2, inputIsbn);

            ResultSet rs = pSmt.executeQuery();

            userBookInfoList = makeListForResult(rs);

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userBookInfoList;

    }

    public List<UserBookInfo> findAll() {
        List<UserBookInfo> userBookInfoList = null;
        String sql = "SELECT * FROM USERBOOKINFO";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {

            ResultSet rs = pSmt.executeQuery();

            userBookInfoList = makeListForResult(rs);

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userBookInfoList;

    }

    private List<UserBookInfo> makeListForResult(ResultSet result) throws SQLException{
        List<UserBookInfo> retList = new ArrayList<>();
        while (result.next()) {
            UserBookInfo userBookInfo = new UserBookInfo(
                    result.getString("userId"),
                    result.getString("isbn"),
                    result.getString("title"),
                    result.getInt("price"),
                    result.getString("authors"),
                    result.getString("publisher"),
                    result.getDate("publishDate"),
                    result.getString("description"),
                    result.getDate("purchaseDate"),
                    result.getDate("updateDate"),
                    result.getInt("states"),
                    result.getInt("review"),
                    result.getString("comment"),
                    result.getInt("isSecretComment"),
                    result.getString("thumbnail"),
                    result.getString("smallthumbnail"));
            retList.add(userBookInfo);
        }
        return retList;
    }
}
