package polsl.tab.skiresort.api.passes.request;

import java.sql.Date;

public class PassRequest {

    private Float unitPrice;

    private Date startDate; // If quantity pass - send null

    private Date endDate;   // If quantity pass - send null

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
