package homework12.entities;

public class ClientStatus {
    private String name;
    private String email;
    private String alias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "ClientStatus{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
