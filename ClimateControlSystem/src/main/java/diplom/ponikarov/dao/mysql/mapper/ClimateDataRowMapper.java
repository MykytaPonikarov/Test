package diplom.ponikarov.dao.mysql.mapper;

import diplom.ponikarov.entity.ClimateData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClimateDataRowMapper implements RowMapper<ClimateData> {

    @Override
    public ClimateData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ClimateData climateData = new ClimateData();
        climateData.setControllerNumber(resultSet.getInt("controller_number"));
        climateData.setTemperature(resultSet.getFloat("temperature"));
        climateData.setHumidity(resultSet.getFloat("humidity"));
        climateData.setStatus(resultSet.getString("status"));
        climateData.setDate(resultSet.getTimestamp("date"));
        return climateData;
    }
}
