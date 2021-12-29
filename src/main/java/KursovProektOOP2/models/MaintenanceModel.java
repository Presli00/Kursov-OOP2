package KursovProektOOP2.models;

public class MaintenanceModel {
int id;
String maintenanceName;
String worksIn;
String City;
String isEmployeed;

    public MaintenanceModel(int id, String maintenanceName, String worksIn, String city, String isEmployeed) {
        this.id = id;
        this.maintenanceName = maintenanceName;
        this.worksIn = worksIn;
        City = city;
        this.isEmployeed = isEmployeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaintenanceName() {
        return maintenanceName;
    }

    public void setMaintenanceName(String maintenanceName) {
        this.maintenanceName = maintenanceName;
    }

    public String getWorksIn() {
        return worksIn;
    }

    public void setWorksIn(String worksIn) {
        this.worksIn = worksIn;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getIsEmployeed() {
        return isEmployeed;
    }

    public void setIsEmployeed(String isEmployeed) {
        this.isEmployeed = isEmployeed;
    }
}
