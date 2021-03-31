package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Account;

public class AccountDAO {
//    private String JDBC_URL = "jdbc:mysql://localhost:3306/bookman";
//    private String DB_USER = "user07";
//    private String DB_PASS = "root07pass";

    private Account account;
    private String userId;
    private String pass;
    private String mail;
    private String name;
    private Date birthDay;
    private int gender;

    public AccountDAO() {
        // クラスのロード。JDBCドライバをロードする
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        }
    }

    public List<Account> searchAccountForId(String inputId) {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNT WHERE userId = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, inputId);

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                userId = rs.getString("userId");
                pass = rs.getString("pass");
                mail = rs.getString("Mail");
                name = rs.getString("name");
                birthDay = rs.getDate("birthday");
                gender = rs.getInt("gender");
                account = new Account(userId, pass, mail, name, birthDay, gender);
                accountList.add(account);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return accountList;
    }

    public List<Account> searchAccountForIdAndPass(String inputId, String inputPass) {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNT WHERE userId = ? and pass = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, inputId);
            pSmt.setString(2, inputPass);

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                userId = rs.getString("userId");
                pass = rs.getString("pass");
                mail = rs.getString("mail");
                name = rs.getString("name");
                birthDay = rs.getDate("birthday");
                gender = rs.getInt("gender");
                account = new Account(userId, pass, mail, name, birthDay, gender);
                accountList.add(account);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return accountList;
    }

    public int insertAccount(Account account) {
        int count = 0;
        String sql = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, account.getUserId());
            pSmt.setString(2, account.getPass());
            pSmt.setString(3, account.getMail());
            pSmt.setString(4, account.getName());
            java.sql.Date sqlDate = new java.sql.Date(account.getBirthDay().getTime());
            pSmt.setDate(5, sqlDate);
            pSmt.setInt(6, account.getGender());

            count = pSmt.executeUpdate();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }

    public int deleteAccount(Account account) {
        int count = 0;
            String sql = "DELETE FROM ACCOUNT WHERE userId = ?";
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS);
            PreparedStatement pSmt = con.prepareStatement(sql)) {
            pSmt.setString(1, account.getUserId());

            count = pSmt.executeUpdate();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return count;
    }

    public int updateAccount(Account account) {
        int count = 0;
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS)) {
            String sql = "UPDATE ACCOUNT SET pass = ?,mail = ?,name = ?,birthday = ?,gender = ? WHERE userId = ?";
            PreparedStatement pSmt = con.prepareStatement(sql);
            pSmt.setString(1, account.getPass());
            pSmt.setString(2, account.getMail());
            pSmt.setString(3, account.getName());
            java.sql.Date sqlDate = new java.sql.Date(account.getBirthDay().getTime());
            pSmt.setDate(4, sqlDate);
            pSmt.setInt(5, account.getGender());
            pSmt.setString(6, account.getUserId());
            count = pSmt.executeUpdate();

            pSmt.close();
            con.close();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

        return count;
    }

    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DAODefine.JDBC_URL, DAODefine.DB_USER, DAODefine.DB_PASS)) {
            String sql = "SELECT * FROM ACCOUNT";
            PreparedStatement pSmt = con.prepareStatement(sql);

            ResultSet rs = pSmt.executeQuery();

            while (rs.next()) {
                userId = rs.getString("userId");
                pass = rs.getString("pass");
                mail = rs.getString("mail");
                name = rs.getString("name");
                birthDay = rs.getDate("birthday");
                gender = rs.getInt("gender");
                account = new Account(userId, pass, mail, name, birthDay, gender);
                accountList.add(account);
            }

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return accountList;
    }

}
