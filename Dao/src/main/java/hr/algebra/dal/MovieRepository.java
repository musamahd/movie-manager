/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Movie;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author musam
 */
public interface MovieRepository {
    int createMovie(Movie movie) throws Exception;

    void createMovies(List<Movie> movies) throws Exception;

    void updateMovie(int id, Movie movie) throws Exception;

    void deleteMovie(int id) throws Exception;

    Optional<Movie> selectMovie(int id) throws Exception;

    List<Movie> selectMovies() throws Exception;

    void addActorToMovie(int movieId, int actorId) throws Exception;
    
    void updateMovieGenre(int movieId, int genreId) throws Exception;
    
    void updateMovieDirector(int movieId, int directorId) throws Exception;


    void removeActorFromMovie(int movieId, int actorId) throws Exception;

    List<Integer> getMovieActorIds(int movieId) throws Exception;
}
