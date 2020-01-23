package spring.pro.springboot.jdbc.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.pro.springboot.jdbc.dao.interfaces.repositories.CustomerRepository;
import spring.pro.springboot.jdbc.dao.entities.Customer;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Repository
public class CustomerDao {
    CustomerRepository repository;

    @Transactional
    public String login(String name, String password){
        Optional<Customer> customer = repository.findByUsernameAndPassword(name, password);
        if (customer.isPresent()){
            String token = UUID.randomUUID().toString();
            customer.get().setToken(token);
            repository.save(customer.get());
            return token;
        }
        return "";
    }

    @Transactional(readOnly = true)
    public Optional<User> findByToken(String token){
        Optional<Customer> result = repository.findByToken(token);
        if (result.isPresent()){
            Customer customer = result.get();
            User user = new User(customer.getUsername(), customer.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Autowired
    public void setRepository(CustomerRepository repository) {
        this.repository = repository;
    }
}
