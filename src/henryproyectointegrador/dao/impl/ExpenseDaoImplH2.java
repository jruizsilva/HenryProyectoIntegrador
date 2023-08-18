package henryproyectointegrador.dao.impl;

import henryproyectointegrador.config.ConnectionH2;
import henryproyectointegrador.dao.ExpenseDao;
import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.entities.ExpenseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImplH2 implements ExpenseDao {
    private static final String SQL_SELECT_ALL = "SELECT id_gasto, monto, categoria_id, fecha FROM expense";
    private static final String SQL_INSERT = "INSERT INTO expense (monto, categoria_id, fecha) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE expense SET monto = ?, categoria_id = ?, fecha = ? WHERE id_gasto = ?";
    private static final String SQL_DELETE = "DELETE FROM expense WHERE id_gasto = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT id_gasto, monto, categoria_id, fecha FROM expense WHERE id_gasto = ?";
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
    public int update(Long id, ExpenseDto dataDto) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setDouble(1, dataDto.getMonto());
            preparedStatement.setLong(2, dataDto.getCategoria_id());
            preparedStatement.setDate(3, (Date) dataDto.getFecha());
            preparedStatement.setLong(4, id);
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
    public int deleteById(Long id) {
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
                double monto = resultSet.getDouble("monto");
                long categoria_id = resultSet.getLong("categoria_id");
                Date fecha = resultSet.getDate("fecha");
                ExpenseDto expenseDto = new ExpenseDto(monto, categoria_id, fecha);
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
    public ExpenseDto findById(Long aLong) {
        return null;
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
