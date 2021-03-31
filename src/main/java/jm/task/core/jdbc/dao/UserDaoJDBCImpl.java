package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException{
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement();){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS preproject113" +
                    " (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    " name varchar(127)," +
                    " lastName varchar(127)," +
                    " age INT)");
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Table has been created");
        } catch (SQLException e) {

            System.out.println("Table creating error: "+ e.getMessage());
        }
    }

    public void dropUsersTable() throws SQLException{
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement();){
            statement.executeUpdate("DROP TABLE IF EXISTS preproject113");
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Table has been deleted");
        } catch (SQLException e) {
            System.out.println("Drop table error: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException{
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO preproject113 (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1,name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User: " + name + " has been added");
        } catch (SQLException | NullPointerException e)  {
            System.out.println("Add user error: " + e.getMessage());
        }
    }

    public void removeUserById(long id) throws SQLException{
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM preproject113 WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User id: " + id + " has been deleted");
        } catch (SQLException e) {
            System.out.println("User delete error: " + e.getMessage());
        }

    }

    public List<User> getAllUsers() throws SQLException{
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM preproject113");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                connection.setAutoCommit(false);
                connection.commit();
                System.out.println(user);
            }
        } catch (SQLException e) {
                System.out.println("Getting all users eror: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException{
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("DELETE FROM preproject113");
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Table has been dropped");
        } catch (SQLException e) {
            System.out.println("Table drop error: " + e.getMessage());
        }
    }
}
