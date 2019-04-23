package bean;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface GetMessages {
    List getMessages(Date startTime, Date endTime);
}
