package com.lesson_db.dao;

import com.lesson_db.entity.Item;
import com.lesson_db.exceptions.BadRequestException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ItemDAO {
    private Item item;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ItemDAO(Item item) {
        this.item = item;
    }

    private static final String NAME_LIKE = "SELECT * FROM ITEMS WHERE ITEMS.NAME LIKE ?";
    private static final String SELECT_FROM = "SELECT * FROM ITEMS";

    public Item findById(long id) throws HibernateException {
        try {
            item = entityManager.find(Item.class, id);
        } catch (HibernateException e) {
            System.err.println("The method findById(long id) is failed");
            System.err.println(e.getMessage());
        }
        return item;
    }

    public void save(Item item) throws HibernateException {
        try {
            entityManager.persist(item);
        } catch (HibernateException e) {
            System.err.println("The method save(Item item) is failed");
            System.err.println(e.getMessage());
        }
    }

    public Item update(Item item) throws HibernateException {
        try {
            item = entityManager.merge(item);
        } catch (HibernateException e) {
            System.err.println("The method update(Item item) is failed");
            System.err.println(e.getMessage());
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
                    + ItemDAO.class.getName());
        }
    }

    public void deleteItem(Item item) throws HibernateException {
        try {
            entityManager.remove(item);
        } catch (HibernateException e) {
            System.err.println("The method deleteItem(Item item) is failed");
            System.err.println(e.getMessage());
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
                    + ItemDAO.class.getName());
        }
    }
}
