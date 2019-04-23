import bean.GetMessages;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "GetMessages")
public class GetMessagesBean {
    @Resource(lookup = "bean.GetMessages")
    GetMessages getMessages;

    private Date startDate;
    private Date endDate;

    private List messagesList;

    public void setMessagesList(List messagesList) {
        this.messagesList = messagesList;
    }

    public List getMessagesList() {
        return messagesList;
    }

    public void getMessages(){
        messagesList = getMessages.getMessages(startDate, endDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
