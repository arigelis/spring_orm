package hiber.service;

import hiber.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    void add(Car car);

    List<Car> listCars();

    Optional<Car> findById(Long id);

    Optional<Car> findByIdAndSeries(Long id, Integer series);
}
