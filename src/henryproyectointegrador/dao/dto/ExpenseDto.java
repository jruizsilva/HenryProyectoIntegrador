package henryproyectointegrador.dao.dto;

import java.util.Date;

public class ExpenseDto {
    private long id;
    private double amount;
    private long category_id;
    private Date date;

    private ExpenseDto(double amount) {
        this.amount = amount;
    }

    public static ExpenseDto Make(double amount) {
        return new ExpenseDto(amount);
    }

    public ExpenseDto Build() {
        return this;
    }

    public long getId() {
        return this.id;
    }

    public ExpenseDto setId(long id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return this.amount;
    }

    public ExpenseDto setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public long getCategory_id() {
        return this.category_id;
    }

    public ExpenseDto setCategory_id(long category_id) {
        this.category_id = category_id;
        return this;
    }

    public Date getDate() {
        return this.date;
    }

    public ExpenseDto setDate(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", category_id=" + category_id +
                ", date=" + date +
                '}';
    }
}
