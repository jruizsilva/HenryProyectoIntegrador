package henryproyectointegrador.dao.dto;

import java.time.LocalDate;

public class ExpenseDto {
    private Integer id;
    private Double amount;
    private Integer idCategory;
    private LocalDate date;

    public ExpenseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", idCategory=" + idCategory +
                ", date=" + date +
                '}';
    }
}
