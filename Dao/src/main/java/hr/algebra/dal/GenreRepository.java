/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Genre;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author musam
 */
public interface GenreRepository {
    int createGenre(Genre genre) throws Exception;

    void updateGenre(int id, Genre genre) throws Exception;

    void deleteGenre(int id) throws Exception;

    Optional<Genre> selectGenre(int id) throws Exception;

    List<Genre> selectGenres() throws Exception;
}
