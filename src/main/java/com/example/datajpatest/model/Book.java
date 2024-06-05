package com.example.datajpatest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bookname")
    private String bookName;
    @Column(name = "authorName")
    private String authorName;
    @Column(name = "pages")
    private int pages;
    @Column(name = "tirazh")
    private int tirazh;

    public Book(String bookName, String authorName, int pages, int tirazh) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.pages = pages;
        this.tirazh = tirazh;
    }
}
