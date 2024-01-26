package com.bezkoder.spring.r2dbc.h2.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoCategoria {
  
  @Id
  private int id;
  
  @NonNull
  private String titulo;






}
