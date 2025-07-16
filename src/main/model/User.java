package src.main.model;

public class User {
    private String ci;
    private String user;
    private String userType;
    private String wallet;

    public User(String ci, String user, String userType, String wallet) {
        this.ci = ci;
        this.user = user;
        this.userType = userType;
        this.wallet = wallet;
    }

    public String getCi () {
        return this.ci;
    }
    public String getUser () {
        return this.user;
    }
    public String getUserType () {
        return this.userType;
    }
    public String getWallet () {
        return this.wallet;
    }

}
