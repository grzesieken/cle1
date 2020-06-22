package com.example.demo;

import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface NotesRepository extends CassandraRepository<Note, UUID> {
}
