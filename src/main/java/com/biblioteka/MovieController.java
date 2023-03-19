package com.biblioteka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository; //korzystanie z repozytorium
    @GetMapping("")
    public List<Movie>getAll(){
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") int id){
        return movieRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Movie> movies){
        return movieRepository.save(movies);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        Movie movie = getById(id);
        movieRepository.delete(movie);
        return "Movie has been deleted";
    }

    @PutMapping("/{id}")
    public int update(@PathVariable ("id") int id, @RequestBody Movie updatedMovie){
        Movie movie = movieRepository.getById(id);
        if(movie != null){
          movie.setName(updatedMovie.getName());
          movie.setRating(updatedMovie.getRating());
          return movieRepository.update(movie);
        } else{
            return -1;
        }
    }
}
