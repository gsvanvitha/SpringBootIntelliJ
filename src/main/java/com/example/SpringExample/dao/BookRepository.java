package com.example.SpringExample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringExample.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
