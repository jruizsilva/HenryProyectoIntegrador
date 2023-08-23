package henryproyectointegrador.dao.h2;

import henryproyectointegrador.config.ConnectionH2;
import henryproyectointegrador.dao.ExpenseCRUD;
import henryproyectointegrador.dao.dto.ExpenseDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ExpenseCRUD implements ExpenseCRUD {
    private static final String SQL_SELECT_ALL = "SELECT id_expense, amount, id_category, date FROM EXPENSES";
    private static final String SQL_SELECT_BY_ID = "SELECT id_expense, amount, id_category, date FROM EXPENSES WHERE id_expense = ?";
    private static final String SQL_INSERT = "INSERT INTO EXPENSES (amount, id_category, date) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE EXPENSES SET amount = ?, id_category = ?, date = ? WHERE id_expense = ?";
    private static final String SQL_DELETE = "DELETE FROM EXPENSES WHERE id_expense = ?";

    @Override
    public Integer insert(ExpenseDto expense) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setDouble(1, expense.getAmount());
            preparedStatement.setInt(2, expense.getIdCategory());
            preparedStatement.setDate(3, Date.valueOf(expense.getDate()));
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionH2.close(connection);
            ConnectionH2.close(preparedStatement);
        }
        return rowsAffected;
    }

    @Override
    public Integer update(ExpenseDto expense) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setDouble(1, expense.getAmount());
            preparedStatement.setInt(2, expense.getIdCategory());
            preparedStatement.setDate(3, Date.valueOf(expense.getDate()));
            preparedStatement.setInt(4, expense.getId());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionH2.close(connection);
            ConnectionH2.close(preparedStatement);
        }

        return rowsAffected;
    }

    @Override
    public Integer delete(ExpenseDto expense) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, expense.getId());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionH2.close(connection);
            ConnectionH2.close(preparedStatement);
        }

        return rowsAffected;
    }

    @Override
    public List<ExpenseDto> selectAll() {
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        List<ExpenseDto> expenseDtoList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double monto = resultSet.getDouble("amount");
                int id = resultSet.getInt("id_expense");
                int idCategory = resultSet.getInt("id_category");
                Date date = resultSet.getDate("date");
                ExpenseDto expenseDto = new ExpenseDto();
                expenseDto.setId(id);
                expenseDto.setAmount(monto);
                expenseDto.setIdCategory(idCategory);
                expenseDto.setDate(date.toLocalDate());
                expenseDtoList.add(expenseDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionH2.close(connection);
            ConnectionH2.close(preparedStatement);
        }
        return expenseDtoList;
    }

    @Override
    public ExpenseDto selectOne(Integer id) {
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ExpenseDto expenseDto = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                int idExpense = resultSet.getInt("id_expense");
                int idCategory = resultSet.getInt("id_category");
                Date date = resultSet.getDate("date");
                expenseDto = new ExpenseDto();
                expenseDto.setId(idExpense);
                expenseDto.setAmount(amount);
                expenseDto.setId(idCategory);
                expenseDto.setDate(date.toLocalDate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionH2.close(connection);
            ConnectionH2.close(preparedStatement);
            ConnectionH2.close(resultSet);
        }
        return expenseDto;
    }
}
