package polsl.tab.skiresort.role;

import polsl.tab.skiresort.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idRole;

    @NotBlank(message = "Your role name should not be empty!")
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
