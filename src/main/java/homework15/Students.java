package homework15;


public class Students {
    private String name;
    private String surname;
    private int age;
    private int weight;
    private String sex;

    public Students(String name, String surname, int age, int weight, String sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", sex='" + sex + '\'' +
                '}';
    }
}
