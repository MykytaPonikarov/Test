package diplom.ponikarov.service;

import diplom.ponikarov.dao.ClimateDataDAO;
import diplom.ponikarov.entity.ClimateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("climateDataService")
public class ClimateDataService {

    @Autowired
    private ClimateDataDAO climateDataDAO;

    public void add(ClimateData climateData) {
        climateDataDAO.add(climateData);
    }

    public List<ClimateData> getAll() {
        return climateDataDAO.getAll();
    }

    public List<ClimateData> getAllWithLimit(int limit) {
        return climateDataDAO.getAllWithLimit(limit);
    }

    public void setClimateDataDAO(ClimateDataDAO climateDataDAO) {
        this.climateDataDAO = climateDataDAO;
    }
}
