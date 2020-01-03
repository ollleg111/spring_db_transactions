package com.lesson_db.service;

import com.lesson_db.dao.ItemDAO;
import com.lesson_db.entity.Item;
import com.lesson_db.exceptions.BadRequestException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ItemService {
    private ItemDAO itemDao;

    @Autowired
    public ItemService(ItemDAO itemDao) throws HibernateException {
        this.itemDao = itemDao;
    }

    public Item save(Item item) throws HibernateException {
        return itemDao.save(item);
    }

    public Item findById(long id) throws RuntimeException {
        Item item = itemDao.findById(id);
        itemNullValidator(item);
        return itemDao.findById(id);
    }

    public Item update(Item item) throws RuntimeException {
        itemNullValidator(item);
        return itemDao.update(item);
    }

    public void delete(long id) throws RuntimeException {
        Item item = itemDao.findById(id);
        itemNullValidator(item);
        itemDao.delete(id);
    }

    public void deleteByName(String name) throws HibernateException {
        validationString(name);
        itemDao.deleteByName(name);
    }

    public List<Item> findAll() throws HibernateException{
        return itemDao.findAll();
    }

    private void itemNullValidator(Item item) throws RuntimeException {
        if (item == null) throw new BadRequestException("Item does not exist in method" +
                " itemNullValidator(Item item) from class " +
                Item.class.getName());
    }

    private void validationString(String name) throws BadRequestException {
        if (name.isEmpty()) throw new BadRequestException(" Field: name is empty in method +" +
                "deleteByName(String name) from class " + ItemDAO.class.getName());
    }
}
