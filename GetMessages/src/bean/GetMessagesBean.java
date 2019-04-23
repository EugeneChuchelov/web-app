package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class GetMessagesBean implements GetMessages {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List getMessages(Date startTime, Date endTime) {
        return em.createQuery("SELECT m FROM Message m WHERE m.time BETWEEN :startTime AND :endTime").
                setParameter("startTime", startTime).
                setParameter("endTime", endTime).
                getResultList();
    }
}
