package ru.anani.lesson19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anani.lesson19.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
