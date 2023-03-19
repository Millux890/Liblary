package com.biblioteka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll() {
        return jdbcTemplate.query("Select id, name, rating FROM movie",
                BeanPropertyRowMapper.newInstance(Movie.class)); //tłumaczneie na model obiektowy
    }

    public Movie getById(int id){
        return jdbcTemplate.queryForObject(
                "SELECT id,name,rating FROM movie WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Movie.class), id);
    }

    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate.update(
                "INSERT INTO movie(name,rating) VALUES(?,?)",
                movie.getName(), movie.getRating()
        ));
        return 1;
    }

    public int update(Movie movie){
        jdbcTemplate.update("UPDATE movie SET name = ?, rating = ? WHERE id = ?",
                movie.getName(), movie.getRating(), movie.getId());
        return 1;
    }

    public void delete(Movie movie){
        jdbcTemplate.update("DELETE FROM movie WHERE id = ? ", movie.getId());
    }
}
