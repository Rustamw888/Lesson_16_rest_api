package network;

public enum EndpointsData {

    LIST_USER_POINT("api/users?page=2"),
    CREATE_POINT("api/users"),
    SINGLE_USER("api/users/2"),
    WRONG_EMAIL("sydney@fife"),
    REGISTER_POINT("api/register");

    public final String title;

    EndpointsData(String title) {
        this.title = title;
    }
}
