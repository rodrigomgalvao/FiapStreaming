package com.bezkoder.spring.r2dbc.h2.model;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VideoCategoria {
  
  @Id
  private int id;
  private String titulo;



public VideoCategoria(int id, String titulo) {
	super();
	this.id = id;
	this.titulo = titulo;

}




}
