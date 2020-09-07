package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
    public Optional<User> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.get(User.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<User> findByIdAndSeries(Long id, Integer series) {
        Session session = sessionFactory.getCurrentSession();


        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select u from User u INNER JOIN u.car c WHERE c.id = ? and c.series = ?")
                .setParameter(0, id)
                .setParameter(1, series);
        return Optional.ofNullable(query.getSingleResult());
    }
}
