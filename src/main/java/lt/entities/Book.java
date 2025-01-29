package lt.entities;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Books")
@Schema(description = "Entidad Libro")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title, author;
  private Integer pages;
  private Double price;
  @Schema(description = "Fecha de lanzamiento")
  private LocalDate releaseDate;
  private Boolean online;

  public Book() {
  }

  public Book(Long id, String title, String author, Integer pages, Double price, LocalDate releaseDate,
      Boolean online) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.pages = pages;
    this.price = price;
    this.releaseDate = releaseDate;
    this.online = online;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Boolean getOnline() {
    return online;
  }

  public void setOnline(Boolean online) {
    this.online = online;
  }

  @Override
  public String toString() {
    return "Book [id=" + id + ", title=" + title + ", author=" + author + ", pages=" + pages + ", price=" + price
        + ", releaseDate=" + releaseDate + ", online=" + online + "]";
  }

}
