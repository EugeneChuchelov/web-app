package receiver;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@MessageDriven(mappedName = "jms/Topic")
public class Receiver implements MessageListener {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        String text = "Wrong message type!";
        if (message instanceof TextMessage) {
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        entity.Message persistenceEntity = new entity.Message(new Date(), text);

        em.persist(persistenceEntity);
    }
}
