package polsl.tab.skiresort.model;

import polsl.tab.skiresort.api.entry.request.UserRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @NotBlank(message = "Your user first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your user last name should not be empty!")
    private String lastName;

    @NotBlank(message = "Your user address should not be empty!")
    private String address;

    @NotBlank(message = "Your user city should not be empty!")
    private String city;

    @NotBlank(message = "Your user voivodeship should not be empty!")
    private String voivodeship;

    @NotBlank(message = "Your user country should not be empty!")
    private String country;

    @NotBlank(message = "Your user postal code should not be empty!")
    private String postalCode;

    @NotBlank(message = "Your user phone should not be empty!")
    private String phone;

    @NotBlank(message = "Your user email should not be empty!")
    private String email;

    @NotBlank(message = "Your user password should not be empty!")
    private String password;

    @OneToMany(mappedBy = "userIdUser")
    private List<Invoice> invoiceList;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "users_id_user")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id_role")}
    )
    private List<Role> roleList;

    public User(String firstName, String lastName, String address, String city, String voivodeship, String country, String postalCode, String phone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.voivodeship = voivodeship;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String address, String city, String voivodeship, String country, String postalCode, String phone, String email, String password, List<Role> roleList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.voivodeship = voivodeship;
        this.country = country;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.roleList = roleList;
    }


    public User() {

    }

    public Integer getIdUser() {
        return idUser;
    }

    void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Invoice> getInvoiceList() {
        if (this.invoiceList == null) {
            this.invoiceList = new ArrayList<>();
        }
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addRole(Role role) {
        if (this.roleList == null) {
            this.roleList = new ArrayList<>();
        }
        this.roleList.add(role);
        if (role.getUserList() == null) {
            role.setUserList(new ArrayList<>());
        }
        role.getUserList().add(this);
    }

    public void removeRole(Role role) {
        if (this.roleList == null) {
            this.roleList = new ArrayList<>();
        }
        this.roleList.remove(role);
        if (role.getUserList() != null) {
            role.getUserList().remove(this);
        }
    }

    public static User editMapping(User currentUser, UserRequest body, Boolean hardEdit) {
        currentUser.setFirstName(body.getFirstName());
        currentUser.setLastName(body.getLastName());
        currentUser.setAddress(body.getAddress());
        currentUser.setCity(body.getCity());
        currentUser.setVoivodeship(body.getVoivodeship());
        currentUser.setCountry(body.getCountry());
        currentUser.setPostalCode(body.getPostalCode());
        currentUser.setPhone(body.getPhone());
        if (Boolean.TRUE.equals(hardEdit)) {
            currentUser.setEmail(body.getEmail());
        }
        return currentUser;
    }
}
