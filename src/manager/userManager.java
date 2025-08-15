package manager;

import domain.user;
import utils.passwordUtil;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class userManager {
    private List<user> users = new ArrayList<user>();

    /**
     *
     * @param username users username
     * @param password users password
     * @param email users email
     * @param firstName users first name
     * @param lastName users last name
     * @param birthDate users birthday
     * @return 1 if the username is already in use, 0 if the user is created successfully
     * @throws Exception
     */
    public int register(String username, String password, String email, String firstName, String lastName, LocalDate birthDate) throws Exception {
        if(users.stream().anyMatch(user -> user.getUsername().equals(username))){
            return 1; //User already exists

        }
        passwordUtil passwordGenerator = new passwordUtil();
        byte[] salt = passwordGenerator.generateSalt();
        String hashedPassword = passwordGenerator.hashPassword(password, salt);
        users.add(new user(username, hashedPassword, salt, email, firstName, lastName, birthDate ));
        System.out.println(salt);
        System.out.println(hashedPassword);
        return 0; //success

    }



}
