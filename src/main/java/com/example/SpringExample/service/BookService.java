package com.example.SpringExample.service;

import java.util.List;

import com.example.SpringExample.entity.Book;
import com.example.SpringExample.entity.Review;

public interface BookService {
	
public List<Book> findAll();
	
	public Book findById(int theId);
	
	public void save(Book theBook);
	
	public void deleteById(int theId);
	
    List<Review> findReviewsByBookId(int theId);

    List<Review> findAllReviews();

    void saveReview(Review theReview);
}
