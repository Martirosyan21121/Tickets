package am.ticket.ticketshop.controller;

import am.ticket.ticketshop.model.User;
import am.ticket.ticketshop.repositories.UserRepo;
import am.ticket.ticketshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class TicketsController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    @GetMapping("/tickets")
    private String tickets() {
        return "tickets";
    }

    @PostMapping("/buy")
    private String buy(@ModelAttribute User user, Random random, ModelMap modelMap) {
        int token = random.nextInt(10000, 100000);
        user.setToken(token);
        List<User> users = userRepo.findAll();
        int sum = 0;
        for (User u : users) {
            sum += u.getTicketSize();
        }
        int count = sum + user.getTicketSize();
        System.out.println(count);
        int allTicket = 1000;
        if (allTicket <= count) {
            String message = "Sorry the tickets are over or little left !!!";
            modelMap.addAttribute("msg", message);
            return "tickets";
        } else if (userService.findByEmail(user.getEmail()).isPresent()) {
            Optional<User> optionalUser = userService.findByEmail(user.getEmail());
            modelMap.addAttribute("name", optionalUser.get().getName());
            modelMap.addAttribute("email", optionalUser.get().getEmail());
            modelMap.addAttribute("address", optionalUser.get().getAddress());
            modelMap.addAttribute("size", optionalUser.get().getTicketSize());
            modelMap.addAttribute("token", optionalUser.get().getToken());
            return "exists";
        }
        userRepo.save(user);
        modelMap.addAttribute("name", user.getName());
        modelMap.addAttribute("email", user.getEmail());
        modelMap.addAttribute("address", user.getAddress());
        modelMap.addAttribute("size", user.getTicketSize());
        modelMap.addAttribute("token", String.valueOf(user.getToken()));
        return "success";
    }
}

