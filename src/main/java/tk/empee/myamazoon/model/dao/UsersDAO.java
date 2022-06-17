package tk.empee.myamazoon.model.dao;

import tk.empee.myamazoon.model.dto.users.Role;
import tk.empee.myamazoon.model.dto.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class UsersDAO {

    public static List<User> findUsers() throws SQLException {

        try(DBConnection connection = new DBConnection()) {
            return connection.execute( s -> {
                List<User> users = new ArrayList<>();
                ResultSet rs = s.executeQuery("SELECT * FROM users");

                while(rs.next()) {
                    users.add( parseUser(rs) );
                }

                return Collections.unmodifiableList(users);
            });
        }
    }

    public static Optional<User> findUserByCredentials(String username, String password) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("SELECT * FROM users WHERE username = ? AND password = ?");

            return connection.executePrepared(s -> {
                s.setString(1, username);
                s.setString(2, password);

                ResultSet rs = s.executeQuery();
                if (rs.next()) {
                    return Optional.of( parseUser(rs) );
                }

                return Optional.empty();
            });
        }
    }

    public static Optional<User> findUserByID(long id) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("SELECT * FROM users WHERE id = ?");
            return connection.executePrepared( s -> {
                s.setLong(1, id);
                ResultSet rs = s.executeQuery();

                if(rs.next()) {
                    return Optional.of(parseUser(rs));
                }

                return Optional.empty();
            });
        }
    }

    public static boolean updateUser(User user) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("UPDATE users SET username = ?, name = ?, surname=?, password = ?, role = ? WHERE id = ?");
            return connection.executePrepared( s -> {
                s.setString(1, user.getUsername());
                s.setString(2, user.getName());
                s.setString(3, user.getSurname());
                s.setString(4, user.getPassword());
                s.setString(5, user.getRole().name());
                s.setLong(6, user.getId());

                return s.executeUpdate() > 0;
            });
        }
    }

    public static synchronized long saveUser(User user) throws SQLException {

        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("INSERT INTO users (username,name,surname, password, role) VALUES (?,?,?,?,?)");
            connection.executePrepared( s -> {
                s.setString(1, user.getUsername());
                s.setString(2, user.getName());
                s.setString(3, user.getSurname());
                s.setString(4, user.getPassword());
                s.setString(5, user.getRole().name());
                s.executeUpdate();
            });

            return connection.execute( s -> {
                ResultSet rs = s.executeQuery("select max(id) as max from users");
                rs.next();

                return rs.getLong("max");
            });
        }

    }

    public static int deleteUser(long id) throws SQLException {
        try(DBConnection connection = new DBConnection()) {
            connection.setPreparedStatement("DELETE FROM users WHERE id = ?");
            return connection.executePrepared( s -> {
                s.setDouble(1, id);
                return s.executeUpdate();
            });
        }
    }

    private static User parseUser(ResultSet rs) throws SQLException {
        User user = new User(rs.getLong("id"));

        user.setUsername(rs.getString("username"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setPassword(rs.getString("password"));
        user.setRole(
                Role.valueOf(rs.getString("role"))
        );

        return user;
    }

}
