package domain;

import java.time.LocalDate;

public class user {
    private String username;
    private String passwordHash;
    private byte[] salt;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public user(String username, String passwordHash, byte[] salt, String email, String firstName, String lastName, LocalDate birthDate) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
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

}
