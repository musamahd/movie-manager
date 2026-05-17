/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author daniel.bele
 */
public class MovieTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {
        "Id", "Title", "Release Year", "Genre", "Director", "Link"
    };

    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movies == null ? 0 : movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        switch (columnIndex) {
            case 0: return movie.getId();
            case 1: return movie.getTitle();
            case 2: return movie.getReleaseYear();
            case 3: return movie.getGenre() != null ? movie.getGenre().getName() : "-";
            case 4: return movie.getDirector() != null ? movie.getDirector().getLastName() : "-";
            case 5: return movie.getLink();
            default: throw new IllegalArgumentException("Invalid column index");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 2 -> Integer.class;
            default -> String.class;
        };
    }
    
}