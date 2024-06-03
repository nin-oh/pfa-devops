package org.xproce.revormclass.service.inf;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.user.entities.UserModel;

import org.xproce.revormclass.user.repositories.Userrepos;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {


    private PasswordEncoder passwordEncoder;
    private Userrepos userRepository;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void save(UserModel user) {
        userRepository.save(user);
    }

    @PostConstruct
    public void postconstrust(){
        UserModel user = new UserModel();
        user.setRole("admin");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("cc"));
        user.setEmail("cc@cc.com");
        userRepository.save(user);
    }
    public void register(UserModel user) {
        user.setRole("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public boolean deleteUser(UserModel userModel) {
        try {
            userRepository.delete(userModel);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
    public UserModel updateUser(UserModel userModel) {

        return userRepository.save(userModel);

    }

    public UserModel getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
