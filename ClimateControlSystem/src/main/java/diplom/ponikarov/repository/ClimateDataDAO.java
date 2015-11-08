package diplom.ponikarov.repository;

import diplom.ponikarov.entity.ClimateData;

import java.util.List;

/**
 * Created by Nikita on 03.11.2015.
 */
public interface ClimateDataDAO {

    void add(ClimateData climateData);

    List<ClimateData> getAll();
}
