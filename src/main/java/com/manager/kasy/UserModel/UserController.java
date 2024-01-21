package com.manager.kasy.UserModel;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class UserController {

    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);


        return "users";

    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser (User user, RedirectAttributes attributes){
      service.save(user);
      attributes.addFlashAttribute("message", "The user has been saved successfully");
      return  "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes){
        try {

           User user =   service.get(id);
           model.addAttribute("user", user);
           model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
           return "user_form";
        } catch (UserNotFoundException e ){
           // e.printStackTrace();
            attributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";

        }
     }


    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes attributes){
        try {

            service.delete(id);
            attributes.addFlashAttribute("message","The user ID " + id + "has been deleted successfully");
        } catch (UserNotFoundException e ){
            // e.printStackTrace();
            attributes.addFlashAttribute("message", e.getMessage());


        }
        return "redirect:/users";
    }
}
