package diplom.ponikarov.dao;

import diplom.ponikarov.entity.ClimateData;

import java.util.Date;
import java.util.List;

public interface ClimateDataDAO {

    void add(ClimateData climateData);

    List<ClimateData> getAll();

    List<ClimateData> getAllByControllerNumberAndDateRange(int controllerNumber, Date fromDate, Date toDate);

    List<ClimateData> getAllByControllerNumber(int controllerNumber);

    List<ClimateData> getAllWithLimit(int limit);
}
