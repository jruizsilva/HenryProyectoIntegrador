package henryproyectointegrador.dao.dto;

public class CategoryDto {
    private int id_category;
    private String name;

    private CategoryDto(String name) {
        this.name = name;
    }

    public static CategoryDto Make(String name) {
        return new CategoryDto(name);
    }

    public CategoryDto Build() {
        return this;
    }

    public int getId_category() {
        return this.id_category;
    }

    public CategoryDto setId_category(int id_category) {
        this.id_category = id_category;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }
}
