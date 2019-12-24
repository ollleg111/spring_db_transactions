package com.lesson_db.dao;

import com.lesson_db.entity.Item;
import com.lesson_db.exceptions.BadRequestException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

// реализация ItemDAO без аннотации @Transaction
@Repository
public class OldItemDAO {

    private Item item;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public OldItemDAO(Item item) {
        this.item = item;
    }

    private static final String NAME_LIKE = "SELECT * FROM ITEMS WHERE ITEMS.NAME LIKE ?";
    private static final String SELECT_FROM = "SELECT * FROM ITEMS";

    private EntityTransaction transaction = entityManager.getTransaction();

    public Item findById(long id) throws HibernateException {
        try {
            transaction.begin();
            item = entityManager.find(Item.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("findById(long id) is failed");
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("The method findById(long id) was failed in class "
                    + com.lesson_db.dao.ItemDAO.class.getName());
        }
        return item;
    }

    public void save(Item item) throws HibernateException {
        try {
            transaction.begin();
            entityManager.persist(item);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("save(Item item) is failed");
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("The method save(Item item) was failed in class "
                    + com.lesson_db.dao.ItemDAO.class.getName());
        }
    }

    public Item update(Item item) throws HibernateException {
        try {
            transaction.begin();
            item = entityManager.merge(item);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("update(Item item) is failed");
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("The method update(Item item) was failed in class "
                    + com.lesson_db.dao.ItemDAO.class.getName());
        }
        return item;
    }

    public void delete(long id) throws HibernateException {
        deleteItem(findById(id));
    }

    public void deleteByName(String name) throws BadRequestException {
        assert entityManager != null;
        try {
            Query query = entityManager.createNativeQuery(NAME_LIKE, Item.class);
            query.setParameter(1, "%" + name + "%");
            Item entity = (Item) query.getSingleResult();
            deleteItem(entity);
        } catch (HibernateException e) {
            throw new HibernateException("Operation with item with name: " + name
                    + " was filed in method deleteByName(String name) from class "
                    + com.lesson_db.dao.ItemDAO.class.getName());
        }
    }

    public void deleteItem(Item item) throws HibernateException {
        try {
            transaction.begin();
            entityManager.remove(item);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("deleteItem(Item item) is failed");
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
            throw new HibernateException("The method deleteItem(Item item) was failed in class "
                    + com.lesson_db.dao.ItemDAO.class.getName());
        }
    }

    //хз
    public List findAll() throws HibernateException {
        assert entityManager != null;
        try {
            Query query = entityManager.createNativeQuery(SELECT_FROM, Item.class);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new HibernateException("Operation filed in method findAll() from class "
                    + com.lesson_db.dao.ItemDAO.class.getName());
        }
    }
}
