package ru.anani.lesson19.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anani.lesson19.entities.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
