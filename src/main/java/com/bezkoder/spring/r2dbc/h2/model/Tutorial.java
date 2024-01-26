package com.bezkoder.spring.r2dbc.h2.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Tutorial {
  
  @Id
  private int id;

  private String title;

  private String description;

  private boolean published;

public Tutorial(int id, String title, String description, boolean published) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.published = published;
}




}
