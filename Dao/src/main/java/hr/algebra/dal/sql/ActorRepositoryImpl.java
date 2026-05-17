/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.ActorRepository;
import hr.algebra.model.Actor;
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
public class ActorRepositoryImpl implements ActorRepository{

     
    private static final String CREATE_ACTOR = "{ CALL createActor (?, ?, ?) }";
    private static final String UPDATE_ACTOR = "{ CALL updateActor (?, ?, ?) }";
    private static final String DELETE_ACTOR = "{ CALL deleteActor (?) }";
    private static final String SELECT_ACTOR = "{ CALL selectActor (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";

    
    private static final String ID_ACTOR = "IDActor";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";

    private final DataSource dataSource = DataSourceSingleton.getInstance();
    
    @Override
    public int createActor(Actor actor) throws Exception {
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(3);
        }
    }

    @Override
    public void updateActor(int id, Actor actor) throws Exception {
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(UPDATE_ACTOR)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
    }
    

    @Override
    public void deleteActor(int id) throws Exception {
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(DELETE_ACTOR)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Actor> selectActor(int id) throws Exception {
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_ACTOR)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Actor> selectActors() throws Exception {
         List<Actor> actors = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(SELECT_ACTORS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                actors.add(new Actor(
                        rs.getInt(ID_ACTOR),
                        rs.getString(FIRST_NAME),
                        rs.getString(LAST_NAME)
                ));
            }
        }
        return actors;
    
    }
    
}
