package KursovProektOOP2.data.services;

import KursovProektOOP2.data.entity.City;
import KursovProektOOP2.data.entity.RenterInformation;
import KursovProektOOP2.data.repository.RenterInformationRepository;
import KursovProektOOP2.data.repository.UserNotificationRepository;

public class RenterService {
    public final RenterInformationRepository repository = RenterInformationRepository.getInstance();

    public static RenterService getInstance(){
        return RenterService.RenterServiceHolder.INSTANCE;
    }

    private static class RenterServiceHolder{
        public static final RenterService INSTANCE = new RenterService();
    }

    public int addRenter(String names, String phone, City city, String address){
        RenterInformation renter = new RenterInformation();
        renter.setRenterId(0);
        renter.setName(names);
        renter.setPhone(phone);
        renter.setCityId(city);
        renter.setStreet(address);
        return repository.saveAndReturnID(renter);
    }

}
