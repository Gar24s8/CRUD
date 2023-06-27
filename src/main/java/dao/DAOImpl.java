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
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Office findOffice = null;
        try {
            findOffice = session.load(Office.class, id);
            System.out.println(findOffice);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return findOffice;
    }

    public Employee findEmployeeById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Employee findEmployee = null;
        try {
            findEmployee = session.load(Employee.class, id);
            System.out.println(findEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return findEmployee;
    }

    public Tasks findTasksBytId(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Tasks findTasks = null;
        try {
            findTasks = session.load(Tasks.class, id);
            System.out.println(findTasks);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return findTasks;
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

    public List<Tasks> findAllTasks() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Tasks> tasks = null;
        try {
            tasks = (List<Tasks>) session.createQuery("FROM Tasks ").list();
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
