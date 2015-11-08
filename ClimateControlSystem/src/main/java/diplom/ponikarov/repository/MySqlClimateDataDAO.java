package diplom.ponikarov.repository;

import diplom.ponikarov.db.MySqlConnectionManager;
import diplom.ponikarov.entity.ClimateData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikita on 03.11.2015.
 */
public class MySqlClimateDataDAO implements ClimateDataDAO {

    private Connection connection;
    public static final String INSERT_TO_CLIMATE_DATE = "INSERT INTO climate_data SET status=?, temperature=?, humidity=?";


    public MySqlClimateDataDAO(MySqlConnectionManager connectionManager) {
        connection = connectionManager.getConnection();
    }

    @Override
    public void add(ClimateData climateData) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT_TO_CLIMATE_DATE)){
            statement.setString(1, climateData.getStatus());
            statement.setInt(2, climateData.getTemperature());
            statement.setInt(3, climateData.getHumidity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClimateData> getAll() {
        return null;
    }
}
