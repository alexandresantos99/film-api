package com.alexandresantos.films.domain.films;

import org.springframework.web.multipart.MultipartFile;

public record FilmRequestDTO(String title, String description, MultipartFile image) {
}
