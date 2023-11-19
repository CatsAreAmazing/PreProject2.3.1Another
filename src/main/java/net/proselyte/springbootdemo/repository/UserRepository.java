package net.proselyte.springbootdemo.repository;

import net.proselyte.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//я так понял, DAO нам теперь не нужны?)
public interface UserRepository extends JpaRepository<User, Integer> {
}
