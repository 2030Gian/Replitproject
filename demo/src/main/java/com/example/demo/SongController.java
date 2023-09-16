package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.DrbgParameters.Reseed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public ResponseEntity<List<Song>> songs() {
        List<Song> songs = songRepository.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> song(@RequestBody Song song) {
        songRepository.save(song);

        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song song){
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song existingSong = optionalSong.get();
            existingSong.setTitle(song.getTitle());
            existingSong.setArtist(song.getArtist());
            existingSong.setAlbum(song.getAlbum());
            existingSong.setReleaseDate(song.getReleaseDate());
            existingSong.setGenre(song.getGenre());
            existingSong.setDuration(song.getDuration());
            existingSong.setCoverImage(song.getCoverImage());
            existingSong.setSpotifyUrl(song.getSpotifyUrl());
            songRepository.save(existingSong);
            return ResponseEntity.status(200).body("Updates");
        }
        else{
            return ResponseEntity.status(404).body("Not Found");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateSong(@PathVariable Long id, @RequestBody Song song){
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()) {
            Song existingSong = optionalSong.get();
            if (song.getTitle() != null) {
                existingSong.setTitle(song.getTitle());
            }

            if (song.getArtist()!=null){
                existingSong.setArtist(song.getArtist());
            }

            if (song.getAlbum()!=null){
                existingSong.setAlbum(song.getAlbum());
            }

            if (song.getReleaseDate()!=null){
                existingSong.setReleaseDate(song.getReleaseDate());
            }

            if (song.getGenre()!=null){
                existingSong.setGenre(song.getGenre());
            }

            if (song.getDuration()!=null){
                existingSong.setDuration(song.getDuration());
            }
/*
            if (song.getCoverImage()!=null){
                existingSong.setDuration(song.getCoverImage());
            }

            if (song.getSpotifyUrl()!=null){
                existingSong.setSpotifyUrl(song.getSpotifyUrl());
            }
            */
            songRepository.save(existingSong);
            return ResponseEntity.status(200).body("Partially Updated");
        }
        else{
            return ResponseEntity.status(404).body("Not Found");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id){
        Optional<Song> optionalSong = songRepository.findById(id);
        if(optionalSong.isPresent()){
            songRepository.deleteById(id);
            return ResponseEntity.status(200).body("Deleted");
        }
        else{
            return ResponseEntity.status(404).body("Not Found");
        }
}
    @GetMapping("/{search")
    public ResponseEntity<List<Song>> songs() {
        List<Song> songs = songRepository.();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}
