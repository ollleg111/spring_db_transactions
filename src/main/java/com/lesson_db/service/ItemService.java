package com.lesson_db.service;

import com.lesson_db.dao.ItemDAO;
import com.lesson_db.entity.Item;
import com.lesson_db.exceptions.BadRequestException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ItemService {
    private ItemDAO itemDao;

    @Autowired
    public ItemService(ItemDAO itemDao) throws HibernateException {
        this.itemDao = itemDao;
    }

    public void save(Item item) throws HibernateException {
        itemDao.save(item);
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
        itemDao.deleteByName(name);
    }

    private void itemNullValidator(Item item) throws RuntimeException {
        if (item == null) throw new BadRequestException("Item does not exist in method" +
                " itemNullValidator(Item item) from class " +
                Item.class.getName());
    }
}
