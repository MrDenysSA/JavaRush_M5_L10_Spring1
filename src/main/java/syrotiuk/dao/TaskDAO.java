package syrotiukDenys.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import syrotiukDenys.domain.Task;

import java.util.List;

@Repository
public class TaskDAO {
    private final SessionFactory sessionFactory;

    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Task> getAll(int offset, int limit) {
        Query<Task> queue = getSession().createQuery("select t from Task t", Task.class);
        queue.setFirstResult(offset);
        queue.setMaxResults(limit);
        return queue.getResultList();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getAllCount() {
        Query<Long> queue = getSession().createQuery("select count(t) from Task t", Long.class);
        return Math.toIntExact(queue.uniqueResult());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Task getById(int id) {
        Query<Task> queue = getSession().createQuery("select t from Task t where t.id = :ID", Task.class);
        queue.setParameter("ID", id);
        return queue.uniqueResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Task task) {
        getSession().persist(task);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task) {
        getSession().remove(task);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
