package com.bridgelabz.fundoo.note.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.note.model.NoteData;

@Repository
public interface NoteRepository extends JpaRepository<NoteData, Long>{

	public Optional<NoteData> findById(Long id);
	
}
