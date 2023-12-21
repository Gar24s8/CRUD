package dao;

import models.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class TaskDAOImpl implements TaskDAO {

    private static final Logger LOG = Logger.getLogger(TaskDAOImpl.class.getName());

    @Override
    public List<Task> getAll() {
        LOG.info(() -> "Trying to get all tasks");
        List<Task> tasks = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tasks = (List<Task>) session.createQuery("FROM Task ").list();
            LOG.info(() -> "All tasks successfully got!");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return tasks;
    }

    @Override
    public Optional<Task> getById(int id) {
        LOG.info(() -> format("Trying to get task %s", id));
        Optional<Task> task = Optional.empty();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            task = Optional.of(session.load(Task.class, id));
            //task = session.load(Task.class, id);
            System.out.println(task);
            LOG.info(() -> format("Task %s successfully got!", id));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return task;
    }

    @Override
    public boolean insert(Task task) {
        LOG.info(() -> format("Trying to insert task %s", task));
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.save(task);
            tx1.commit();
            LOG.info(() -> format("Task %s successfully inserted!", task));
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            LOG.log(Level.SEVERE, e, e::getMessage);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Task task) {
        LOG.info(() -> format("Trying to update task %s", task));
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.update(task);
            tx1.commit();
            LOG.info(() -> format("Task %s successfully updated!", task));
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            LOG.log(Level.SEVERE, e, e::getMessage);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
