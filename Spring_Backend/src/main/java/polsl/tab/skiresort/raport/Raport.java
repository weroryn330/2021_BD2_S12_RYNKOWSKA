package polsl.tab.skiresort.raport;

import polsl.tab.skiresort.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;


@Entity
@Table(name = "raports")
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idRaport;

    @NotBlank(message = "Your raport name should not be empty!")
    private String name;

    @NotBlank(message = "Your raport date should not be empty!")
    private Date raportDate;

    @NotBlank(message = "Your raport path should not be empty!")
    private String path;

    @ManyToOne()
    @JoinColumn(name = "users_id_customer")
    private User usersIdCustomer;

    public Integer getIdRaport() {
        return idRaport;
    }

    public void setIdRaport(Integer idRaport) {
        this.idRaport = idRaport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRaportDate() {
        return raportDate;
    }

    public void setRaportDate(Date raportDate) {
        this.raportDate = raportDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUsersIdCustomer() {
        return usersIdCustomer;
    }

    public void setUsersIdCustomer(User usersIdCustomer) {
        this.usersIdCustomer = usersIdCustomer;
    }
}
