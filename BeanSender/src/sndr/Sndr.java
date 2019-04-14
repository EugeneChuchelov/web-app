package sndr;

import javax.ejb.Remote;

@Remote
public interface Sndr {
    void send(String message);
}
