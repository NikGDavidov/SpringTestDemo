package ru.gb.springdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;


//@RequiredArgsConstructor
@Entity
@Table(name = "readers")
@Data
public class Reader {

  public static long sequence = 1L;

  //private final long id;
 // private final String name;
  @Id
  private  long id;
  @Column(name = "name")
  private String name;
  public Reader(){};

  public Reader(long id,String name){
    this.id = id;
    this.name = name;
  }
  public Reader(String name) {
    this(sequence++, name);
  }

}
