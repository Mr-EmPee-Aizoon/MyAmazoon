package tk.empee.myamazoon.model.dto.users;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter @Setter
    private long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Role role;

    public User(String name, String surname, Role role, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public User(long id) {
        this.id = id;
    }

}
