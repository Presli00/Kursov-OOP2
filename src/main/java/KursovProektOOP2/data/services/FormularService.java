package KursovProektOOP2.data.services;

import KursovProektOOP2.data.entity.Formular;
import KursovProektOOP2.data.entity.RenterInformation;
import KursovProektOOP2.data.entity.StorageRoom;
import KursovProektOOP2.data.repository.FormularRepository;
import KursovProektOOP2.data.repository.RenterInformationRepository;

import java.sql.Timestamp;
import java.time.LocalDate;

public class FormularService {
    public final FormularRepository repository = FormularRepository.getInstance();
    public final RenterInformationRepository renterRepository = RenterInformationRepository.getInstance();

    public static FormularService getInstance(){
        return FormularService.FormularServiceHolder.INSTANCE;
    }

    private static class FormularServiceHolder{
        public static final FormularService INSTANCE = new FormularService();
    }

    public Formular createFormular(StorageRoom room, int id, LocalDate startDate, LocalDate endDate, double price){
        Formular formular = new Formular();
        formular.setRenterId((RenterInformation) renterRepository.getById(id).get());
        formular.setStorageRoom(room);
        formular.setPeriodBegin(Timestamp.valueOf(startDate.atStartOfDay()));
        formular.setPeriodEnd(Timestamp.valueOf(endDate.atStartOfDay()));
        formular.setPrice(price);
        repository.save(formular);
        return formular;
    }
}
