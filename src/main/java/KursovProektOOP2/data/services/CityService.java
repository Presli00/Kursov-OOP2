package KursovProektOOP2.data.services;

import KursovProektOOP2.data.entity.City;
import KursovProektOOP2.data.repository.*;

import java.util.List;

public class CityService {
    public final CityRepository repository = CityRepository.getInstance();

    public static CityService getInstance(){
        return CityService.CityServiceHolder.INSTANCE;
    }

    private static class CityServiceHolder{
        public static final CityService INSTANCE = new CityService();
    }

    public List<City> getAllCities(){
        return repository.getAll();
    }
}
