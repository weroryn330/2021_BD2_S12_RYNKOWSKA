package polsl.tab.skiresort.api.passes.request;

import java.sql.Date;
import java.sql.Timestamp;

public class PassRequest {

    private Float unitPrice;

    private Timestamp startDate; // If quantity pass - send null

    private Timestamp endDate;   // If quantity pass - send null

    private String firstName;

    private String lastName;

    private Date birthDate;

    private Integer usesTotal;  // If time pass - send null

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getUsesTotal() {
        return usesTotal;
    }

    public void setUsesTotal(Integer usesTotal) {
        this.usesTotal = usesTotal;
    }
}
