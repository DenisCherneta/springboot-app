package spring.pro.springboot.jdbc.dao.interfaces.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.pro.springboot.jdbc.dao.entities.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

//    @Query(value = "Select c from customer as c where c.username = :username and c.password = :password")
//    Optional<Customer> login(@Param("username")String username, @Param("password")String password);
    Optional<Customer> findByUsernameAndPassword(String username, String password);
    Optional<Customer> findByToken (String token);
}
