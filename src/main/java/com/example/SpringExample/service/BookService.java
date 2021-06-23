package com.example.SpringExample.service;

import java.util.List;

import com.example.SpringExample.entity.Book;
import com.example.SpringExample.entity.Review;

public interface BookService {

    List<Book> findAll();
	
	 Book findById(int theId);
	
	 Book save(Book theBook);
	
	 void deleteById(int theId);
	
    List<Review> findReviewsByBookId(int theId);

    List<Review> findAllReviews();

    void saveReview(Review theReview);
}
