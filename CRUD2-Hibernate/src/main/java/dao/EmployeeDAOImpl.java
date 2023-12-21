package dao;

import models.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final Logger LOG = Logger.getLogger(EmployeeDAOImpl.class.getName());

    @Override
    public List<Employee> getAll() {
        LOG.info(() -> "Trying to get all offices");
        List<Employee> employees = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            employees = (List<Employee>) session.createQuery("FROM Employee ").list();
            LOG.info(() -> "All offices successfully got!");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return employees;
    }

    @Override
    public Optional<Employee> getById(int id) {
        LOG.info(() -> format("Trying to get employee %s", id));
        Optional<Employee> employee = Optional.empty();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            employee = Optional.of(session.load(Employee.class, id));
            System.out.println(employee);
            LOG.info(() -> format("Employee %s successfully got!", id));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return employee;
    }

    @Override
    public boolean insert(Employee employee) {
        LOG.info(() -> format("Trying to insert employee %s", employee));
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.save(employee);
            tx1.commit();
            LOG.info(() -> format("Employee %s successfully inserted!", employee));
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            LOG.log(Level.SEVERE, e, e::getMessage);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Employee employee) {
        LOG.info(() -> format("Trying to update employee %s", employee));
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.update(employee);
            tx1.commit();
            LOG.info(() -> format("Employee %s successfully updated!", employee));
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            LOG.log(Level.SEVERE, e, e::getMessage);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        LOG.info(() -> format("Trying to delete office %s", id));
        Transaction tx1 = null;
        Employee employee;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                tx1.commit();
                LOG.info(() -> format("Employee %s successfully deleted!", employee));
            } else {
                LOG.warning(() -> format("Failed to delete employee %s", id));
            }
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            LOG.log(Level.SEVERE, e, e::getMessage);
            return false;
        }
        return true;
    }
}
