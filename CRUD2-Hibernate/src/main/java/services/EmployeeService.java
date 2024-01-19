package services;

import dao.EmployeeDAO;
import models.Employee;
import models.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class EmployeeService implements EmployeeDAO {

    private static final Logger LOG = Logger.getLogger(EmployeeService.class.getName());

    @Override
    public List<Employee> getAll() {
        LOG.info(() -> "Trying to get all employees");
        List<Employee> employees = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            employees = (List<Employee>) session.createQuery("FROM Employee ").list();
            LOG.info(() -> "All employees successfully got!");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return employees;
    }

    @Override
    public Optional<Employee> getById(int id) {
        LOG.info(() -> format("Trying to get employee %s", id));
        //  Optional<Employee> employee = Optional.empty();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Employee employee = session.get(Employee.class, id);
            LOG.info(() -> format("Employee %s successfully got!", id));
            return Optional.ofNullable(employee);
//            employee = Optional.of(session.find(Employee.class, id));
//            System.out.println(employee);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
            return Optional.empty();
        }
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
        LOG.info(() -> format("Trying to delete employee %s", id));
        Transaction tx1 = null;
        Employee employee;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                tx1.commit();
                LOG.info(() -> format("Employee %s successfully deleted!", id));
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

    public List<Employee> getEmployeeByOfficeId(int officeID) {
        LOG.info(() -> "Trying to get employees from office " + officeID);
        List<Employee> employees = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Employee WHERE office.id = :officeID");
            query.setParameter("officeID", officeID);
            employees = query.list();
            LOG.info(() -> "Employees from office " + officeID + " successfully got!");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return employees;
    }

    public Optional<List<Task>> getTasksByEmployee(Employee employee) {
        LOG.info(() -> "Trying to get tasks by employee " + employee.getId());
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> root = query.from(Task.class);
            query.select(root).where(builder.isMember(employee, root.get("employees")));
            List<Task> tasks = session.createQuery(query).getResultList();
            LOG.info(() -> "Tasks for employee " + employee.getId() + " successfully got: " + tasks.toString());
            return Optional.ofNullable(tasks);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
            return Optional.empty();
        }
    }

    public List<Employee> findEmployeeByName(String name) {
        LOG.info(() -> "Trying to find employees contains in name " + name);
        List<Employee> employees = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Employee WHERE lower(name) LIKE lower(:name)");
            query.setParameter("name", "%" + name.toLowerCase() + "%");
            employees = query.list();
            LOG.info(() -> "Employees contains in name " + name + " successfully got!");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return employees;
    }
}
