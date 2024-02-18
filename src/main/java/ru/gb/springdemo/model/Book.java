package ru.gb.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Entity
@Table(name = "books")
@Data


public class Book {

  public static long sequence = 1L;
  @Id
  private  long id;
  @Column (name = "name")
  private String name;
  public Book(){};

  public Book(long id,String name){
    this.id = id;
    this.name = name;
  }

  public Book(String name) {
    this(sequence++, name);
  }

}



