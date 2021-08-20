package jpa.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter

public class User {
    @Id
    @Column(nullable = false)
    @TableGenerator(name = "table_generate_id", table = "generate_id", pkColumnValue = "userId",
            initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generate_id")
    private Long id;

    @Size(min=5, max=32)
    @Column(nullable = false, length = 32)
    private String login;

    @Email
    @Column(nullable = false)
    private String email;

    @Size(min=5, max=32)
    @Column(nullable = false, length = 32)
    private String password;



    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

}
