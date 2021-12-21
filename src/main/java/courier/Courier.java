package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    public final String login;
    public final String password;
    public final String firstname;

    public Courier(String login, String password, String firstname){
        this.login=login;
        this.password=password;
        this.firstname=firstname;
    }

    public static String getRandomString(){
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static Courier getRandomCourier(){
        return new Courier(getRandomString(), getRandomString(), getRandomString());
    }

    public static Courier getRandomCourierWithoutPassword(){
        return new Courier(getRandomString(),null, getRandomString());
    }

    public static Courier getRandomCourierWithoutLogin(){
        return new Courier(null, getRandomString(), getRandomString());
    }

    public static Courier getRandomCourierWithoutFirstName(){
        return new Courier(getRandomString(), getRandomString(),null);
    }

}
