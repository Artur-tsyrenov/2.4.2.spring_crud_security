package org.web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }


    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User read(int id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u " +
                "where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public void delete(int id) {
        Query query = entityManager.createQuery("delete from User u where u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public UserDetails getUserByName(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u " +
                "where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public void update(int id, User user) {
        Query query = entityManager.createQuery("update User u set u.firstName = :firstName, " +
                "u.lastName = :lastName, u.salary = :salary where u.id = :id");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("salary", user.getSalary());
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
