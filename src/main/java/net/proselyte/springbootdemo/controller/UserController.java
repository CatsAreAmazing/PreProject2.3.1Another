package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newUser", new User());
        return "users";
    }
    //7. Используйте ReqestParam аннотацию, использование аннотации PathVariable запрещено
    //Кажется, в этот раз ничего не забыл.

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    //Put просто ради того чтобы оба маппинга были привязаны к настройке, которую пришлось изменить,
    //чтобы они работали, или есть какая-то причина использовать конкретно для этого варианта обновления
    //именно put, а не patch?
    @PutMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") int id) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
