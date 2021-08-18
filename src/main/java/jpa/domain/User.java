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
    @TableGenerator(name = "name_table_generate",
            table = "table_generate_id",
            valueColumnName = "col_value",
            pkColumnName = "col_id",
            pkColumnValue = "nextUserId",
            initialValue = 0,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "name_table_generate")
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
