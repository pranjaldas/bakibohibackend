package click.pranjalonline.bakibohibackend.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users",
    uniqueConstraints = {
            @UniqueConstraint(name = "uk_users_email",columnNames = {"email"}),
            @UniqueConstraint(name = "uk_users_phone",columnNames = {"phone"}),
            @UniqueConstraint(name = "uk_users_username",columnNames = {"username"})
    }
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore //THIS ANNOTATION HELP TO NOT TO FETCH PASSWORD, STOP DESERIALIZING PASSWORD TO OBJECT ATTRIBUTE
    private String password;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private long phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roles;
}
