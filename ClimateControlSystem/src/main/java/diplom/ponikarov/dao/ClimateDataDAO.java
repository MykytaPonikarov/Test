package diplom.ponikarov.dao;

import diplom.ponikarov.entity.ClimateData;

import java.util.Date;
import java.util.List;

/**
 * Created by Nikita on 03.11.2015.
 */
public interface ClimateDataDAO {

    void add(ClimateData climateData);

    List<ClimateData> getAll();

    List<ClimateData> getAllByControllerNumberAndDateRange(int controllerNumber, Date fromDate, Date toDate);
}
