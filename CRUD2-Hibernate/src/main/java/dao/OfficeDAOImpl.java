package dao;

import models.Office;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class OfficeDAOImpl implements OfficeDAO {

    private static final Logger LOG = Logger.getLogger(OfficeDAOImpl.class.getName());


    @Override
    public List<Office> getAll() {
        LOG.info(() -> "Trying to get all offices");
        List<Office> offices = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            offices = (List<Office>) session.createQuery("FROM Office ").list();
            LOG.info(() -> "All offices successfully got!");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return offices;
    }

    @Override
    public Optional<Office> getById(int id) {
        LOG.info(() -> format("Trying to get office %s", id));
        Optional<Office> office = Optional.empty();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            office = Optional.of(session.load(Office.class, id));
            System.out.println(office);
            LOG.info(() -> format("Office %s successfully got!", id));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e, e::getMessage);
        }
        return office;
    }

    @Override
    public boolean insert(Office office) {
        LOG.info(() -> format("Trying to insert office %s", office));
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.save(office);
            tx1.commit();
            LOG.info(() -> format("Office %s successfully inserted!", office));
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            LOG.log(Level.SEVERE, e, e::getMessage);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Office office) {
        LOG.info(() -> format("Trying to update office %s", office));
        Transaction tx1 = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            session.update(office);
            tx1.commit();
            LOG.info(() -> format("Office %s successfully updated!", office));
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
        Office office;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            tx1 = session.beginTransaction();
            office = session.get(Office.class, id);
            if (office != null) {
                session.delete(office);
                tx1.commit();
                LOG.info(() -> format("Office %s successfully deleted!", office));
            } else {
                LOG.warning(() -> format("Failed to delete office %s", id));
            }
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            LOG.log(Level.SEVERE, e, e::getMessage);
            return false;
        }
        return true;
    }
}
