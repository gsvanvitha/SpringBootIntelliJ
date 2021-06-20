package com.example.SpringExample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.SpringExample.entity.Book;
import com.example.SpringExample.entity.Review;
import com.example.SpringExample.service.BookService;

@Controller
@RequestMapping("/books")
public class BookRestController {
	
	
	private BookService bookService;
	
	@Autowired
	public BookRestController(BookService theBookService) {
		bookService=theBookService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder theWebDataBinder) {
	    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	    theWebDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
//	@GetMapping("/books")
//	public List<Book> getBooks(){
//		return bookService.findAll();
//	}
	@GetMapping("/list")
	public String showAllBooks(Model theModel){
		List<Book> theBooks = bookService.findAll();
		System.out.println(theBooks);
		theModel.addAttribute("books", theBooks);
		return "books/list-books";
	}
	 @GetMapping("/reviews")
	    public String showAllReviews(Model theModel) throws InterruptedException {
		 	
	        List<Review> theReviews = bookService.findAllReviews();
	        theModel.addAttribute("reviews", theReviews);
	        List<Book> theBooks = bookService.findAll();
	        theModel.addAttribute("books",theBooks);
	        return "books/list-reviews";
	    }
	 @GetMapping("/showFormForAdd")
	    public String showFormForAdd(Model theModel) throws InterruptedException {
		 Thread.sleep(5000);
	        Book newBook = new Book();
	        theModel.addAttribute("book", newBook);
	        return "books/add-update-book-form";
	    }

	    @PostMapping("/save")
	    public String saveBook(@Valid @ModelAttribute("book") Book theBook, BindingResult theBindingResult) {
	    	  if (theBindingResult.hasErrors())
	              return "books/add-update-book-form";
	          else {
	              bookService.save(theBook);
	              return "redirect:/books/list";
	          }
	    }

	    @GetMapping("/showFormForUpdate")
	    public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel) {
	        Book theBook = bookService.findById(theId);
	        System.out.println(theBook);
	        if(theBook==null)return "access-denied-page";
	        theModel.addAttribute("book", theBook);
	        return "books/add-update-book-form";
	    }

	    @GetMapping("/delete")
	    public String delete(@RequestParam("bookId") int theId) {
	        bookService.deleteById(theId);
	        return "redirect:/books/list";
	    }
	    @GetMapping("/reviews-by-bookId")
	    public String showAllReviewsByBookId(Model theModel, @RequestParam("bookId") int theId) throws RuntimeException {
	        List<Review> reviews = bookService.findReviewsByBookId(theId);
	        theModel.addAttribute("bookId", theId);

	        String bookTitle = bookService.findById(theId).getTitle();
	        theModel.addAttribute("bookTitle",bookTitle);

//	        if (reviews.isEmpty())
//	            return "reviews-not-found";
	        theModel.addAttribute("reviews", reviews);
	        theModel.getAttribute(bookTitle);
	        return "books/list-reviews-bookId";
	    }
	    @GetMapping("/showFormToAddReview")
	    public String addReview(@RequestParam("bookId") int theId, Model theModel) {
	        String bookTitle = bookService.findById(theId).getTitle();
	        theModel.addAttribute("bookTitle",bookTitle);
	        theModel.addAttribute("bookId", theId);
	        return "books/add-review";
	    }
	    @PostMapping("/reviews/save")
	    public String saveReview(@RequestParam("bookId") Integer theBookId, String comment) {
	    	Review tempReview  = new Review(theBookId,comment,theBookId);
	    	tempReview.setBookId(theBookId);
	        
	        
	        bookService.saveReview(tempReview);
	        return "redirect:/books/reviews";
	    }

	
}
