package app.domain_entities;

/**
 * Created by Tyler Von Moll on 6/3/16.
 *
 * Description: Add users here.
 */
public enum User {

    NORMAL_USER("test_user1", "goodPassword"),
    BAD_PASSWORD_USER("test_user1", "jibberish1234");

    private final String emailAddy;
    private final String password;

    User(String userName, String password) {
        this.emailAddy = userName + "@yourdomain.com";
        this.password = password;
    }

    public String getEmailAddy() {
        return emailAddy;
    }

    public String getPassword() {
        return password;
    }
}
