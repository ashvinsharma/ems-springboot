package com.ems.springboot.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import java.util.logging.Logger;

public abstract class AbstractDao {
    @Autowired
    private SessionFactory sessionFactory;

    private final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());

    Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    void persist(Object entity) {
        try {
            getSession().persist(entity);
        } catch (PersistenceException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public void delete(Object entity) {
        getSession().delete(entity);
    }
}
