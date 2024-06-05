package com.example.datajpatest.controler;

import com.example.datajpatest.model.Book;
import com.example.datajpatest.serv.ServiceL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controler {
    private final ServiceL serviceL;

    @PostMapping("/save")
    public ResponseEntity<Book> save(@RequestBody Book book) {
        log.info("В методе save");
        return ResponseEntity.ok(serviceL.save(book));
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Book book,@RequestParam int id){
        log.info("в методе Update");
        Book update = serviceL.update(book, id);
        return new ResponseEntity<>("Book обновлен", HttpStatus.ACCEPTED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Book>> findAll(){
        return new ResponseEntity<>(serviceL.findAll(),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteByName")
    public ResponseEntity<String> deleteByName(@RequestParam String namebook){
        serviceL.deleteByName(namebook);
        return ResponseEntity.ok("Пользователь удален");
    }
    @GetMapping("/getMaxPages")
    public ResponseEntity<Book> getMaxPages(){
        return ResponseEntity.ok(serviceL.getMaxPages());
    }
    @GetMapping("/getSumm")
    public ResponseEntity<Integer> getSumm(){
        return ResponseEntity.ok(serviceL.getSumm());
    }
    @GetMapping("/getAVG")
    public ResponseEntity<Double> getAVG(){
        return new ResponseEntity<>(serviceL.getAVG(),HttpStatus.ACCEPTED);
    }

}
