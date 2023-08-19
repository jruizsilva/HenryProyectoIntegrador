package henryproyectointegrador.dao.dto;

import java.util.Date;

public class ExpenseDto {
    private int id_expense;
    private double amount;
    private int category_id;
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

    public int getId_expense() {
        return this.id_expense;
    }

    public ExpenseDto setId_expense(int id_expense) {
        this.id_expense = id_expense;
        return this;
    }

    public double getAmount() {
        return this.amount;
    }

    public ExpenseDto setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public ExpenseDto setCategory_id(int category_id) {
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
                "id=" + id_expense +
                ", amount=" + amount +
                ", category_id=" + category_id +
                ", date=" + date +
                '}';
    }
}
