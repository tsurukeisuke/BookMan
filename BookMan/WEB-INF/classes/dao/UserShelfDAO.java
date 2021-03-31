package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.UserShelf;

public class UserShelfDAO {
//    private final String JDBC_URL = "jdbc:mysql://localhost:3306/bookman";
//    private final String DB_USER = "user07";
//    private final String DB_PASS = "root07pass";

    private UserShelf userShelf;
    private String shelfId;
    private String isbn;
    private String userId;
    private Date purchaseDate;
    private Date updateDate;
    private int states;
    private int review;
    private String comment;
    private int isSecretComment;

    public UserShelfDAO() {
        // クラスのロード。JDBCドライバをロードする
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        }
    }

    public List<UserShelf> searchUserShelfForTitle(String inputTitle) {
        List<UserShelf> userShelfList = new ArrayList<>();
        String sql = "SELECT * FROM UserShelf WHERE title like ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {

            pSmt.setString(1, "%" + inputTitle + "%");

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                shelfId = rs.getString("shelfId");
                isbn = rs.getString("isbn");
                userId = rs.getString("userId");
                purchaseDate = rs.getDate("purchaseDate");
                updateDate = rs.getDate("updateDate");
                states = rs.getInt("states");
                review = rs.getInt("review");
                comment = rs.getString("comment");
                isSecretComment = rs.getInt("isSecretComment");

                userShelf = new UserShelf(shelfId, isbn, userId, purchaseDate, updateDate, states, review ,comment ,isSecretComment);
                userShelfList.add(userShelf);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userShelfList;
    }

    public List<UserShelf> searchUserShelfForUserId(String inputUserId) {
        List<UserShelf> userShelfList = new ArrayList<>();
        String sql = "SELECT * FROM UserShelf WHERE userId = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {

            pSmt.setString(1, inputUserId );

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                shelfId = rs.getString("shelfId");
                isbn = rs.getString("isbn");
                userId = rs.getString("userId");
                purchaseDate = rs.getDate("purchaseDate");
                updateDate = rs.getDate("updateDate");
                states = rs.getInt("states");
                review = rs.getInt("review");
                comment = rs.getString("comment");
                isSecretComment = rs.getInt("isSecretComment");

                userShelf = new UserShelf(shelfId, isbn, userId, purchaseDate, updateDate, states, review ,comment ,isSecretComment);
                userShelfList.add(userShelf);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userShelfList;
    }


    public List<UserShelf> searchUserShelfForIsbn(String inputIsbn) {
        List<UserShelf> userShelfList = new ArrayList<>();
        String sql = "SELECT * FROM UserShelf WHERE isbn = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {

            pSmt.setString(1, inputIsbn );

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                shelfId = rs.getString("shelfId");
                isbn = rs.getString("isbn");
                userId = rs.getString("userId");
                //String型⇒Date型変換
                purchaseDate = rs.getDate("purchaseDate");
                updateDate = rs.getDate("updateDate");
                states = rs.getInt("states");
                review = rs.getInt("review");
                comment = rs.getString("comment");
                isSecretComment = rs.getInt("isSecretComment");

                userShelf = new UserShelf(shelfId, isbn, userId, purchaseDate, updateDate, states, review ,comment ,isSecretComment);
                userShelfList.add(userShelf);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userShelfList;
    }

	public List<UserShelf> searchUserShelfForUserIdAndIsbn(String inputUserId ,String inputIsbn) {
        List<UserShelf> userShelfList = new ArrayList<>();
        String sql = "SELECT * FROM UserShelf WHERE userId = ? and isbn = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {

            pSmt.setString(1, inputUserId );
        	pSmt.setString(2, inputIsbn );

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                shelfId = rs.getString("shelfId");
                isbn = rs.getString("isbn");
                userId = rs.getString("userId");
                purchaseDate = rs.getDate("purchaseDate");
                updateDate = rs.getDate("updateDate");
                states = rs.getInt("states");
                review = rs.getInt("review");
                comment = rs.getString("comment");
                isSecretComment = rs.getInt("isSecretComment");

                userShelf = new UserShelf(shelfId, isbn, userId, purchaseDate, updateDate, states, review ,comment ,isSecretComment);
                userShelfList.add(userShelf);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userShelfList;
    }

    //インサート
    public int insertUserShelf(UserShelf userShelf) {
        int count = 0;
        String sql = "INSERT INTO UserShelf VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, userShelf.getShelfId());
            pSmt.setString(2, userShelf.getIsbn());
            pSmt.setString(3, userShelf.getUserId());
            java.sql.Date sqlPurChaseDate = new java.sql.Date(userShelf.getPurchaseDate().getTime());
            pSmt.setDate(4, sqlPurChaseDate);
            java.sql.Date sqlUpdateDate = new java.sql.Date(userShelf.getUpdateDate().getTime());
            pSmt.setDate(5, sqlUpdateDate);
            pSmt.setInt(6, userShelf.getStates());
            pSmt.setInt(7, userShelf.getReview());
            pSmt.setString(8, userShelf.getComment());
            pSmt.setInt(9, userShelf.getIsSecretComment());

            count = pSmt.executeUpdate();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }


    //デリート
    public int deleteUserShelf(UserShelf userShelf) {
        int count = 0;
        String sql = "DELETE FROM UserShelf WHERE shelfId = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, userShelf.getShelfId());

            count = pSmt.executeUpdate();

            pSmt.close();
            con.close();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }

	//アップデート
	public int updateUserShelf(UserShelf userShelf) {
        int count = 0;
        String sql = "UPDATE UserShelf SET purchaseDate = ?,updateDate = ?,states = ?,review = ?,comment = ?,isSecretComment = ? WHERE shelfId = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
                PreparedStatement pSmt = con.prepareStatement(sql)) {
            /*
             * String sql =
             * "UPDATE UserShelf SET shelfId,isbn,userId,purChaseDate,updateDate,states,review,comment,isSecretComment = ?,?,?,?,?,?,?,?,? WHERE shelfId = ?"
             * ;
             */
            java.sql.Date sqlPurChaseDate = new java.sql.Date(userShelf.getPurchaseDate().getTime());
            pSmt.setDate(1, sqlPurChaseDate);
            java.sql.Date sqlUpdateDate = new java.sql.Date(userShelf.getUpdateDate().getTime());
            pSmt.setDate(2, sqlUpdateDate);
            pSmt.setInt(3, userShelf.getStates());
            pSmt.setInt(4, userShelf.getReview());
            pSmt.setString(5, userShelf.getComment());
            pSmt.setInt(6, userShelf.getIsSecretComment());
        	pSmt.setString(7, userShelf.getShelfId());

            count = pSmt.executeUpdate();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }


    public List<UserShelf> findAll() {
        List<UserShelf> userShelfList = new ArrayList<>();
        String sql = "SELECT * FROM UserShelf";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                shelfId = rs.getString("shelfId");
                isbn = rs.getString("isbn");
                userId = rs.getString("userId");
                //String型⇒Date型変換
                purchaseDate = rs.getDate("purchaseDate");
                updateDate = rs.getDate("updateDate");
                states = rs.getInt("states");
                review = rs.getInt("review");
                comment = rs.getString("comment");
                isSecretComment = rs.getInt("isSecretComment");

                userShelf = new UserShelf(shelfId, isbn, userId, purchaseDate, updateDate, states, review ,comment ,isSecretComment);
                userShelfList.add(userShelf);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return userShelfList;
    }



}
