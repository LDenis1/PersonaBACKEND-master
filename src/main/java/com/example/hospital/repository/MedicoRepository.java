package com.example.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

}
