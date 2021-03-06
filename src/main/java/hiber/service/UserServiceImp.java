package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private CarService carService;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Car is not present"));
    }

    @Override
    public User getUserByCarIdAndCarSeries(Long id, Integer series) {
        return userDao.findByIdAndSeries(id, series).orElseThrow(() -> new IllegalArgumentException("Car is not present"));
    }
}
