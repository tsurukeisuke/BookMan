package model;

import java.io.Serializable;
import java.util.Date;

public class UserBookInfo implements Serializable{
    private String userId;
    private String isbn;
    private String title;
    private int price;
    private String authors;
    private String publisher;
    private Date publishDate;
    private String description;
    private Date purchaseDate;
    private Date updateDate;
    private int states;
    private int review;
    private String comment;
    private int isSecretComment;
    private String thumnail;
    private String smallThumnail;
    public UserBookInfo() {
    }
    public UserBookInfo(String userId, String isbn, String title, int price, String authors, String publisher,
            Date publishDate, String description, Date purchaseDate, Date updateDate, int states, int review,
            String comment, int isSecretComment, String thumnail, String smallThumnail) {
        super();
        this.userId = userId;
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.updateDate = updateDate;
        this.states = states;
        this.review = review;
        this.comment = comment;
        this.isSecretComment = isSecretComment;
        this.thumnail = thumnail;
        this.smallThumnail = smallThumnail;
    }


    public String getThumnail() {
        return thumnail;
    }
    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }
    public String getSmallThumnail() {
        return smallThumnail;
    }
    public void setSmallThumnail(String smallThumnail) {
        this.smallThumnail = smallThumnail;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
