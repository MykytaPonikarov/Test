package diplom.ponikarov.service;

import diplom.ponikarov.dao.ClimateDataDAO;
import diplom.ponikarov.entity.ClimateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("climateDataService")
public class ClimateDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClimateDataService.class);

    @Autowired
    private ClimateDataDAO climateDataDAO;

    public void add(ClimateData climateData) {
        LOGGER.debug("Add data to storage. Data: {}", climateData);
        climateDataDAO.add(climateData);
    }

    public List<ClimateData> getAll() {
        return  climateDataDAO.getAll();
    }

    public List<ClimateData> getAllWithLimit(int limit) {
        return climateDataDAO.getAllWithLimit(limit);
    }

    public List<ClimateData> getAllByControllerNumber(int controllerNumber) {
        return climateDataDAO.getAllByControllerNumber(controllerNumber);
    }

    public List<ClimateData> getAllByControllerNumberAndDateRange(int controllerNumber, Date fromDate, Date toDate) {
        return climateDataDAO.getAllByControllerNumberAndDateRange(controllerNumber, fromDate, toDate);
    }
}
