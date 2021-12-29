package KursovProektOOP2.models;

public class AgentModel {
    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    String phone;
    int dealAmount;
    double rating;

    public AgentModel(int id, String username, String firstName, String lastName, String email, String phone, int dealAmount, double rating) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dealAmount = dealAmount;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(int dealAmount) {
        this.dealAmount = dealAmount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
