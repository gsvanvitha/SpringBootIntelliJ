package com.example.SpringExample.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@Entity
@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@Getter
	@Setter
	private int id;
	
	@Column(name="title")
	@NotNull(message = "'Title' is required")
	@Getter
	@Setter
	private String title;
	
	@Column(name="author")
	@NotNull(message = "'Author' is required")
	@Getter
	@Setter
	private String author;
	
	@Column(name="publisher")
	@NotNull(message = "'Publisher' is required")
	@Getter
	@Setter
	private String publisher;
	
	@Column(name="stock")
	@NotNull(message = "'Stock' is required")
    @Min(value = 1, message = "At least '1' book is required")
    @Max(value = 100, message = "Stock limit is '100' only")
	@Getter
	@Setter
	private int stock;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="book_id")
	@Getter
	@Setter
	private List<Review> reviews;

	public void addReview(Review tempReview) {
		if(reviews==null)reviews=new ArrayList<>();
		reviews.add(tempReview);
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", stock="
				+ stock + "]";
	}
	
	
}
