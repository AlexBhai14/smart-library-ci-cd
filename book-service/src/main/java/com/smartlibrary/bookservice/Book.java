package com.smartlibrary.bookservice;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String category;
    private int quantity;
    private int availableQuantity;

    public Book() {}
    public Book(String title, String author, String category, int quantity) {
        this.title=title; this.author=author; this.category=category;
        this.quantity=quantity; this.availableQuantity=quantity;
    }
    public Long getId(){return id;} public String getTitle(){return title;}
    public void setTitle(String v){title=v;} public String getAuthor(){return author;}
    public void setAuthor(String v){author=v;} public String getCategory(){return category;}
    public void setCategory(String v){category=v;} public int getQuantity(){return quantity;}
    public void setQuantity(int v){quantity=v;} public int getAvailableQuantity(){return availableQuantity;}
    public void setAvailableQuantity(int v){availableQuantity=v;}
}
