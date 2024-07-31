package com.alexandresantos.films.repositories;

import com.alexandresantos.films.domain.films.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID> {
}
