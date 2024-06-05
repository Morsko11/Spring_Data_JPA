package com.example.datajpatest;

import com.example.datajpatest.model.Book;
import com.example.datajpatest.repository.RepositoryL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataJPARealDBTest {
    @Autowired
    private RepositoryL repositoryL;
    @Test
    void shoudSaveTest(){
        Book book = new Book("str","str",4,4);
        repositoryL.save(book);
        assertThat(3).isEqualTo(repositoryL.findAll().size());
        assertEquals("str",repositoryL.findByBookName(book.getBookName()).get().getBookName());
    }
    @Test
    void shoudUpdateTest(){
        Book book = new Book("str","str",4,4);
    Book byBookName = repositoryL.findByBookName("44").get();
        byBookName.setPages(book.getPages());
        byBookName.setAuthorName(book.getAuthorName());
        byBookName.setBookName(book.getBookName());
        byBookName.setTirazh(book.getTirazh());
        repositoryL.save(byBookName);
        assertEquals("str",repositoryL.findByBookName(byBookName.getBookName()).get().getBookName());
    }
    @Test
    void shoudFindAllTest(){
        assertEquals(2,repositoryL.findAll().size());
        assertThat(repositoryL.findAll().get(1).getAuthorName()).isEqualTo("444");
    }
    @Test
    void shoudDeleteByName(){
        repositoryL.deleteByBookName("44");
        assertThat(repositoryL.findAll().size()).isNotEqualTo(2);
        assertThat(repositoryL.findByBookName("44").isEmpty()).isTrue();
        assertEquals(1,repositoryL.findAll().size());
    }
    @Test
    void shoudGetMaxPages(){
        repositoryL.save(new Book("500","500",500,500));
        assertEquals(500,repositoryL.getMaxPages().getPages());
    }
    @Test
    void shoudSum(){
        assertEquals(488,repositoryL.getSumm());
    }
    @Test
    void shoudAVG(){
        assertThat(repositoryL.getAVG()).isEqualTo(244);
    }
}
