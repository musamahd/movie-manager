/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author musam
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id","title","genre","releaseYear","description","director","actors","link","picturePath"})
public final class Movie implements Comparable<Movie>{
    
   
    
    private int id;
    private String title;
    private String description;
    @XmlElement(name = "picturepath")
    private String picturePath;
    @XmlElement(name = "releaseyear")
    private int releaseYear;
    private String link;

    private Genre genre;
    private Director director;
    
    @XmlElementWrapper
    @XmlElement(name = "actor")
    
    private List<Actor> actors = new ArrayList<>();

    public Movie() {
    }

    public Movie(int id, String title, String description, String picturePath, int releaseYear, String link, Genre genre, Director director) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.picturePath = picturePath;
        this.releaseYear = releaseYear;
        this.link = link;
        this.genre = genre;
        this.director = director;
    }

    public Movie(String title, String description, String picturePath, int releaseYear, String link, Genre genre, Director director) {
        this.title = title;
        this.description = description;
        this.picturePath = picturePath;
        this.releaseYear = releaseYear;
        this.link = link;
        this.genre = genre;
        this.director = director;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        return this.id == other.id;
    }

    
    @Override
    public String toString() {
        return title + " (" + releaseYear + ")";
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.compareToIgnoreCase(o.title);
    }
    
    
        
}
