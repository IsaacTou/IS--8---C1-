package src.main.model;

public class SesionUser {
    private static SesionUser instance;
    private User sesionUser;

    private SesionUser() {} 

    public static SesionUser getInstance() {
        if (instance == null) {
            instance = new SesionUser();
        }
        return instance;
    }

    public void setSesionUser(User user) {
        this.sesionUser = user;
    }

    public User getUser() {
        return sesionUser;
    }

    public void logout() {
        this.sesionUser = null;
    }

}
