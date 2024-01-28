package ru.gb.springdemo.model;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
@Entity
@Table(name = "issues")
public class Issue {

  public static long sequence = 1L;

 // private final long id;
  //private final long bookId;
  //private final long readerId;

  /**
   * Дата выдачи
   */
  //private final LocalDateTime timestamp;
  @Id
  private long id;
  @Column (name = "book_id")
  private long bookId;
  @Column(name = "reader_id")
  private long readerId;
  @Column (name = "timestamp")
  private LocalDateTime timestamp;
  @Column (name = "returnDate")
  private LocalDateTime returnDate;
  public Issue(){
    this.timestamp = LocalDateTime.now();
  };

  public Issue(long bookId, long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.timestamp = LocalDateTime.now();
  }



}
