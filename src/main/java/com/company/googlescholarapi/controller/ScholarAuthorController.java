package com.company.googlescholarapi.controller;

import com.company.googlescholarapi.model.Author;
import com.company.googlescholarapi.repository.AuthorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
public class ScholarAuthorController {

  @Autowired
  private AuthorRepository authorRepository;

  @PostMapping(value = "/fetch-authors/{author_id}", produces = "application/json")

  public ScholarAuthorResponse fetchAuthors(@PathVariable("author_id") String author_id) {
    Dotenv dotenv = Dotenv.configure().load();
    String API_KEY = dotenv.get("API_KEY");

    String endpoint = "https://serpapi.com/search.json";
    String engine = "google_scholar_author";
    String api_key = API_KEY;

    RestTemplate restTemplate = new RestTemplate();
    String url = (endpoint + "?engine=" + engine + "&author_id=" +
        author_id + "&api_key=" + api_key);

    ResponseEntity<ScholarAuthorResponse> response = restTemplate.getForEntity(url, ScholarAuthorResponse.class);
    ScholarAuthorResponse searchResult = response.getBody();
    SearchMetadata searchMetadata = searchResult.getSearch_metadata();
    SearchAuthor author = searchResult.getAuthor();
    CitedBy citedBy = searchResult.getCited_by();

    CitedByTable citedByTable = citedBy.getTable();
    Citations citations = citedByTable.getCitations();

    System.out.println("Response status: " + response.getStatusCode());

    Author authorEntity = new Author();
    authorEntity.setAuthor_id(author_id);
    authorEntity.setName(author.getName());
    authorEntity.setAuthorurl(searchMetadata.getGoogle_scholar_author_url());
    authorEntity.setAffiliations(author.getAffiliations());
    authorEntity.setEmail(author.getEmail());
    authorEntity.setCitations_all(citations.getAll());
    authorEntity.setCitations_5y(citations.getSince_2018());

    authorRepository.save(authorEntity);

    return searchResult;
  }
}

class ScholarAuthorResponse {
  SearchMetadata search_metadata;
  SearchAuthor author;
  List<Article> articles;
  CitedBy cited_by;

  public SearchMetadata getSearch_metadata() {
    return search_metadata;
  }

  public SearchAuthor getAuthor() {
    return author;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public CitedBy getCited_by() {
    return cited_by;
  }

}

class SearchMetadata {
  String json_endpoint;
  String google_scholar_author_url;
  String raw_html_file;

  public String getJson_endpoint() {
    return json_endpoint;
  }

  public String getGoogle_scholar_author_url() {
    return google_scholar_author_url;
  }

  public String getRaw_html_file() {
    return raw_html_file;
  }
}

class SearchAuthor {
  String name;
  String affiliations;
  String email;
  String website;
  List<Interest> interests;

  public String getName() {
    return name;
  }

  public String getAffiliations() {
    return affiliations;
  }

  public String getEmail() {
    return email;
  }

  public String getWebsite() {
    return website;
  }

  public List<Interest> getInterests() {
    return interests;
  }

}

class Interest {
  String title;

  public String getTitle() {
    return title;
  }

}

class Article {
  String title;
  String link;
  String citation_id;
  String authors;
  String publication;
  ArticleCitedBy cited_by;

  public String getTitle() {
    return title;
  }

  public String getLink() {
    return link;
  }

  public String getCitation_id() {
    return citation_id;
  }

  public String getAuthors() {
    return authors;
  }

  public String getPublication() {
    return publication;
  }

  public ArticleCitedBy getCited_by() {
    return cited_by;
  }

}

class ArticleCitedBy {
  int value;

  public int getValue() {
    return value;
  }

}

class CitedBy {
  List<CitedByTable> table;

  public CitedByTable getTable() {
    if (table != null && !table.isEmpty()) {
      return table.get(0);
    }
    return null;
  }

}

class CitedByTable {
  Citations citations;

  public Citations getCitations() {
    return citations;
  }

}

class Citations {
  Integer all;
  Integer since_2018;

  public Integer getAll() {
    return all;
  }

  public Integer getSince_2018() {
    return since_2018;
  }

}
