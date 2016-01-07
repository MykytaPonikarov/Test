package diplom.ponikarov.dao.mysql;

import diplom.ponikarov.dao.ControllerConfigurationDAO;
import diplom.ponikarov.dao.mysql.mapper.ControllerConfigurationRowMapper;
import diplom.ponikarov.entity.ControllerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("controllerConfigurationDAO")
public class MySqlControllerConfigurationDAO implements ControllerConfigurationDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlControllerConfigurationDAO.class);

    private static final String INSERT_CONFIGURATION = "INSERT INTO controller_configuration SET controller_number=?, max_temperature=?, min_temperature=?, max_humidity=?, min_humidity=?";
    private static final String SELECT_BY_CONTROLLER_NUMBER = "SELECT * FROM controller_configuration WHERE controller_number=?";
    private static final String UPDATE_CONTROLLER_CONFIGURATION = "UPDATE controller_configuration SET max_temperature=?, min_temperature=?, max_humidity=?, min_humidity=? WHERE controller_number=?";

    @Autowired
    private DataSource dataSource;

    @Override
    public void add(ControllerConfiguration configuration) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(INSERT_CONFIGURATION,
                configuration.getControllerNumber(),
                configuration.getMaxTemperature(),
                configuration.getMinTemperature(),
                configuration.getMaxHumidity(),
                configuration.getMaxHumidity());
    }

    @Override
    public ControllerConfiguration get(int controllerNumber) {
        ControllerConfiguration configuration = null;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            configuration = jdbcTemplate.queryForObject(SELECT_BY_CONTROLLER_NUMBER,
                    new Object[]{controllerNumber},
                    new ControllerConfigurationRowMapper());

        } catch (EmptyResultDataAccessException e) {
            LOGGER.warn("Controller configuration with number {} not exist", controllerNumber);
        }
        return configuration;
    }

    @Override
    public void update(ControllerConfiguration controllerConfiguration) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(UPDATE_CONTROLLER_CONFIGURATION,
                controllerConfiguration.getMaxTemperature(),
                controllerConfiguration.getMinTemperature(),
                controllerConfiguration.getMaxHumidity(),
                controllerConfiguration.getMinHumidity(),
                controllerConfiguration.getControllerNumber());
    }
}
