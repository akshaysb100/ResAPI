package com.bridgelabz.fundoo.label.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.label.model.LabelModel;

@Repository
public interface LabelRepository extends JpaRepository<LabelModel, Long>{
	
	public LabelModel findByLabelId(Long Id);

}
