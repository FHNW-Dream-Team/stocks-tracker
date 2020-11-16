package fhnw.dreamteam.stockstracker.data.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class User {
    public User() { }

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(unique=true)
    @NotEmpty(message = "Please enter a username.")
    private String username;

    @Getter
    @Setter
    @NotEmpty(message = "Please provide your firstname.")
    private String firstname;

    @Getter
    @Setter
    @NotEmpty(message = "Please provide your lastname.")
    private String lastname;

    @Getter
    @Setter
    @Email(message = "Please provide a valid e-mail.")
    @Column(unique=true)
    @NotEmpty(message = "Please provide an e-mail.")
    private String email;

    @Getter
    @Setter
    @NotEmpty(message = "Please provide a mobile number.")
    private String mobile;

    @Setter
    @org.springframework.data.annotation.Transient //will not be serialized
    private String password;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Stock> stocks;

    public User(@NotEmpty(message = "Please enter a username.") String username, @NotEmpty(message = "Please provide your firstname.") String firstname, @NotEmpty(message = "Please provide your lastname.") String lastname, @Email(message = "Please provide a valid e-mail.") @NotEmpty(message = "Please provide an e-mail.") String email, @NotEmpty(message = "Please provide a mobile number.") String mobile, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }


    public String getPassword() {
        String transientPassword = this.password;
        this.password = null;
        return transientPassword;
    }
}