package com.alexandresantos.films.controller;

import com.alexandresantos.films.domain.films.Film;
import com.alexandresantos.films.domain.films.FilmRequestDTO;
import com.alexandresantos.films.domain.films.FilmResponseDTO;
import com.alexandresantos.films.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    @Autowired
    private FilmService service;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Film> create(
            @RequestParam("title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "image") MultipartFile image
    ) {
        FilmRequestDTO filmRequestDTO = new FilmRequestDTO(title, description, image);
        Film newFilm = this.service.createFilm(filmRequestDTO);
        return ResponseEntity.ok(newFilm);
    }

    @GetMapping
    public ResponseEntity<List<FilmResponseDTO>> getFilms(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size
    ) {
        List<FilmResponseDTO> allEvents = this.service.getFilms(page, size);
        return ResponseEntity.ok(allEvents);
    }
}
