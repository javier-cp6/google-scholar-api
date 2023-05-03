package com.company.googlescholarapi.controller;

import com.company.googlescholarapi.model.Author;
import com.company.googlescholarapi.repository.AuthorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

  @Autowired
  private AuthorRepository authorRepository;

  @PostMapping("/authors")
  Author newAuthor(@RequestBody Author newAuthor) {
    return authorRepository.save(newAuthor);
  }

  @GetMapping("/authors")
  List<Author> getAllUsers() {
    return authorRepository.findAll();
  }

}
