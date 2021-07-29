package homework12.entities;

public class Accounts {
    private int id;
    private int client_id;
    private String number;
    private double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", number='" + number + '\'' +
                ", value=" + value +
                '}';
    }
}
