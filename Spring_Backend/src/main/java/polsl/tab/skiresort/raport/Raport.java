package polsl.tab.skiresort.raport;

import polsl.tab.skiresort.user.User;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "raports")
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idRaport;

    private String name;

    private Date raportDate;

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
