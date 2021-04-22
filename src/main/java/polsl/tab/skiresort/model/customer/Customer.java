package polsl.tab.skiresort.model.customer;

import polsl.tab.skiresort.model.employee.Employee;
import polsl.tab.skiresort.model.invoice.Invoice;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idCustomer;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String voivodeship;

    private String country;

    private String postalCode;

    private String phone;

    private String email;

    @ManyToOne
    @JoinColumn(name = "support_employee")
    private Employee supportEmployee;

    @OneToMany(mappedBy = "idCustomer")
    private List<Invoice> invoiceList;

    public Customer(){};

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

//    public Employee getEmployee() {
//        return supportEmployee;
//    }
//
//    public void setEmployee(Employee supportEmployee) {
//        this.supportEmployee = supportEmployee;
//    }
}
