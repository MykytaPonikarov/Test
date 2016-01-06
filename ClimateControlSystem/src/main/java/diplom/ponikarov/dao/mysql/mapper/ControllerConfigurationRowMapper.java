package diplom.ponikarov.dao.mysql.mapper;

import diplom.ponikarov.entity.ControllerConfiguration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerConfigurationRowMapper implements RowMapper<ControllerConfiguration> {

    @Override
    public ControllerConfiguration mapRow(ResultSet rs, int rowNum) throws SQLException {
        ControllerConfiguration configuration = new ControllerConfiguration();
        configuration.setControllerNumber(rs.getInt("controller_number"));
        configuration.setMaxTemperature(rs.getFloat("max_temperature"));
        configuration.setMinTemperature(rs.getFloat("min_temperature"));
        configuration.setMaxHumidity(rs.getFloat("max_humidity"));
        configuration.setMinHumidity(rs.getFloat("min_humidity"));
        return configuration;
    }
}
