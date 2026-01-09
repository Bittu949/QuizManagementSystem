package com.project.Quiz.System.Repository;

import com.project.Quiz.System.Entity.User;
import com.project.Quiz.System.Entity.UserAuth;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public List<User> validateUser(String userName){
        return em.createQuery("FROM User where username=:userName", User.class)
                .setParameter("userName", userName).getResultList();
    }

    public void addToken(UserAuth auth) {
        em.persist(auth);
    }

    public void deleteInvalidToken(Long id) {
        em.createQuery("DELETE FROM UserAuth ua where ua.user.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<UserAuth> findFromToken(String token) {
        return em.createQuery("FROM UserAuth where token=:token", UserAuth.class)
                .setParameter("token", token)
                .getResultList();
    }
}
