package polsl.tab.skiresort.api.employee.response;

import java.util.Date;

public class PassesResponse {

    private final Integer id;

    private final Float unitPrice;

    private final String invoiceOwnerEmail;

    private final Date startDate;

    private final Date endDate;

    private final String firstName;

    private final String lastName;

    private final Date birthDate;

    private final Integer usesTotal;

    private final Integer usesLeft;

    private final Boolean blocked;

    public PassesResponse(Integer id,
                          Float unitPrice,
                          String invoiceOwnerEmail,
                          Date startDate,
                          Date endDate,
                          String firstName,
                          String lastName,
                          Date birthDate,
                          Integer usesTotal,
                          Integer usesLeft,
                          Boolean blocked
    ) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.invoiceOwnerEmail = invoiceOwnerEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.usesTotal = usesTotal;
        this.usesLeft = usesLeft;
        this.blocked = blocked;
    }

    public Integer getId() {
        return id;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public String getInvoiceOwnerEmail() {
        return invoiceOwnerEmail;
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

    public Boolean getBlocked() {
        return blocked;
    }
}
