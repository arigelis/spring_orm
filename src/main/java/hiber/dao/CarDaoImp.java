package hiber.dao;

import hiber.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public Optional<Car> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.get(Car.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Car> findByIdAndSeries(Long id, Integer series) {
        Session session = sessionFactory.getCurrentSession();

        TypedQuery<Car> query = sessionFactory.getCurrentSession()
                .createQuery("from Car where id = ? and series = ?")
                .setParameter(0, id)
                .setParameter(1, series);
        return Optional.ofNullable(query.getSingleResult());
    }

}
