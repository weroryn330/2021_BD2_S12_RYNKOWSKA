package polsl.tab.skiresort.user;

import polsl.tab.skiresort.invoice.Invoice;
import polsl.tab.skiresort.role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
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

    @OneToOne
    @JoinColumn(name = "roles_id_role")
    @NotNull
    private Role rolesIdRole;

    @NotBlank(message = "Your user password should not be empty!")
    private String password;

    @OneToMany(mappedBy = "userIdUser")
    private List<Invoice> invoiceList;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
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

    public Role getRolesIdRole() {
        return rolesIdRole;
    }

    public void setRolesIdRole(Role rolesIdRole) {
        this.rolesIdRole = rolesIdRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
