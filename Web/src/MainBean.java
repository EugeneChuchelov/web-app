import sndr.Sndr;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "Send")
public class MainBean {
    @Resource(lookup = "sndr.Sndr")
    private Sndr sender;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void send() {
        sender.send(message);
    }
}