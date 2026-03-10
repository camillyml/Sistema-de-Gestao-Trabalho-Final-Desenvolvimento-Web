package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository // Diz ao Spring que esta interface é um Componente de Acesso a Dados.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // A interface disponibiliza várias definições prontas como save(), findAll(), findById(), delete()
    Optional<Cliente> findByCpf(String cpf);
}