package henryproyectointegrador.dao.h2;

import henryproyectointegrador.config.ConnectionH2;
import henryproyectointegrador.dao.CategoryCRUD;
import henryproyectointegrador.dao.dto.CategoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2CategoryCRUD implements CategoryCRUD {
    private static final String SQL_SELECT_ALL = "SELECT id_category, name FROM categories";
    private static final String SQL_SELECT_BY_ID = "SELECT id_category, name FROM categories WHERE id_category = ?";
    private static final String SQL_INSERT = "INSERT INTO categories (name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE categories SET name = ? WHERE id_category = ?";
    private static final String SQL_DELETE = "DELETE FROM categories WHERE id_category = ?";

    @Override
    public Integer insert(CategoryDto categoryDto) {
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
    public Integer update(CategoryDto categoryDto) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, categoryDto.getName());
            preparedStatement.setInt(2, categoryDto.getId());
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
    public Integer delete(CategoryDto category) {
        int rowsAffected;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, category.getId());
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
    public List<CategoryDto> selectAll() {
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
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(id);
                categoryDto.setName(name);
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
    public CategoryDto selectOne(Integer id) {
        CategoryDto categoryDto = null;
        Connection connection = ConnectionH2.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idCategory = resultSet.getInt("id_category");
                String name = resultSet.getString("name");
                categoryDto = new CategoryDto();
                categoryDto.setId(idCategory);
                categoryDto.setName(name);
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
