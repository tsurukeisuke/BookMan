package model;

import java.io.Serializable;
import java.util.Date;

public class UserShelf implements Serializable{
    private String shelfId;
    private String isbn;
    private String userId;
    private Date purchaseDate;
    private Date updateDate;
    private int states;
    private int review;
    private String comment;
    private int isSecretComment;
    public UserShelf() {

    }
    public UserShelf(String shelfId, String isbn, String userId, Date purchaseDate, Date updateDate, int states,
            int review, String comment, int isSecretComment) {
        super();
        this.shelfId = shelfId;
        this.isbn = isbn;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.updateDate = updateDate;
        this.states = states;
        this.review = review;
        this.comment = comment;
        this.isSecretComment = isSecretComment;
    }
    public String getShelfId() {
        return shelfId;
    }
    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String ueserId) {
        this.userId = ueserId;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public int getStates() {
        return states;
    }
    public void setStates(int states) {
        this.states = states;
    }
    public int getReview() {
        return review;
    }
    public void setReview(int review) {
        this.review = review;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getIsSecretComment() {
        return isSecretComment;
    }
    public void setIsSecretComment(int isSecretComment) {
        this.isSecretComment = isSecretComment;
    }


}
