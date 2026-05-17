/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieRepository;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author musam
 */
public class MovieRepositoryImpl implements MovieRepository{

    // Stored Procedure Constants
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";
    private static final String UPDATE_MOVIE_GENRE = "{ CALL updateMovieGenre(?, ?) }";
    private static final String UPDATE_MOVIE_DIRECTOR = "{CALL updateMovieDirector(?, ?)}";


    private static final String ADD_ACTOR_TO_MOVIE = "{ CALL addActorToMovie (?,?) }";
    private static final String REMOVE_ACTOR_FROM_MOVIE = "{ CALL removeActorFromMovie (?,?) }";
    private static final String SELECT_MOVIE_ACTORS = "{ CALL selectMovieActors (?) }";

    // ️ Column Names
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String RELEASE_YEAR = "ReleaseYear";
    private static final String LINK = "Link";
    private static final String ID_GENRE = "IDGenre";
    private static final String ID_DIRECTOR = "IDDirector";

    private final DataSource dataSource = DataSourceSingleton.getInstance();
    
    @Override
    public int createMovie(Movie movie) throws Exception {
         try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getPicturePath());
            stmt.setInt(4, movie.getReleaseYear());
            stmt.setString(5, movie.getLink());
            stmt.setInt(6, movie.getGenre().getId());
            stmt.setInt(7, movie.getDirector().getId());
            stmt.registerOutParameter(8, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(8);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        for (Movie movie : movies) {
            createMovie(movie);
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
      try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {

           int genreId = movie.getGenre() != null ? movie.getGenre().getId() : 1;
        int directorId = movie.getDirector() != null ? movie.getDirector().getId() : 1;

        stmt.setInt(1, id);                           
        stmt.setString(2, movie.getTitle());         
        stmt.setString(3, movie.getDescription());    
        stmt.setString(4, movie.getPicturePath());   
        stmt.setInt(5, movie.getReleaseYear());       
        stmt.setString(6, movie.getLink());           
        stmt.setInt(7, genreId);                      
        stmt.setInt(8, directorId);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
       try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
     try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Movie movie = new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            rs.getInt(RELEASE_YEAR),
                            rs.getString(LINK),
                            new Genre(rs.getInt(ID_GENRE), null, null),
                            new Director(rs.getInt(ID_DIRECTOR), null, null)
                    );
                    return Optional.of(movie);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
       List<Movie> movies = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        rs.getInt(RELEASE_YEAR),
                        rs.getString(LINK),
                        new Genre(rs.getInt(ID_GENRE), null, null),
                        new Director(rs.getInt(ID_DIRECTOR), null, null)
                );
                movies.add(movie);
            }
        }
        return movies;
    }

    @Override
    public void addActorToMovie(int movieId, int actorId) throws Exception {
         try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(ADD_ACTOR_TO_MOVIE)) {

            stmt.setInt(1, movieId);
            stmt.setInt(2, actorId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeActorFromMovie(int movieId, int actorId) throws Exception {
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(REMOVE_ACTOR_FROM_MOVIE)) {

            stmt.setInt(1, movieId);
            stmt.setInt(2, actorId);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Integer> getMovieActorIds(int movieId) throws Exception {
        List<Integer> ids = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_MOVIE_ACTORS)) {

            stmt.setInt(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt("IDActor"));
                }
            }
        }
        return ids;
    
    }
    @Override
    public void updateMovieGenre(int movieId, int genreId) throws Exception {
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(UPDATE_MOVIE_GENRE)) {
        stmt.setInt(1, movieId);
        stmt.setInt(2, genreId);
        stmt.executeUpdate();
    }
    

}
    @Override
public void updateMovieDirector(int movieId, int directorId) throws Exception {
    try (Connection con = dataSource.getConnection();
         CallableStatement stmt = con.prepareCall(UPDATE_MOVIE_DIRECTOR)) {
        stmt.setInt(1, movieId);
        stmt.setInt(2, directorId);
        stmt.executeUpdate();
    }
}

    
}
