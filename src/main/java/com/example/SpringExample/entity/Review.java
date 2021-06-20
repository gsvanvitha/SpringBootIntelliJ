package com.example.SpringExample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="review")
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@Getter
	@Setter
	private int id;
	
	@Column(name="comment")
	@Getter
	@Setter
	private String comment;
	
	 @Column(name = "book_id")
	 @Getter
	 @Setter
	    private int bookId;
	 	
	   /* public Review( String comment, Integer bookId) {
	      
	        this.comment = comment;
	        this.bookId = bookId;
	        System.out.println("==================="+bookId);
	    }
	 	@Autowired
	    public Review(int id, String comment, int bookId) {
	        this.id = id;
	        this.comment = comment;
	        this.bookId = bookId;
	    }
	public Review() {
		
	}*/

	public Review(String comment) {
		this.comment = comment;
	}

	/*public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
		System.out.println(bookId);
	}
*/
	@Override
	public String toString() {
		return "Review [id=" + id + ", comment=" + comment + ", bookId= "+ bookId + "]";
	}
	
}

