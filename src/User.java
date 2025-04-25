public class User {
    private String username;
    private String password;
    private AccessLevel accessLevel;
    private Employee employee;


    public User (String Username, String password, AccessLevel accessLevel, Employee employee) {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.employee = employee;
    }

    public AccessLevel getAccessLevel () {
        return accessLevel;

    }
    public Employee getEmployee () {
        return employee;
    }

    public boolean hasPermission(String action) {
        switch (accessLevel) {
            case Admin:
                return true;
            case Manager:
                return !action.equals("delete_employee");
            case Developer:
            case Designer:
                return action.equals("view_tasks");
            default:
                return false;
        }
    }
}


