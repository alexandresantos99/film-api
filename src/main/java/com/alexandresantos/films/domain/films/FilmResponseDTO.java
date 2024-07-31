package com.alexandresantos.films.domain.films;

import java.util.UUID;

public record FilmResponseDTO(
        UUID id,
        String title,
        String description,
        String imageUrl) {
}

