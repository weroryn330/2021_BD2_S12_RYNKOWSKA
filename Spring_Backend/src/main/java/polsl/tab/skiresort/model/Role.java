package polsl.tab.skiresort.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer idRole;

    @NotBlank(message = "Your role name should not be empty!")
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    @JsonIgnore
    private List<User> userList;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIdRole() {
        return idRole;
    }

    void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        if (this.userList == null) {
            this.userList = new ArrayList<>();
        }
        this.userList.add(user);
        user.getRoleList().add(this);
    }

    public void removeUser(User user) {
        if (this.userList == null) {
            this.userList = new ArrayList<>();
        }
        this.userList.remove(user);
        user.getRoleList().remove(this);
    }
}
