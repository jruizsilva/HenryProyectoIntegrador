package henryproyectointegrador.dao.impl;

import henryproyectointegrador.config.ConnectionH2;
import henryproyectointegrador.dao.ExpenseDao;
import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.entities.ExpenseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImplH2 implements ExpenseDao {
    private static final String SQL_SELECT_ALL = "SELECT id_expense, amount, id_category, date FROM EXPENSE";
    private static final String SQL_SELECT_BY_ID = "SELECT id_expense, amount, id_category, date FROM EXPENSE WHERE id_expense = ?";
    private static final String SQL_INSERT = "INSERT INTO EXPENSE (amount, id_category, date) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE EXPENSE SET amount = ?, id_category = ?, date = ? WHERE id_expense = ?";
    private static final String SQL_DELETE = "DELETE FROM EXPENSE WHERE id_expense = ?";
    private static final List<ExpenseEntity> expenseEntityList = new ArrayList<>();

    @Override
    public int insert(ExpenseDto dataDto) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
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
    public int update(Integer id, ExpenseDto dataDto) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setDouble(1, dataDto.getAmount());
            preparedStatement.setInt(2, dataDto.getCategory_id());
            preparedStatement.setDate(3, (Date) dataDto.getDate());
            preparedStatement.setInt(4, id);
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
    public int deleteById(Integer id) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
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
    public List<ExpenseDto> findAll() {
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
                int category_id = resultSet.getInt("id_category");
                Date date = resultSet.getDate("date");
                ExpenseDto expenseDto = ExpenseDto.Make(monto)
                                                  .setId_expense(id)
                                                  .setCategory_id(category_id)
                                                  .setDate(date)
                                                  .Build();
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
    public ExpenseDto findById(Integer id) {
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ExpenseDto expenseDto = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double monto = resultSet.getDouble("amount");
                int id_expense = resultSet.getInt("id_expense");
                int category_id = resultSet.getInt("id_category");
                Date date = resultSet.getDate("date");
                expenseDto = ExpenseDto.Make(monto)
                                       .setId_expense(id)
                                       .setCategory_id(category_id)
                                       .setDate(date)
                                       .Build();
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

    @Override
    public ExpenseEntity mapDtoToEntity(ExpenseDto expenseDto) {
        return null;
    }

    @Override
    public ExpenseDto mapEntityToDto(ExpenseEntity expenseEntity) {
        return null;
    }
}
