package henryproyectointegrador.entities;

public class CategoryEntity {
    private int id;
    private String name;

    private CategoryEntity(String name) {
        this.name = name;
    }

    public static CategoryEntity Make(String name) {
        return new CategoryEntity(name);
    }

    public CategoryEntity Build() {
        return this;
    }

    public int getId() {
        return this.id;
    }

    public CategoryEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "category_id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
