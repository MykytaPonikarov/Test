package diplom.ponikarov.dao.mysql;

import diplom.ponikarov.dao.ClimateDataDAO;
import diplom.ponikarov.dao.mysql.mapper.ClimateDataRowMapper;
import diplom.ponikarov.db.MySqlConnectionManager;
import diplom.ponikarov.entity.ClimateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("climateDataDAO")
public class MySqlClimateDataDAO implements ClimateDataDAO {

//    private Connection connection;
    private static final String INSERT_TO_CLIMATE_DATE = "INSERT INTO climate_data SET status=?, temperature=?, humidity=?";
    private static final String GET_ALL_CLIMATE_DATA = "SELECT * FROM climate_data";
    private static final String GET_CLIMATE_DATA_WITH_LIMIT = "SELECT * FROM climate_data ORDER BY date DESC LIMIT ?";

/*    public MySqlClimateDataDAO(MySqlConnectionManager connectionManager) {
        connection = connectionManager.getConnection();
    }*/

    @Autowired
    private DataSource dataSource;

    @Override
    public void add(ClimateData climateData) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(INSERT_TO_CLIMATE_DATE, climateData.getStatus(), climateData.getTemperature(), climateData.getHumidity());
    }

    //    @Override
//    public void add(ClimateData climateData) {
//        try (PreparedStatement statement = connection.prepareStatement(INSERT_TO_CLIMATE_DATE)) {
//            statement.setString(1, climateData.getStatus());
//            statement.setFloat(2, climateData.getTemperature());
//            statement.setFloat(3, climateData.getHumidity());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public List<ClimateData> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(GET_ALL_CLIMATE_DATA, new ClimateDataRowMapper());
    }


//    @Override
//    public List<ClimateData> getAll() {
//        List<ClimateData> climateDataList = new ArrayList<>();
//        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CLIMATE_DATA)) {
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                climateDataList.add(extract(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return climateDataList;
//    }

    @Override
    public List<ClimateData> getAllByControllerNumberAndDateRange(int controllerNumber, Date fromDate, Date toDate) {

        return null;
    }

    @Override
    public List<ClimateData> getAllWithLimit(int limit) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(GET_CLIMATE_DATA_WITH_LIMIT, new Object[]{limit}, new ClimateDataRowMapper());
    }

    //    @Override
//    public List<ClimateData> getAllWithLimit(int limit) {
//        List<ClimateData> climateDataList = new ArrayList<>();
//        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CLIMATE_DATA)) {
//            statement.setInt(1, limit);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                climateDataList.add(extract(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return climateDataList;
//    }

//    private ClimateData extract(ResultSet resultSet) throws SQLException {
//        ClimateData climateData = new ClimateData();
//        climateData.setControllerNumber(resultSet.getInt("controller_number"));
//        climateData.setTemperature(resultSet.getFloat("temperature"));
//        climateData.setHumidity(resultSet.getFloat("humidity"));
//        climateData.setStatus(resultSet.getString("status"));
//        climateData.setDate(resultSet.getTimestamp("date"));
//        return climateData;
//    }
}
