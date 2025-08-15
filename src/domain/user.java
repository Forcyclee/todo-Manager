package domain;

import java.time.LocalDate;
import java.util.UUID;

public class user {
    private String username;
    private String passwordHash;
    private byte[] salt;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String userID;

    public user(String username, String passwordHash, byte[] salt, String email, String firstName, String lastName, LocalDate birthDate) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.userID = UUID.randomUUID().toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public byte[] getSalt() {
        return salt;
    }
    public String getUserID() {
        return userID;
    }

}
