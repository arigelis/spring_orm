package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserBySeriesAndId(Long id, Integer series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "FROM  User u INNER JOIN Car c on u.car_id = c.id WHERE c.series = :series and c.id = :id");
        query.setParameter("series", series);
        query.setParameter("id", id);

        return query.getSingleResult();
    }
}
