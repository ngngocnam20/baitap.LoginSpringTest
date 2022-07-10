package com.baitap.loginspringtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository repository;

    @GetMapping("")
    public String viewHomePage()
    {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model)
    {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processingRegistration(User user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repository.save(user);
        return "register_success";
    }

    @PostMapping("/edit_user")
            public String editUser(User user)
    {
        return "";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model)
    {
        List<User> listUsers = repository.findAll();
        model.addAttribute("listUsers",listUsers );
        return "users";
    }

//    @GetMapping("/users/eidt/{id}")
//    public String showEditForm (@PathVariable("id") Integer id, Model model, RedirectAttributes ra)
//    {
//        try
//        {
//            User user = service.getId(id);
//            model.addAttribute("user", user);
//        } catch (UserNotFoundException e)
//        {
//            ra.addAttribute("message", "Cập nhật thành công.");
//            return "redirect:/users";
//        }
//    }

}
