package com.alexandresantos.films.service;

import com.alexandresantos.films.domain.films.Film;
import com.alexandresantos.films.domain.films.FilmRequestDTO;
import com.alexandresantos.films.domain.films.FilmResponseDTO;
import com.alexandresantos.films.repositories.FilmRepository;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FilmService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private FilmRepository repository;

    public List<FilmResponseDTO> getFilms(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Film> filmPage = this.repository.findAll(pageable);
        return filmPage.map(event -> new FilmResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getImageUrl()
        )).toList();
    }


    public Film createFilm(FilmRequestDTO filmRequest) {
        String imageUrl = null;

        if (filmRequest.image() != null) {
            imageUrl = this.uploadImage(filmRequest.image());
        }
        Film newFilm = new Film();
        newFilm.setTitle(filmRequest.title());
        newFilm.setDescription(filmRequest.description());
        newFilm.setImageUrl(imageUrl);
        repository.save(newFilm);
        return newFilm;
    }

    private String uploadImage(MultipartFile image) {
        String fileName = UUID.randomUUID() + "-" + image.getName();
        try {
            File file = this.convertMultipartToFile(image);
            s3Client.putObject(bucketName, fileName, file);
            file.delete();
            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (Exception ex) {
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }

    private File convertMultipartToFile(MultipartFile image) throws IOException {
        File convertFile = new File(Objects.requireNonNull(image.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(image.getBytes());
        fos.close();
        return convertFile;
    }
}
