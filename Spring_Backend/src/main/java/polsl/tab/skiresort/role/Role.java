package polsl.tab.skiresort.role;

import org.hibernate.annotations.GeneratorType;
import polsl.tab.skiresort.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idRole;


    @OneToOne(mappedBy = "rolesIdRole")
    @NotBlank(message = "Your role should not be empty!")
    private User roleName;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public User getRoleName() {
        return roleName;
    }

    public void setRoleName(User roleName) {
        this.roleName = roleName;
    }
}
