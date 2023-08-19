package henryproyectointegrador.entities;

import java.util.Date;

public class ExpenseEntity {
    private int id;
    private double amount;
    private int category_id;
    private Date date;

    private ExpenseEntity(double amount) {
        this.amount = amount;
    }

    public static ExpenseEntity Make(double amount) {
        return new ExpenseEntity(amount);
    }

    public ExpenseEntity Build() {
        return this;
    }

    public int getId() {
        return this.id;
    }

    public ExpenseEntity setId(int id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return this.amount;
    }

    public ExpenseEntity setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public ExpenseEntity setCategory_id(int category_id) {
        this.category_id = category_id;
        return this;
    }

    public Date getDate() {
        return this.date;
    }

    public ExpenseEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "ExpenseEntity{" +
                "id_gasto=" + id +
                ", monto=" + amount +
                ", id_category=" + category_id +
                ", fecha=" + date +
                '}';
    }
}
