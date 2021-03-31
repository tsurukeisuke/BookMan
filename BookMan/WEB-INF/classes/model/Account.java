package model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable{
    private String userId;
    private String pass;
    private String mail;
    private String name;
    private Date birthDay;
    private int gender;

    public Account() {
        // TODO 自動生成されたコンストラクター・スタブ
    }
    public Account(String userId, String pass, String mail, String name, Date birthDay, int gender) {
        this.userId = userId;
        this.pass = pass;
        this.mail = mail;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getUserId() {
        return userId;
    }
    public String getPass() {
        return pass;
    }
    public String getMail() {
        return mail;
    }
    public String getName() {
        return name;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public int getGender() {
        return gender;
    }
    /*
     * public Account(String userId) { this.userId = userId; } public Account(String
     * userId, String pass) { this.userId = userId; this.pass = pass; }
     */
}
