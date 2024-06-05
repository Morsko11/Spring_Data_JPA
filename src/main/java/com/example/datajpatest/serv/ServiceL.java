package com.example.datajpatest.serv;

import com.example.datajpatest.model.Book;
import com.example.datajpatest.repository.RepositoryL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceL {
    private final RepositoryL repositoryL;


    public Book save(Book book) {

        return repositoryL.save(book);
    }

    public Book update(Book book, int id) {
        if (repositoryL.existsById(id)){
            Book byId = repositoryL.getById(id);
            byId.setTirazh(book.getTirazh());
            byId.setPages(book.getPages());
            byId.setAuthorName(book.getAuthorName());
            byId.setAuthorName(book.getAuthorName());
            return repositoryL.save(byId);
        }else throw new IllegalArgumentException();

    }

    public List<Book> findAll() {
        return repositoryL.findAll();
    }

    public void deleteByName(String namebook) {
         repositoryL.deleteByBookName(namebook);
    }

    public Book getMaxPages() {
        return repositoryL.getMaxPages();
    }

    public Integer getSumm() {
        return repositoryL.getSumm();
    }

    public Double getAVG() {
        return repositoryL.getAVG();
    }
}
