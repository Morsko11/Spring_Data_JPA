package com.example.datajpatest;

import com.example.datajpatest.model.Book;
import com.example.datajpatest.repository.RepositoryL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class DataJPAFaikeDBTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    RepositoryL repositoryL;
    @Test
    void shoudReturnSaveBook(){
        Book book = new Book("str","str",4,4);
        testEntityManager.persist(book);
        assertThat("str").isEqualTo(repositoryL.findByBookName("str").get().getBookName());
        assertEquals("str",repositoryL.findByBookName("str").get().getBookName());

    }
    @Test
    void shoudUpdateBook(){
        Book book = new Book("str","str",4,4);
        Book book1 = new Book("strs","strs",43,43);
        testEntityManager.persist(book);
        testEntityManager.persist(book1);
        Book booktest = repositoryL.findByBookName(book1.getBookName()).get();
        booktest.setAuthorName("tttt");
        booktest.setBookName("RRRR");
        booktest.setTirazh(5);
        booktest.setPages(111);
        repositoryL.save(booktest);
        assertThat(book1.getBookName()).isEqualTo("RRRR");
        assertEquals("RRRR",book1.getBookName());
    }
    @Test
    void shoudFindAllBook(){
        Book book = new Book("str","str",4,4);
        Book book1 = new Book("strs","strs",43,43);
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        testEntityManager.persist(book);
        testEntityManager.persist(book1);
        assertThat(2).isEqualTo(repositoryL.findAll().size());
        assertThat(repositoryL.findAll()).isNotNull();
        assertThat(repositoryL.findAll()).isEqualTo(books);
        assertEquals(books,repositoryL.findAll());
    }
    @Test
    void shoudDeleteByName(){
        Book book = new Book("str","str",4,4);
        Book book1 = new Book("strs","strs",43,43);
        testEntityManager.persist(book);
        testEntityManager.persist(book1);
        repositoryL.deleteByBookName(book.getBookName());
        Book book2 = testEntityManager.find(Book.class, book.getId());
        assertThat(1).isEqualTo(repositoryL.findAll().size());
        assertThat(book2).isNull();
    }
    @Test
    void shoudGetMaxPages(){
        Book book = new Book("str","str",4,4);
        Book book1 = new Book("strs","strs",43,43);
        testEntityManager.persist(book);
        testEntityManager.persist(book1);
        assertThat(repositoryL.getMaxPages().getPages()).isEqualTo(43);
        assertEquals(43,repositoryL.getMaxPages().getPages());
    }
    @Test
    void shoudGetSummPages(){
        Book book = new Book("str","str",4,4);
        Book book1 = new Book("strs","strs",43,43);
        testEntityManager.persist(book);
        testEntityManager.persist(book1);
        assertEquals(47,repositoryL.getSumm());
        assertThat(repositoryL.getSumm()).isEqualTo(47);
    }
    @Test
    void shoudGetAVGPages(){
        Book book = new Book("str","str",4,4);
        Book book1 = new Book("strs","strs",46,43);
        testEntityManager.persist(book);
        testEntityManager.persist(book1);
        assertThat(repositoryL.getAVG()).isEqualTo(25);
        assertEquals(25,repositoryL.getAVG());
    }
}
