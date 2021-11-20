package com.example.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hospital.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
