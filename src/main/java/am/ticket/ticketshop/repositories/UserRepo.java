package am.ticket.ticketshop.repositories;

import am.ticket.ticketshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}
