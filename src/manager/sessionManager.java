package manager;

import domain.user;

public class sessionManager {
    private static  sessionManager instance;
    private user currentUser;
    private sessionManager() {
    }
    public static sessionManager getInstance() {
        if (instance == null) {
            instance = new sessionManager();
        }
        return instance;
    }

    public void login(user user){
        currentUser = user;
    }

    public void logout(){
        currentUser = null;
    }

    public user getCurrentUser(){
        return currentUser;
    }

}
