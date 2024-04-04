package dio.mywebapi.controller;

import dio.mywebapi.model.User;
import dio.mywebapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("{username}")
    public User getUser(@PathVariable("username") String username){
        return userRepository.findByUsername(username);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }

    @PostMapping()
    public void saveUser(@RequestBody User user){
        userRepository.save(user);
    }

    @PutMapping()
    public void updateUser(@RequestBody User user){
        userRepository.save(user);
    }
}
