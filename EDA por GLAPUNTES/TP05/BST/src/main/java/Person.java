public class Person implements Comparable<Person> {
    private String name;
    private Integer id;

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int compareTo(Person o) {
        int cmp = Integer.compare(this.id,o.getId());
        if (cmp == 0) {
            cmp = this.name.compareTo(o.name);
        }
        return cmp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Legajo: %d\nNombre: %s\n",getId(),getName());
    }
}
