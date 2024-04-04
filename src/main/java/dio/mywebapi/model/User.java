package dio.mywebapi.model;

public class User {

    private Integer id;
    private String login;
    private String password;

    public User(){}

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public Integer getId(){
        return this.id;
    }

    public User setId(Integer id){
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString(){
        return "User{" +
                "login= '" + login + '\'' +
                "password= '" + password + '\'' +
                "}";
    }
}
