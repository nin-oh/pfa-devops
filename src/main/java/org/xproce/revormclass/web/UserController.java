package org.xproce.revormclass.web;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xproce.revormclass.dao.entities.Category;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.service.ProductManager;
import org.xproce.revormclass.service.inf.UserService;
import org.xproce.revormclass.user.entities.UserModel;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/users")
    public String userList(Model model) {
        List<UserModel> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "listusers";
    }

    @GetMapping("/admin/user/Update/{id}")
    public String admineditUser(@PathVariable Long id, Model model) {
        UserModel user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "adminedituser";
    }

    @GetMapping("/user/Update/{id}")
    public String editUser(@PathVariable String id, Model model) {
        UserModel user = userService.getUserByUsername(id);
        model.addAttribute("user", user);
        return "edituser";
    }

    @PostMapping("user/Update")
    public String handleEditForm(@RequestParam("id") Long id,
                                 @RequestParam("firstName") String first,@RequestParam("lastName") String lastName,@RequestParam("email") String email,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,Model model) {

        UserModel updatedProduct = userService.getUserById(id);
        updatedProduct.setFirstName(first);
        updatedProduct.setLastName(lastName);
        updatedProduct.setEmail(email);
        updatedProduct.setUsername(username);
        updatedProduct.setPassword(passwordEncoder.encode(password));
        userService.updateUser(updatedProduct);
        return "redirect:/home";//return "redirect:/getProductsList"; // Redirect to the product list page after updating
    }
    @PostMapping("admin/user/Update")
    public String handleEditFormadmin(@RequestParam("id") Long id,
                                 @RequestParam("firstName") String first,@RequestParam("lastName") String lastName,@RequestParam("email") String email,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password,Model model) {

        UserModel updatedProduct = userService.getUserById(id);
        updatedProduct.setFirstName(first);
        updatedProduct.setLastName(lastName);
        updatedProduct.setEmail(email);
        updatedProduct.setUsername(username);
        updatedProduct.setPassword(passwordEncoder.encode(password));
        userService.updateUser(updatedProduct);
        return "redirect:/users";//return "redirect:/getProductsList"; // Redirect to the product list page after updating
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteProduct(@PathVariable("id") Long id,@ModelAttribute UserModel user) {
        userService.deleteUser(user);
        return "redirect:/users";
    }
}
