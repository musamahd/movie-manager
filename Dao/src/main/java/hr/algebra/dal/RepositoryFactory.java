/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel.bele
 */
public final class RepositoryFactory {

    private static final Properties properties = new Properties();
    private static final String PATH = "/config/repository.properties";
    

    

    static {
        try (InputStream is = RepositoryFactory.class.getResourceAsStream(PATH)) {
            properties.load(is);
        } catch (Exception ex) {
            Logger.getLogger(RepositoryFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static MovieRepository getMovieRepository() {
        return create(MovieRepository.class, "movieRepo");
    }

    public static ActorRepository getActorRepository() {
        return create(ActorRepository.class, "actorRepo");
    }

    public static DirectorRepository getDirectorRepository() {
        return create(DirectorRepository.class, "directorRepo");
    }

    public static GenreRepository getGenreRepository() {
        return create(GenreRepository.class, "genreRepo");
    }

    public static UserRepository getUserRepository() {
        return create(UserRepository.class, "userRepo");
    }

     @SuppressWarnings("unchecked")
    private static <T> T create(Class<T> type, String key) {
        try {
            return (T) Class.forName(properties.getProperty(key))
                .getDeclaredConstructor()
                .newInstance();
        } catch (Exception ex) {
            Logger.getLogger(RepositoryFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
