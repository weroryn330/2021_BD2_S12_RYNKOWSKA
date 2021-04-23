package polsl.tab.skiresort.model.employee;

import polsl.tab.skiresort.model.customer.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idEmployee;

    @NotBlank(message = "Your first name should not be empty!")
    private String firstName;

    @NotBlank(message = "Your last name should not be empty!")
    private String lastName;

    private String title;

    private Date birthDate;

    private Date hireDate;

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
    @JoinColumn(name = "reports_to")
    private Employee reportsTo;

    @OneToMany(mappedBy = "reportsTo")
    private Collection<Employee> children;

    @OneToMany(mappedBy = "supportEmployee")
    private List<Customer> customerList;

    private Employee() {}

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static final class EmployeeBuilder {
        private String firstName;
        private String lastName;
        private String title;
        private Date birthDate;
        private Date hireDate;
        private String address;
        private String city;
        private String voivodeship;
        private String country;
        private String postalCode;
        private String phone;
        private String email;
        private String password;
        private Employee reportsTo;
        private Collection<Employee> children;
        private List<Customer> customerList;

        public EmployeeBuilder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder setLastName(final String lastName) {
            this.lastName = lastName;
            return this;

        }

        public EmployeeBuilder setTitle(final String title) {
            this.title = title;
            return this;
        }

        public EmployeeBuilder setBirthDate(final Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public EmployeeBuilder setHireDate(final Date hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public EmployeeBuilder setAddress(final String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder setCity(final String city) {
            this.city = city;
            return this;
        }

        public EmployeeBuilder  setVoivodeship(final String voivodeship) {
           this.voivodeship = voivodeship;
           return this;
        }

        public EmployeeBuilder setCountry(final String country) {
            this.country = country;
            return this;
        }

        public EmployeeBuilder setPostalCode(final String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public EmployeeBuilder setPhone(final String phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public EmployeeBuilder setReportsTo(final Employee reportsTo) {
            this.reportsTo = reportsTo;
            return this;
        }

        public EmployeeBuilder setChildren(final Collection<Employee> children) {
            this.children = children;
            return this;
        }

        public EmployeeBuilder setCustomerList(final List<Customer> customerList) {
            this.customerList = customerList;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.address = this.address;
            employee.birthDate = this.birthDate;
            employee.children = this.children;
            employee.city = this.city;
            employee.country = this.country;
            employee.customerList = this.customerList;
            employee.email = this.email;
            employee.firstName = this.firstName;
            employee.lastName = this.lastName;
            employee.title = this.title;
            employee.hireDate = this.hireDate;
            employee.password = this.password;
            employee.voivodeship = this.voivodeship;
            employee.reportsTo = this.reportsTo;
            employee.postalCode = this.postalCode;
            employee.phone = this.phone;
            return employee;
        }
    }



    public Integer getIdEmployee() {
        return idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getHireDate() {
        return hireDate;
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

    public Employee getReportsTo() {
        return reportsTo;
    }

    public Collection<Employee> getChildren() {
        return children;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public String getPassword() {
        return password;
    }
}
