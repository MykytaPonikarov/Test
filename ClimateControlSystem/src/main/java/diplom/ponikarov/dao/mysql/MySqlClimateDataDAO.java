package diplom.ponikarov.dao.mysql;

import diplom.ponikarov.dao.ClimateDataDAO;
import diplom.ponikarov.dao.mysql.mapper.ClimateDataRowMapper;
import diplom.ponikarov.entity.ClimateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository("climateDataDAO")
public class MySqlClimateDataDAO implements ClimateDataDAO {

    private static final String INSERT_TO_CLIMATE_DATE = "INSERT INTO climate_data SET status=?, temperature=?, humidity=?, controller_number=?";
    private static final String GET_ALL_CLIMATE_DATA = "SELECT * FROM climate_data";
    private static final String GET_CLIMATE_DATA_WITH_LIMIT = "SELECT * FROM climate_data ORDER BY date DESC LIMIT ?";
    private static final String GET_CLIMATE_DATA_BY_CONTROLLER_NUMBER = "SELECT * FROM climate_data WHERE controller_number=?";
    private static final String GET_CLIMATE_DATA_BY_CONTROLLER_NUMBER_IN_DATE_RANGE = "SELECT * FROM climate_data WHERE controller_number=? AND date < ? AND date > ?";

    @Autowired
    private DataSource dataSource;

    @Override
    public void add(ClimateData climateData) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(INSERT_TO_CLIMATE_DATE,
                climateData.getStatus(),
                climateData.getTemperature(),
                climateData.getHumidity(),
                climateData.getControllerNumber());
    }

    @Override
    public List<ClimateData> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(GET_ALL_CLIMATE_DATA,
                new ClimateDataRowMapper());
    }

    @Override
    public List<ClimateData> getAllByControllerNumberAndDateRange(int controllerNumber, Date fromDate, Date toDate) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(GET_CLIMATE_DATA_BY_CONTROLLER_NUMBER_IN_DATE_RANGE,
                new Object[]{controllerNumber, toDate, fromDate},
                new ClimateDataRowMapper());
    }

    @Override
    public List<ClimateData> getAllByControllerNumber(int controllerNumber) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(GET_CLIMATE_DATA_BY_CONTROLLER_NUMBER,
                new Object[]{controllerNumber},
                new ClimateDataRowMapper());
    }

    @Override
    public List<ClimateData> getAllWithLimit(int limit) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(GET_CLIMATE_DATA_WITH_LIMIT,
                new Object[]{limit},
                new ClimateDataRowMapper());
    }
}
