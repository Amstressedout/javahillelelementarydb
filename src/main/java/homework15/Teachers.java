package homework15;

public class Teachers {
    private String name;

    public Teachers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "name='" + name + '\'' +
                '}';
    }
}
