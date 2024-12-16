//import org.example.entity.Car;
//import org.example.repository.CarRepository;
//import org.example.service.CarService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class CarServiceTest {
//
//    @Test
//    public void testGetAllCars() {
//        // Создаём mock-репозиторий
//        CarRepository mockRepository = Mockito.mock(CarRepository.class);
//        when(mockRepository.findAll()).thenReturn(List.of(new Car("TestModel", "TestBrand")));
//
//        // Создаём сервис с mock-репозиторием
//        CarService carService = new CarService(mockRepository);
//        List<Car> cars = carService.getAllCars();
//
//        // Проверяем результат
//        assertEquals(1, cars.size());
//        assertEquals("TestModel", cars.get(0).getModel());
//    }
//
//    @Test
//    public void testAddCar() {
//        CarRepository mockRepository = Mockito.mock(CarRepository.class);
//        Car car = new Car("ModelX", "Tesla");
//
//        when(mockRepository.save(car)).thenReturn(car);
//
//        CarService carService = new CarService(mockRepository);
//        Car savedCar = carService.addCar(car);
//
//        assertEquals("ModelX", savedCar.getModel());
//        verify(mockRepository, times(1)).save(car);
//    }
//}
//
