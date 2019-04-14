package sndr;


import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.jms.*;

@Stateful
public class SndrBean implements Sndr {
    @Resource(lookup="jms/ConnectionPool")
    private javax.jms.ConnectionFactory connectionFactory;

    @Resource(lookup="jms/Topic")
    private Destination destination;

    public void send(String txt){
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText(txt);
            producer.send(message);
            session.close();
            connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
}
