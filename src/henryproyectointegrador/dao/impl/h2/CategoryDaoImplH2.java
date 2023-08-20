package henryproyectointegrador.dao.impl.h2;

import henryproyectointegrador.config.ConnectionH2;
import henryproyectointegrador.dao.CategoryDao;
import henryproyectointegrador.dao.dto.CategoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImplH2 implements CategoryDao {
    private static final String SQL_SELECT_ALL = "SELECT id_category, name FROM CATEGORY";
    private static final String SQL_SELECT_BY_ID = "SELECT id_category, name FROM CATEGORY WHERE id_category = ?";
    private static final String SQL_INSERT = "INSERT INTO CATEGORY (name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE CATEGORY SET name = ? WHERE id_category = ?";
    private static final String SQL_DELETE = "DELETE FROM CATEGORY WHERE id_category = ?";

    @Override
    public int insert(CategoryDto categoryDto) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, categoryDto.getName());
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
    public int update(Integer integer, CategoryDto categoryDto) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, categoryDto.getName());
            preparedStatement.setInt(2, integer);
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
    public int deleteById(Integer integer) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, integer);
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
    public List<CategoryDto> findAll() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_category");
                String name = resultSet.getString("name");
                CategoryDto categoryDto = CategoryDto.Make(name)
                                                     .setId_category(id)
                                                     .Build();
                categoryDtoList.add(categoryDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionH2.close(connection);
            ConnectionH2.close(preparedStatement);
            ConnectionH2.close(resultSet);
        }

        return categoryDtoList;
    }

    @Override
    public CategoryDto findById(Integer integer) {
        CategoryDto categoryDto = null;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, integer);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_category");
                String name = resultSet.getString("name");
                categoryDto = CategoryDto.Make(name)
                                         .setId_category(id)
                                         .Build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionH2.close(connection);
            ConnectionH2.close(preparedStatement);
            ConnectionH2.close(resultSet);
        }
        return categoryDto;
    }
}
