package com.lesson_db.service;

import com.lesson_db.dao.ItemDAO;
import com.lesson_db.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class ItemService {
    private ItemDAO itemDao;

    @Autowired
    public ItemService(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }

    public void save(Item item) {
        itemDao.save(item);
    }

    public Item findById(long id) {
        return itemDao.findById(id);
    }

    public Item update(Item item) {
        return itemDao.update(item);
    }

    public void delete(long id) {
        itemDao.delete(id);
    }

    public void deleteByName(String name){
        itemDao.deleteByName(name);
    }
}
