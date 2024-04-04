package dio.mywebapi.repository;

import dio.mywebapi.exception.BusinessException;
import dio.mywebapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {

    public void save(User user) {

        if(Objects.isNull(user.getLogin()) || Objects.isNull(user.getPassword())){
            throw new BusinessException("Campos nulos");
        }

        if (Objects.isNull(user.getId()))
            System.out.println("Save - Receiving the user on the repository");
        else
            System.out.println("Update - Receiving the user on the repository");

        System.out.println(user);
    }

    public void deleteById(Integer id) {
        System.out.println(String.format("Delete/id - Receiving the id: %d to delete a user", id));
        System.out.println(id);
    }

    public List<User> findAll() {
        System.out.println("List - Listing all users");
        List<User> users = new ArrayList<>();
        users.add(new User().setLogin("Tassi").setPassword("password"));
        users.add(new User().setLogin("Hisae").setPassword("masterPass"));
        return users;
    }

    public User findById(Integer id) {
        System.out.println(String.format("Find/id - Receiving id: %d to look for the user", id));
        return new User("Tassi", "pass");
    }

    public User findByUsername(String username) {
        System.out.println(String.format("Find/username - Receiving username: %s to find a user", username));
        return new User("Tassi", "pass");
    }
}
