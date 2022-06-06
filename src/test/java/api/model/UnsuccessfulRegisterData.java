package api.model;

public class UnsuccessfulRegisterData {
    private String email;

    public UnsuccessfulRegisterData() {
    }

    public UnsuccessfulRegisterData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
