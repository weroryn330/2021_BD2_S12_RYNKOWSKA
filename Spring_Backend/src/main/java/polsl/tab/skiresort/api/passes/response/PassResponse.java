package polsl.tab.skiresort.api.passes.response;

import polsl.tab.skiresort.model.Pass;

import java.sql.Date;

public class PassResponse {

    private final Integer idPass;

    private final Float unitPrice;

    private final Date startDate;

    private final Date endDate;

    private final String firstName;

    private final String lastName;

    private final Date birthDate;

    private final Integer usesTotal;

    private final Integer usesLeft;

    public PassResponse(Pass pass) {
        this.idPass = pass.getIdPass();
        this.unitPrice = pass.getUnitPrice();
        this.startDate = pass.getStartDate();
        this.endDate = pass.getEndDate();
        this.firstName = pass.getFirstName();
        this.lastName = pass.getLastName();
        this.birthDate = pass.getBirthDate();
        this.usesTotal = pass.getUsesTotal();
        this.usesLeft = pass.getUsesLeft();
    }

    public Integer getIdPass() {
        return idPass;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
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
}
