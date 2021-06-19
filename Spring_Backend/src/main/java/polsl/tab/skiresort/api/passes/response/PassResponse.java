package polsl.tab.skiresort.api.passes.response;

import polsl.tab.skiresort.model.Pass;

import java.sql.Date;
import java.sql.Timestamp;

public class PassResponse {

    private final Integer id;

    private final Float unitPrice;

    private final Timestamp startDate;

    private final Timestamp endDate;

    private final String firstName;

    private final String lastName;

    private final Date birthDate;

    private final Integer usesTotal;

    private final Integer usesLeft;

    private final Boolean blocked;

    public PassResponse(Pass pass) {
        this.id = pass.getIdPass();
        this.unitPrice = pass.getUnitPrice();
        this.startDate = pass.getStartDate();
        this.endDate = pass.getEndDate();
        this.firstName = pass.getFirstName();
        this.lastName = pass.getLastName();
        this.birthDate = pass.getBirthDate();
        this.usesTotal = pass.getUsesTotal();
        this.usesLeft = pass.getUsesLeft();
        this.blocked = pass.getBlocked();
    }

    public Integer getId() {
        return id;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Integer getUsesTotal() {
        return usesTotal;
    }

    public Integer getUsesLeft() {
        return usesLeft;
    }

    public Boolean getBlocked() {
        return blocked;
    }
}
