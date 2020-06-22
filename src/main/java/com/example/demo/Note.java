package com.example.demo;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("notes")
@Value
public class Note {

  @PrimaryKey
  private UUID id;

  @Column
  private String content;

}
