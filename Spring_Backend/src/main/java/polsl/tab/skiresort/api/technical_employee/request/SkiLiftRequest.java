package polsl.tab.skiresort.api.technical_employee.request;

public class SkiLiftRequest {

    private String name;

    private Integer height;

    private Character isOpened;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Character getIsOpened() {
        return isOpened;
    }

    public void setIsOpened(Character isOpened) {
        this.isOpened = isOpened;
    }
}
