package at.htlkaindorf.bsp_development.collections;

public class Person {
    private String name;
    private int id;

    public Person(String line) {
        id = Integer.parseInt(line.split(";")[0]);
        name = line.split(";")[1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
