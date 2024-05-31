import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CarDealershipConsole {
    private final CarDealership cd = new CarDealership();
    int currentCode = 0;
    Scanner sc = new Scanner(System.in);

    public void initializeCarDealerShip() throws IOException {
        // Получать данные из СУДБ и заполнять списки машин, пользователей - 2 атта
        cd.addCar("BMW", "M5F90");
        cd.addCar("BMW",  "X5M");
        cd.addCar("Mercedes", "E63S AMG");
        cd.addCar("Mercedes", "GLE");
        cd.addCar("Toyota", "Mark ||");

        cd.addCustomer("Иванов Сидор Петрович", 25, true);
        cd.addCustomer("Семенов Евгений Иванович", 35, true);
        cd.addCustomer("Черных Мария Павловна", 37, false);
        cd.addCustomer("Теплая Ирина Михайловна", 22, false);

        while (currentCode != -1) {
            System.out.println("------------------ Добро пожаловать в автосалон DivanCar ------------------");
            System.out.println();
            System.out.println("Выберите действие (введите номер): ");
            System.out.println("1. Посмотреть список машин");
            System.out.println("2. Посмотреть список клиентов");
            System.out.println("3. Продать автомобиль");
            System.out.println("4. Добавить автомобиль");
            System.out.println("5. Добавить клиента");
            System.out.println("6. Посмотреть проданные автомобили");
            System.out.println("7. Выйти");
            System.out.println("---------------------------------------------------------------------------");
            currentCode = Integer.parseInt(sc.nextLine());

            switch (currentCode) {
                case (1):
                    checkCars();
                    break;
                case (2):
                    checkCustomers();
                    break;
                case (3):
                    sellCar();
                    break;
                case (4):
                    addCar();
                    break;
                case (5):
                    addCustomer();
                    break;
                case (6):
                    checkSoldCars();
                    break;
                case (7):
                    currentCode = -1;
                    break;
                default:
                    System.out.println("Введено неверное значение");
            }
        }

    }

    public void checkCars() {
        ArrayList<Car> cars = cd.getCars();
        while (currentCode != -2) {
            showCars();

            if (currentCode == 0) {
                currentCode = -2;
            } else if (currentCode - 1 >= 0 && currentCode <= cars.size()) {
                workWithCar(currentCode - 1);
            }
        }
    }

    public void showCars() {
        ArrayList<Car> cars = cd.getCars();
        System.out.println("--------------------------- Список автомобилей --------------------------------");
        System.out.println();
        System.out.println("Вы можете выбрать какой-либо автомобиль, либо вернуться назад");
        System.out.println();
        System.out.println(0 + ". Назад");
        for (Car car : cars) {
            System.out.println("Номер: " + (car.getID() + 1) + " Марка: " + car.getBrand() + "; Модель: " + car.getModel());
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        currentCode = Integer.parseInt(sc.nextLine());
    }

    public void workWithCar(int id) {
        Car currentCar = cd.getCars().get(id);
        String newValue = "";
        while (currentCode != -2) {
            System.out.println("----------------------------- Автомобиль ----------------------------------");
            System.out.println();
            System.out.println("Выберите действие с автомобилем:");
            System.out.println();
            System.out.println("1. Редактировать");
            System.out.println("2. Удалить");
            System.out.println("3. Назад");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------");
            currentCode = Integer.parseInt(sc.nextLine());

            if (currentCode == 3) {
                currentCode = -2;
            } else if (currentCode >= 1 && currentCode <= 2) {
                switch (currentCode) {
                    case (1):
                        while (currentCode != -3) {
                            System.out.println("----------------------------- Характеристики ----------------------------------");
                            System.out.println();
                            System.out.println("Выберите нужную характеристику:");
                            System.out.println();
                            System.out.println("1. Марка");
                            System.out.println("2. Модель");
                            System.out.println("3. Назад");
                            System.out.println();
                            System.out.println("-------------------------------------------------------------------------------");
                            currentCode = Integer.parseInt(sc.nextLine());
                            if (currentCode == 3) {
                                break;
                            } else if (currentCode > 3 || currentCode < 1) {
                                continue;
                            }
                            System.out.println("Введите новое значение: ");
                            newValue = sc.nextLine();

                            switch (currentCode) {
                                case (1):
                                    currentCar.setBrand(newValue);
                                    break;
                                case (2):
                                    currentCar.setModel(newValue);
                                    break;
                            }
                        }
                        break;
                    case (2):
                        cd.deleteCar(id);
                }
            }
        }
    }

    public void checkCustomers() {
        ArrayList<Customer> customers = cd.getCustomers();
        while (currentCode != -2) {
            showCustomers();

            if (currentCode == 0) {
                currentCode = -2;
            } else if (currentCode - 1 >= 0 && currentCode <= customers.size()) {
                workWithCustomer(currentCode - 1);
            }
        }
    }

    public void showCustomers() {
        ArrayList<Customer> customers = cd.getCustomers();
        System.out.println("--------------------------- Список автомобилей --------------------------------");
        System.out.println();
        System.out.println("Вы можете выбрать какой-либо автомобиль, либо вернуться назад");
        System.out.println();
        System.out.println(0 + ". Назад");
        for (Customer customer : customers) {
            String gender = (customer.getGender() ? "Мужской" : "Женский");
            System.out.println("Номер: " + (customer.getID() + 1) + " Имя: " + customer.getName() + "; Возраст: " + customer.getAge() + "; Пол: " + gender);
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        currentCode = Integer.parseInt(sc.nextLine());
    }
    public void workWithCustomer(int id) {
        Customer currentCustomer = cd.getCustomers().get(id);
        String newValue = "";
        while (currentCode != -2) {
            System.out.println("----------------------------- Покупатели ----------------------------------");
            System.out.println();
            System.out.println("Выберите действие с покупателями:");
            System.out.println();
            System.out.println("1. Редактировать");
            System.out.println("2. Удалить");
            System.out.println("3. Назад");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------");
            currentCode = Integer.parseInt(sc.nextLine());

            if (currentCode == 3) {
                currentCode = -2;
            } else if (currentCode >= 1 && currentCode <= 2) {
                switch (currentCode) {
                    case (1):
                        while (currentCode != -3) {
                            System.out.println("----------------------------- Характеристики ----------------------------------");
                            System.out.println();
                            System.out.println("Выберите нужную характеристику:");
                            System.out.println();
                            System.out.println("1. Имя");
                            System.out.println("2. Возраст");
                            System.out.println("3. Пол");
                            System.out.println("4. Назад");
                            System.out.println();
                            System.out.println("-------------------------------------------------------------------------------");
                            currentCode = Integer.parseInt(sc.nextLine());
                            if (currentCode == 4) {
                                break;
                            } else if (currentCode > 4 || currentCode < 1) {
                                continue;
                            }
                            System.out.println("Введите новое значение: ");
                            newValue = sc.nextLine();

                            switch (currentCode) {
                                case (1):
                                    currentCustomer.setName(newValue);
                                    break;
                                case (2):
                                    currentCustomer.setAge(Integer.parseInt(newValue));
                                    break;
                                case (3):
                                    boolean isMale = true;
                                    if (!newValue.equals("Мужской") && !newValue.equals("Женский")) {
                                        break;
                                    } else if (newValue.equals("Женский")) {
                                        isMale =  false;
                                    }
                                    currentCustomer.setGender(isMale);
                                    break;
                            }
                        }
                        break;
                    case (2):
                        cd.deleteCustomer(id);
                }
            }
        }
    }

    public void addCar() {
        String model = "";
        String brand = "";
        System.out.println("--------------------------- Добавление нового автомобиля --------------------------------");
        System.out.println();
        System.out.println("Введите марку автомобиля: ");
        brand = sc.nextLine();
        System.out.println("Введите модель автомобиля: ");
        model = sc.nextLine();
        cd.addCar(model, brand);
        System.out.println("Автомобиль добавлен.");
    }

    public void addCustomer() {
        String name = "";
        int age = 0;
        boolean isMale = true;
        System.out.println("--------------------------- Добавление нового клиента --------------------------------");
        System.out.println();
        System.out.println("Введите имя клиента: ");
        name = sc.nextLine();
        System.out.println("Введите возраст: ");
        age = Integer.parseInt(sc.nextLine());
        System.out.println("Введите пол: ");
        if (sc.nextLine().equals("Мужской")) {
            isMale = true;
        } else if (sc.nextLine().equals("Женский")) {
            isMale = false;
        }
        cd.addCustomer(name, age, isMale);
        System.out.println("Клиент добавлен.");
    }

    public void sellCar() {
        ArrayList<Car> cars = cd.getCars();
        ArrayList<Customer> customers = cd.getCustomers();
        Car currentCar = new Car(-1, "", "");
        Customer currentCustomer = new Customer(-1, "", -1, true);
        while (currentCode != -2) {
            System.out.println("--------------------------- Продажа автомобиля --------------------------------");
            System.out.println();
            showCars();
            if (currentCode > 0 && currentCode <= cars.size()) {
                showCustomers();
                currentCar = cars.get(currentCode -1);
                if (currentCode > 0 && currentCode <= cars.size()) {
                    currentCustomer = customers.get(currentCode - 1);
                    cd.sellCar(currentCustomer, currentCar.getModel(), currentCar.getBrand());
                    System.out.println("Автомобиль продан");
                    break;
                } else if (currentCode == 0) {
                    currentCode = -2;
                }
            } else if (currentCode == 0) {
                currentCode = -2;
            }
        }
    }

    public void checkSoldCars() {
        ArrayList<Car> cars = cd.getSoldCars();

        System.out.println("--------------------------- Список проданных автомобилей --------------------------------");
        System.out.println();
        for (Car car : cars) {
            System.out.println("Номер: " + (car.getID() + 1) + " Марка: " + car.getBrand() + "; Модель: " + car.getModel() + "; Владелец: " + car.getCustomer().getName());
        }
        System.out.println();
        System.out.println("Введите Enter, чтобы продолжить");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        String stop = sc.nextLine();
    }
}
