package sender;

import javax.ejb.Remote;

@Remote
public interface Sender {
    void send(String message);
}
