package diplom.ponikarov.dao.mysql;

import diplom.ponikarov.dao.ClimateDataDAO;
import diplom.ponikarov.db.MySqlConnectionManager;
import diplom.ponikarov.entity.ClimateData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikita on 03.11.2015.
 */
public class MySqlClimateDataDAO implements ClimateDataDAO {

    private Connection connection;
    private static final String INSERT_TO_CLIMATE_DATE = "INSERT INTO climate_data SET status=?, temperature=?, humidity=?";
    private static final String GET_ALL_CLIMATE_DATA = "SELECT * FROM climate_data";

    public MySqlClimateDataDAO(MySqlConnectionManager connectionManager) {
        connection = connectionManager.getConnection();
    }

    @Override
    public void add(ClimateData climateData) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_TO_CLIMATE_DATE)) {
            statement.setString(1, climateData.getStatus());
            statement.setFloat(2, climateData.getTemperature());
            statement.setFloat(3, climateData.getHumidity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClimateData> getAll() {
        List<ClimateData> climateDataList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_CLIMATE_DATA)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                climateDataList.add(extract(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return climateDataList;
    }

    @Override
    public List<ClimateData> getAllByControllerNumberAndDateRange(int controllerNumber, Date fromDate, Date toDate) {

        return null;
    }

    private ClimateData extract(ResultSet resultSet) throws SQLException {
        ClimateData climateData = new ClimateData();
        climateData.setControllerNumber(resultSet.getInt("controller_number"));
        climateData.setTemperature(resultSet.getFloat("temperature"));
        climateData.setHumidity(resultSet.getFloat("humidity"));
        climateData.setStatus(resultSet.getString("status"));
        climateData.setDate(resultSet.getTimestamp("date"));
        return climateData;
    }
}
