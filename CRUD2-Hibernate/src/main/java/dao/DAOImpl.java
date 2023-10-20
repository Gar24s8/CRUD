package dao;

import models.Employee;
import models.Office;
import models.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class DAOImpl implements DAO {

    public Office findOfficeById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Office foundOffice = null;
        try {
            foundOffice = session.load(Office.class, id);
            System.out.println(foundOffice);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundOffice;
    }

    public Employee findEmployeeById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Employee foundEmployee = null;
        try {
            foundEmployee = session.load(Employee.class, id);
            System.out.println(foundEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundEmployee;
    }

    public Task findTasksBytId(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Task foundTask = null;
        try {
            foundTask = session.load(Task.class, id);
            System.out.println(foundTask);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return foundTask;
    }

    public List<Employee> findAllEmployees() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Employee> employees = null;
        try {
            employees = (List<Employee>) session.createQuery("FROM Employee ").list();
            System.out.println(employees);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    public List<Office> findAllOffices() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Office> offices = null;
        try {
            offices = (List<Office>) session.createQuery("FROM Office ").list();
            System.out.println(offices);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return offices;
    }

    public List<Task> findAllTasks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Task> tasks = null;
        try {
            tasks = (List<Task>) session.createQuery("FROM Task ").list();
            System.out.println(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tasks;
    }

    public void createEmployee(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.save(employee);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void createOffice(Office office) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.save(office);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void createTask(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.save(task);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployee(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            if (employee != null) session.update(employee);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateOffice(Office office) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.update(office);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateTask(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.update(task);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteEmployee(Employee employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.delete(employee);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteOffice(Office office) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.delete(office);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteTask(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.delete(task);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Employee deleteEmployeeById(int id) {
        Transaction tx1 = null;
        Employee employee = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                System.out.println("Employee " + id + " deleted!");
            }
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        }
        return employee;
    }
}


