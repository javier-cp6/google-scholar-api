package com.company.googlescholarapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String author_id;
  private String name;
  private String authorurl;
  private String affiliations;
  private String email;
  private Integer citations_all;
  private Integer citations_5y;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor_id() {
    return author_id;
  }

  public void setAuthor_id(String author_id) {
    this.author_id = author_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthorurl() {
    return authorurl;
  }

  public void setAuthorurl(String authorurl) {
    this.authorurl = authorurl;
  }

  public String getAffiliations() {
    return affiliations;
  }

  public void setAffiliations(String affiliations) {
    this.affiliations = affiliations;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getCitations_all() {
    return citations_all;
  }

  public void setCitations_all(Integer citations_all) {
    this.citations_all = citations_all;
  }

  public Integer getCitations_5y() {
    return citations_5y;
  }

  public void setCitations_5y(Integer citations_5y) {
    this.citations_5y = citations_5y;
  }

}
