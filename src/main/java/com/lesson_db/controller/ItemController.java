package com.lesson_db.controller;

import com.lesson_db.exceptions.BadRequestException;
import com.lesson_db.dao.ItemDAO;
import com.lesson_db.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/item")
public class ItemController {
    private ItemDAO itemDao;

    @Autowired
    public ItemController(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/save",
            produces = "text/plain")
//    public ResponseEntity<String> save(Item item) {
    public ResponseEntity<String> save(@RequestBody Item item) {
        try {
//            itemDao.save(new ObjectMapper().readValue((DataInput) item, Item.class));
            itemDao.save(item);
            return new ResponseEntity<>(" Item was saved ", HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find",
            produces = "text/plain")
//    public ResponseEntity<String> findById(long id) {
    public ResponseEntity<String> findById(@RequestParam(value = "id") Long id) {
        try {
//            itemDao.findById(new ObjectMapper().readValue(String.valueOf(id), Long.class));
            itemDao.findById(id);
            return new ResponseEntity<>(" Item was found ", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/update",
            produces = "text/plain")
//    public ResponseEntity<String> update(Item item) {
    public ResponseEntity<String> update(@RequestBody Item item) {
        try {
//            itemDao.update(new ObjectMapper().readValue((DataInput) item, Item.class));
            itemDao.update(item);
            return new ResponseEntity<>(" Item was updated ", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/delete",
            produces = "text/plain")
//    public ResponseEntity<String> delete(long id) {
    public ResponseEntity<String> delete(@RequestParam(value = "id") Long id) {
        try {
//            itemDao.delete(new ObjectMapper().readValue(String.valueOf(id), Long.class));
            itemDao.delete(id);
            return new ResponseEntity<>(" Item was deleted ", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (
                Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
