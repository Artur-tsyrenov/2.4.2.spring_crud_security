package org.web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.web.models.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }


    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User read(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User " +
                "where id = :userId");
        query.setParameter("userId", id);
        return query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("delete from User " +
                "where id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(int id, User user) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("update from User set firstName = :firstName, " +
                "lastName = :lastName, salary = :salary where id = :id");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("salary", user.getSalary());
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
