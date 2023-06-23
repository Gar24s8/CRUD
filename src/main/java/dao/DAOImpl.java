package dao;

import models.Employee;
import models.Office;
import models.Tasks;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class DAOImpl implements DAO {


    public Office findOfficeById(int id) {
        Office findOffice = HibernateSessionFactoryUtil.getSessionFactory().openSession().load(Office.class, id);
        System.out.println(findOffice);
        return findOffice;
    }

    public Employee findEmployeeById(int id) {
        Employee findEmployee = HibernateSessionFactoryUtil.getSessionFactory().openSession().load(Employee.class, id);
        System.out.println(findEmployee);
        return findEmployee;
    }

    public Tasks findTasksBytId(int id) {
        Tasks findTasks = HibernateSessionFactoryUtil.getSessionFactory().openSession().load(Tasks.class, id);
        System.out.println(findTasks);
        return findTasks;
    }

    public List<Employee> findAllEmployees() {
        List<Employee> employees = (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Employee ").list();
        System.out.println();
        return employees;
    }

    public List<Office> findAllOffices() {
        List<Office> offices = (List<Office>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Office ").list();
        System.out.println(offices);
        return offices;
    }

    public List<Tasks> findAllTasks() {
        List<Tasks> tasks = (List<Tasks>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Tasks ").list();
        System.out.println(tasks);
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

    public void createTask(Tasks tasks) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.save(tasks);
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
            session.update(employee);
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

    public void updateTask(Tasks tasks) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.update(tasks);
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

    public void deleteTask(Tasks tasks) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = null;
        try {
            tx1 = session.beginTransaction();
            session.delete(tasks);
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
