package com.lucas.chamados.backendchamadosprojetoufn.repositories;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>  {
}
