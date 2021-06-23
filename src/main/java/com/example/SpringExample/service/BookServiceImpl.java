package com.example.SpringExample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringExample.dao.BookRepository;
import com.example.SpringExample.dao.ReviewRepository;
import com.example.SpringExample.entity.Book;
import com.example.SpringExample.entity.Review;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;
	private ReviewRepository reviewRepository;
	
	 @Autowired
	  public BookServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository) {
	        this.bookRepository = bookRepository;
	        this.reviewRepository = reviewRepository;
	  }
	 
	@Override
	@Transactional
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book findById(int theId) {
		Optional<Book> result = bookRepository.findById(theId);
		Book theBook;
		if(result.isPresent()) {
			theBook = result.get();
		}
		else {
			return null;
		}
		return theBook;
	}

	@Override
	public Book save(Book theBook) {
		return bookRepository.save(theBook);
	}

	@Override
	public void deleteById(int theId) {
		bookRepository.deleteById(theId);
	}

	@Override
	public List<Review> findReviewsByBookId(int theId) {
		Optional<Book> result = bookRepository.findById(theId);
		Book theBook;
		List<Review> listReviews;
		if(result.isPresent()) {
			theBook = result.get();
			listReviews = theBook.getReviews();
		}
		else {
			return null;
		}
		return listReviews;
	}

	@Override
	public List<Review> findAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public void saveReview(Review theReview) {
		reviewRepository.save(theReview);
	}

}
