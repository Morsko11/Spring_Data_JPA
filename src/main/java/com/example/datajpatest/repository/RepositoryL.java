package com.example.datajpatest.repository;

import com.example.datajpatest.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RepositoryL extends JpaRepository<Book,Integer> {
    @Transactional
    @Modifying
    void deleteByBookName(String namebook);
    @Query(value="select * from book order by pages desc limit 1",nativeQuery = true)
    Book getMaxPages();


    Optional<Book>findByBookName(String name);
    @Query(value = "select sum (pages) from book",nativeQuery = true)
    Integer getSumm();
    @Query(value = "select avg(pages) from  book",nativeQuery = true)
    Double getAVG();
}
