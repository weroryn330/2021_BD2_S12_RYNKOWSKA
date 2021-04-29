package polsl.tab.skiresort.user;

import polsl.tab.skiresort.invoice.Invoice;
import polsl.tab.skiresort.raport.Raport;
import polsl.tab.skiresort.role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idCustomer;

    @NotBlank(message = "Your first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your last name should not be empty!")
    private String lastName;

    private String address;

    private String city;

    private String voivodeship;

    private String country;

    private String postalCode;

    @NotBlank(message = "Your phone information should not be empty!")
    private String phone;

    @NotBlank(message = "Your email should not be empty!")
    private String email;

    @OneToOne()
    @JoinColumn(name = "roles_id_role")
    private Role rolesIdRole;

    @NotBlank(message = "Your password should not be empty!")
    private String password;

    @OneToMany(mappedBy = "usersIdCustomer")
    private List<Invoice> invoiceList;

    //Tu nie ma błędu, po mappedBy tak samo sie nazywają
    @OneToMany(mappedBy = "usersIdCustomer")
    private List<Raport> raportList;

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
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

    public List<Raport> getRaportList() {
        return raportList;
    }

    public void setRaportList(List<Raport> raportList) {
        this.raportList = raportList;
    }
}
