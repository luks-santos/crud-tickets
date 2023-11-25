package com.lucas.chamados.backendchamadosprojetoufn;

import com.lucas.chamados.backendchamadosprojetoufn.entities.Category;
import com.lucas.chamados.backendchamadosprojetoufn.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendChamadosProjetoUfnApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendChamadosProjetoUfnApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository) {

        return args -> {
            categoryRepository.deleteAll();

            Category c1 = new Category();
            c1.setName("Sugestão");

            categoryRepository.save(c1);
        };
    }
}
