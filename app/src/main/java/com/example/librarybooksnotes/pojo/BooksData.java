package com.example.librarybooksnotes.pojo;

public class BooksData {

    private String book_title ;
    private String book_author;
    private int book_pages_numbers;

    public BooksData(String book_title, String book_author, int book_pages_numbers) {
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages_numbers = book_pages_numbers;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public int getBook_pages_numbers() {
        return book_pages_numbers;
    }

    public void setBook_pages_numbers(int book_pages_numbers) {
        this.book_pages_numbers = book_pages_numbers;
    }
}
