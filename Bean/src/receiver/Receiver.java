package receiver;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@MessageDriven(mappedName = "jms/Topic")
public class Receiver implements MessageListener {
    @Resource(lookup = "jdbc/Orcl")
    private DataSource dataSource;

    @Override
    public void onMessage(Message message) {
        String sql = "INSERT INTO MSG VALUES(?, sysdate)";
        TextMessage textMessage = (TextMessage) message;
        try (Connection databaseConnection = dataSource.getConnection()
        ) {
            System.out.println(textMessage.getText());
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.setString(1, textMessage.getText());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
