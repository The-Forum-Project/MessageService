package com.example.messageservice.dao;

import com.example.messageservice.domain.Message;
import com.example.messageservice.exception.MessageNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MessageDao extends AbstractHibernateDao<Message>{
    public void createMessage(Message message) {
        try(Session session = sessionFactory.openSession()){
            session.saveOrUpdate(message);
        }
    }

    public List<Message> getAllMessages() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Message", Message.class).list();
        }
    }

    public Message getMessage(int id) throws MessageNotFoundException {
        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Message> cr = cb.createQuery(Message.class);
            Root<Message> root = cr.from(Message.class);
            cr.select(root).where(cb.and(cb.equal(root.get("messageId"), id)));
            List<Message> list = session.createQuery(cr).getResultList();
            if(list.isEmpty()){
                throw new MessageNotFoundException();
            }
            return session.createQuery(cr).uniqueResult();
        }
    }

    public void updateMessage(Message message) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(message);
            transaction.commit();
        }
    }
}
