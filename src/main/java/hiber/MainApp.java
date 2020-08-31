package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);
        User tmpUser = new User("User1", "Lastname1", "user1@mail.ru");
        tmpUser.setCar(new Car());
        tmpUser.getCar().setSeries(1);
        userService.add(tmpUser);
//
//        tmpUser = new User("User2", "Lastname2", "user2@mail.ru", new Car());
////        tmpUser.getCar().setUser(tmpUser);
//        tmpUser.getCar().setSeries(2);
//        userService.add(tmpUser);
//
//        tmpUser = new User("User3", "Lastname3", "user3@mail.ru", new Car());
////        tmpUser.getCar().setUser(tmpUser);
//        tmpUser.getCar().setSeries(3);
//        userService.add(tmpUser);
//
//        tmpUser = new User("User4", "Lastname4", "user4@mail.ru", new Car());
////        tmpUser.getCar().setUser(tmpUser);
//        tmpUser.getCar().setSeries(4);
//        userService.add(tmpUser);
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car()));
//        List<Car> cars = carService.listCars();


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }


        User a = userService.getUserBySeriesAndId(1L, 1);

        context.close();
    }
}
