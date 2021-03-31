package model;

import java.io.Serializable;
import java.util.Date;
public class BookInfo implements Serializable{
    private String isbn;
    private String title;
    private int price;
    private String authors;
    private String publisher;
    private Date publishDate;
    private String description;
    private String thumnail;
    private String smallThumnail;
    public BookInfo() {

    }
    public BookInfo(String isbn, String title, int price, String authors, String publisher, Date publishDate,
            String description, String thumnail, String smallThumnail) {
        super();
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.description = description;
        this.thumnail = thumnail;
        this.smallThumnail = smallThumnail;
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

}
