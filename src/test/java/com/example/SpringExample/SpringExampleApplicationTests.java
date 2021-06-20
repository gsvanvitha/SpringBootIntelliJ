package com.example.SpringExample;

import com.example.SpringExample.dao.BookRepository;
import com.example.SpringExample.dao.ReviewRepository;
import com.example.SpringExample.entity.Book;
import com.example.SpringExample.service.BookService;
import com.example.SpringExample.service.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringExampleApplicationTests {
    @Test
    public void contextLoads() {
    }

//    @Autowired
//    private BookService bookService;

    @Autowired
    private BookService bookService;


    @Autowired
    private BookRepository bookRepository;


    @Test
    public void testGetBooks() {
        List<Book> bookList = bookRepository.findAll();
        int initialSize = bookList.size();
        System.out.println(bookList);
        Book book = new Book();
        book.setAuthor("Sidney Sheldon");
        book.setPublisher("FingerPrint Classics");
        book.setStock(38);
        book.setTitle("The stars shine down");


        bookService.save(book);

        List<Book> updatedBookList = bookService.findAll();
        System.out.println(updatedBookList);
        assertEquals(updatedBookList.size(), initialSize + 1);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setAuthor("Sidney Sheldon");
        book.setPublisher("FingerPrint Classics");
        book.setStock(38);
        book.setTitle("The sands of time");

        bookService.save(book);
        int id = book.getId();

        Book tempBook = bookService.findById(id);

        assertEquals(tempBook.getId(), id );
        assertEquals(tempBook.getAuthor(), book.getAuthor());
        assertEquals(tempBook.getTitle(), book.getTitle());
        assertEquals(tempBook.getPublisher(), book.getPublisher());
        assertEquals(tempBook.getStock(), book.getStock());
    }

    @Test
    public void testGetBook() {

        Book book = new Book();
        book.setAuthor("Sidney Sheldon");
        book.setPublisher("FingerPrint Classics");
        book.setStock(38);
        book.setTitle("Sky is Falling");

        bookService.save(book);

        int id = book.getId();

        Book tempBook = bookService.findById(id);
        assertNotEquals(tempBook, null);
        assertEquals(tempBook.getId(), id);
    }

    @Test
    public void testDeleteBook() {

        Book book = new Book();
        book.setAuthor("John Green");
        book.setPublisher("FingerPrint Classics");
        book.setStock(38);
        book.setTitle("The Fault In Our Stars");

        bookService.save(book);
        int id = book.getId();

        bookService.deleteById(id);
        Book tempBook=null;
        try{
            tempBook = bookService.findById(id);
        }
        catch (Exception e){
            assertEquals(e.getMessage(),"Did not find employee id - "+id);
        }
    }

    @Test
    public void testUpdateBook() {

        Book book = new Book();
        book.setAuthor("Sidney Sheldon");
        book.setPublisher("FingerPrint Classics");
        book.setStock(38);
        book.setTitle("The other side of me");

        bookService.save(book);
        int id = book.getId();

        Book tempBook = bookService.findById(id);
        book.setStock(99);

        bookService.save(book);

        book = bookService.findById(id);

        assertEquals(book.getStock(), 99);

    }
}
