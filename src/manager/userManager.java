package manager;

import domain.user;
import utils.passwordUtil;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class userManager {
    private List<user> users = new ArrayList<user>();

    /**
     *  Function used to register a new user
     * @param username users username
     * @param password users password
     * @param email users email
     * @param firstName users first name
     * @param lastName users last name
     * @param birthDate users birthday
     * @return 3 if the user is already logged in an account, 1 if the username is already in use, 0 if the user is created successfully
     * @throws Exception
     */
    public int register(String username, String password, String email, String firstName, String lastName, LocalDate birthDate) throws Exception {
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() != null){
            System.out.println("Logout from current account first");
            return 3; //user already logged in an account
        }
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

    /**
     * Function used to log in as a user
     * @param username users username
     * @param password users password
     * @return 3 if the user is already logged in an account, 2 if the username isn't on the database, 1 if the password is wrong and 0 if login successful.
     * @throws Exception
     */
    public int login(String username, String password) throws Exception {
        sessionManager temp = sessionManager.getInstance();
        if(temp.getCurrentUser() != null){
            System.out.println("Already logged in");
            return 3; //user already logged in an account
        }

        user tempUserVerify = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (tempUserVerify == null) {
            System.out.println("User not found");
            return 2;
        }

        user tempUser = users.stream().filter(user -> user.getUsername().equals(username)).findFirst().get();


        passwordUtil passwordGenerator = new passwordUtil();

        boolean correctInfo = passwordGenerator.verifyPassword(password, tempUser.getPasswordHash(), tempUser.getSalt());
        if(correctInfo){
            System.out.println("Succesfully logged in");
            temp.login(tempUser);
            return 0; //Successful login
        }
        System.out.println("Wrong password");
        return 1;


    }
    public int logout(){
        sessionManager temp = sessionManager.getInstance();
        if (temp.getCurrentUser() != null) {
            System.out.println("Logout successful ");
            temp.logout();
            return 0;
        } else {
            System.out.println("No user is currently logged in.");
            return 1;
        }
    }


}
