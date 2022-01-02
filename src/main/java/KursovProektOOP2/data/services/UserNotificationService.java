package KursovProektOOP2.data.services;

import KursovProektOOP2.controllers.Main;
import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.repository.UserNotificationRepository;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;

public class UserNotificationService {
    private static final Logger log = Logger.getLogger(Main.class);
    public final UserNotificationRepository repository = UserNotificationRepository.getInstance();

    public static UserNotificationService getInstance(){
        return UserNotificationService.UserNotificationServiceHolder.INSTANCE;
    }

    private static class UserNotificationServiceHolder{
        public static final UserNotificationService INSTANCE = new UserNotificationService();
    }

    public void createOwnerNotif(Owner owner, StorageRoom room, Formular formular){
        Usernotifications ownerNotif = new Usernotifications();
        ownerNotif.setIdFromUser(owner.getUserId());
        ownerNotif.setNotificationName("Стая от склад "
                + room.getwarehouse().getWarehouseName()
                + "\n беше отдадена под наем от "
                + formular.getPeriodBegin().toString()
                + "\n до "
                + formular.getPeriodEnd().toString());
        ownerNotif.setNotifTimeStamp(new Timestamp(System.currentTimeMillis()));
        owner.getUserId().getUsernotifications().add(ownerNotif);
        repository.save(ownerNotif);
    }

    public void createAgentNotif(Agent agent, Warehouse warehouse){
        Usernotifications userNotif = new Usernotifications();
        userNotif.setIdFromUser(agent.getIdFromUser());
        userNotif.setNotificationName("Бяхте добавен като складов агент за  склад: " + warehouse.getWarehouseName() + "\nТой принадлежи на " + warehouse.getOwnerId().getUserId().getUsername());
        userNotif.setNotifTimeStamp(new Timestamp(System.currentTimeMillis()));
        agent.getIdFromUser().getUsernotifications().add(userNotif);
        repository.save(userNotif);
    }

    public void markAsRead(int id){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String UPDATE_QUERY = "UPDATE Usernotifications SET isRead = true WHERE idNotifications = :idNotif";
        try{
            session.createQuery(UPDATE_QUERY).setParameter("idNotif", id).executeUpdate();
        }catch (Exception ex){
            log.error("Notifications marking unsuccessful " + "\n" + ex.getMessage());
        }finally {
            transaction.commit();
        }
    }

    public void deleteNotif(int id){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String DELETE_QUERY = "DELETE FROM Usernotifications WHERE idNotifications = :idNotif";
        try{
            session.createQuery(DELETE_QUERY).setParameter("idNotif", id).executeUpdate();

        }catch (Exception ex){
            log.error("Notifications deletion unsuccessful " + "\n" + ex.getMessage());
        }finally {
            transaction.commit();
        }
    }
}
