package spring.pro.springboot.jdbc.dao.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

//TODO: add spring audit
@Entity
@Table(name="customer", schema = "test")
public class Customer {

    private Long id;
    private String password;
    private String token;
    private String username;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "password", nullable = false, length = 49)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "token", nullable = false, length = 49)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "username", nullable = false, length = 49)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + id +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
