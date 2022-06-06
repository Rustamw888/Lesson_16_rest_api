package testData;

public enum EndpointsData {

    LIST_USER_POINT("api/users?page=2"),
    CREATE_POINT("api/users"),
    SINGLE_USER("api/users/2"),
    WRONG_EMAIL("sydney@fife"),
    REGISTER_POINT("api/register");

    private String title;

    EndpointsData() {
    }

    EndpointsData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
