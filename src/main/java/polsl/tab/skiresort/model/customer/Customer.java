package polsl.tab.skiresort.model.customer;

import polsl.tab.skiresort.model.employee.Employee;
import polsl.tab.skiresort.model.invoice.Invoice;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customers")
public class Customer {

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

    @NotBlank(message = "Your password should not be empty!")
    private String password;

    @ManyToOne
    @JoinColumn(name = "support_employee")
    private Employee supportEmployee;

    @OneToMany(mappedBy = "idCustomer")
    private List<Invoice> invoiceList;

    // Immutable class - do not change :)
    private Customer() {}

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public static final class CustomerBuilder {
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String voivodeship;
        private String country;
        private String postalCode;
        private String phone;
        private String email;
        private String password;
        private Employee supportEmployee;
        private List<Invoice> invoiceList;

        public CustomerBuilder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder setLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

       public CustomerBuilder setAddress(final String address) {
            this.address = address;
            return this;
        }

        public CustomerBuilder setCity(final String city) {
            this.city = city;
            return this;
        }

        public CustomerBuilder setVoivodeship(final String voivodeship) {
            this.voivodeship = voivodeship;
            return this;
        }

        public CustomerBuilder setCountry(final String country) {
            this.country = country;
            return this;
        }

        public CustomerBuilder setPostalCode(final String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public CustomerBuilder setPhone(final String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder setSupportEmployee(final Employee supportEmployee) {
            this.supportEmployee = supportEmployee;
            return this;
        }

        public CustomerBuilder setInvoiceList(final List<Invoice> invoiceList) {
            this.invoiceList = invoiceList;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.firstName = this.firstName;
            customer.lastName = this.lastName;
            customer.address = this.address;
            customer.city = this.city;
            customer.voivodeship = this.voivodeship;
            customer.country = this.country;
            customer.postalCode = this.postalCode;
            customer.phone = this.phone;
            customer.email = this.email;
            customer.password = this.password;
            customer.supportEmployee = this.supportEmployee;
            customer.invoiceList = this.invoiceList;
            return customer;
        }
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Employee getSupportEmployee() {
        return supportEmployee;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }
}
