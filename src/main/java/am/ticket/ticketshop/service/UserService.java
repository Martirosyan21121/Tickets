package am.ticket.ticketshop.service;

import am.ticket.ticketshop.model.User;
import am.ticket.ticketshop.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Optional<User> findByEmail(String email){
        return userRepo.findUserByEmail(email);
    }

}
