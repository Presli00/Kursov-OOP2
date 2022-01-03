package KursovProektOOP2.data.services;

import KursovProektOOP2.data.entity.Climate;
import KursovProektOOP2.data.repository.ClimateRepository;

import java.util.List;

public class ClimateService {
    public final ClimateRepository repository = ClimateRepository.getInstance();

    public static ClimateService getInstance(){
        return ClimateService.ClimateServiceHolder.INSTANCE;
    }

    private static class ClimateServiceHolder{
        public static final ClimateService INSTANCE = new ClimateService();
    }

    public List<Climate> getAllClimates(){
        return repository.getAll();
    }
}
