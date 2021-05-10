package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.entity.Label;

@Repository
public interface LabelRespository extends JpaRepository<Label, Long> {

}
