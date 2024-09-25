package main.java.our.project;


import main.java.our.project.exception.UserInputException;
import main.java.our.project.util.SortUtil;
import main.java.our.project.model.Animal;
import main.java.our.project.model.Barrel;
import main.java.our.project.model.Gender;
import main.java.our.project.model.Person;
import main.java.our.project.util.BinarySearchUtil;
import main.java.our.project.util.RandomDataGenerator;


import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Application {

    private Scanner scanner = new Scanner(System.in);
    private List<Animal> animals = new ArrayList<>();
    private List<Person> people = new ArrayList<>();
    private List<Barrel> barrels = new ArrayList<>();
    Random random = new Random();


    public void run() {
        boolean exit = false;

        while (!exit) {
            try {

                showMainMenu();

                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    throw new UserInputException("Неправильный тип данных.");
                }

                int inputNumber = scanner.nextInt();
                if (inputNumber > 0 && inputNumber < 4) {
                    switch (inputNumber) {
                        //Вход в выбор зполнения массива
                        case 1:
                            inputDataInArray();
                            break;
                        //Вход в выбор поиска элемента
                        case 2:
                            searchElement();
                            break;

                        //Выход из программы
                        case 3:
                            System.out.println();
                            System.out.println("Вы вышли из программы.");
                            exit = true;
                            break;
                    }
                } else {
                    throw new UserInputException("Неправильный тип данных.");
                }
            } catch (UserInputException e) {
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден.");
            } catch (NoSuchElementException e) {
                System.out.println("Введенная длинна массива превышает количество элементов в файле.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("Пожалуйста, выберете необходимый пункт в меню : ");
        System.out.println();
        System.out.println("1. Заполнение массива.");
        System.out.println("2. Поиск элемента.");
        System.out.println("3. Выход.");
    }

    private void inputDataInArray() throws FileNotFoundException, UserInputException {
        int inputNumber;
        System.out.println();
        System.out.println("Вы выбрали опцию вариант заполнения массива.");
        System.out.println();
        System.out.println("Выберете вариант заполнения массива :");
        System.out.println();

        System.out.println("Введите 1, если хотите сделать выбор из файла.");
        System.out.println("Введите 2, если хотите сделать выбор рандом.");
        System.out.println("Введите 3, если хотите сделать выбор вручную.");

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            throw new UserInputException("Неправильный тип данных.");
        }
        inputNumber = scanner.nextInt();

        if (inputNumber > 0 && inputNumber < 4) {
            switch (inputNumber) {
                case 1:
                    System.out.println();
                    System.out.println("Вы выбрали опцию заполнение массива из файла");
                    System.out.println();
                    inputArrayFromFile();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Вы выбрали опцию заполнение массива рандом");
                    System.out.println();

                    inputArrayRandom();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Вы выбрали опцию заполнение массива вручную");

                    System.out.println();
                    inputArrayManually();
                    System.out.println();
                    break;
            }
        } else {
            throw new UserInputException("Некорректный ввод данных. Вводимые данные должны быть от '1' до '3'");
        }
    }

    private int inputLengthArray() throws UserInputException {
        System.out.println();
        System.out.println("Введите длину массива");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            throw new UserInputException("Неправильный тип данных.");
        }
        int arrayLength = scanner.nextInt();
        return arrayLength;
    }

    private String chooseObjectType() throws UserInputException {
        showMenuChoice();
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            throw new UserInputException("Неправильный тип данных.");
        }
        int inputNumber = scanner.nextInt();
        if (inputNumber > 0 && inputNumber < 4) {
            switch (inputNumber) {
                case 1:
                    return "Животное";

                case 2:
                    return "Бочка";

                case 3:
                    return "Человек";
            }
        } else {
            throw new UserInputException("Неправильный тип данных.");
        }
        return "0";
    }

    private void showMenuChoice() {
        System.out.println("Выберите c каким типом объекта Вы хотите работать:");
        System.out.println();
        System.out.println("Введите 1, если хотите выбрать объект 'Животное'.");
        System.out.println("Введите 2, если хотите выбрать объект 'Бочка'.");
        System.out.println("Введите 3, если хотите выбрать объект 'Человек'.");
    }

    private void inputArrayFromFile() throws FileNotFoundException, UserInputException {
        showMenuChoice();
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            throw new UserInputException("Неправильный тип данных.");
        }
        int path = scanner.nextInt();

        if (path > 0 && path < 4) {
            switch (path) {
                case 1: {
                    fillAnimalsFromFile();
                    SortUtil.sort(animals);
                    System.out.println(animals);
                    break;
                }
                case 2: {
                    fillBarrelsFromFile();
                    SortUtil.sort(barrels);
                    System.out.println(barrels);
                    break;
                }
                case 3: {
                    fillPersonsFromFile();
                    SortUtil.sort(people);
                    System.out.println(people);
                    break;
                }
            }
        } else {
            throw new UserInputException("Неправильный тип данных.");
        }
    }

    private void fillAnimalsFromFile() throws FileNotFoundException, UserInputException {
        animals.clear();
        int arraySize = inputLengthArray();
        URL url = getClass().getResource("Animal.txt");
        File file = new File(url.getPath());
        Scanner scannerFromFile = new Scanner(file);
        for (int i = 0; i < arraySize; i++) {
            animals.add(Animal.builder()
                    .setType(scannerFromFile.next())
                    .setEyeColor(scannerFromFile.next())
                    .setHasFur(scannerFromFile.nextBoolean())
                    .build());
        }
    }

    private void fillBarrelsFromFile() throws FileNotFoundException, UserInputException {
        barrels.clear();
        int arraySize = inputLengthArray();
        URL url = getClass().getResource("Barrel.txt");
        File file = new File(url.getPath());
        Scanner scannerFromFile = new Scanner(file);
        for (int i = 0; i < arraySize; i++) {
            barrels.add(Barrel.builder()
                    .setVolume(scannerFromFile.nextInt())
                    .setContent(scannerFromFile.next())
                    .setBarrelMaterial(scannerFromFile.next())
                    .build());
        }
    }

    private void fillPersonsFromFile() throws FileNotFoundException, UserInputException {
        people.clear();
        int arraySize = inputLengthArray();
        URL url = getClass().getResource("Human.txt");
        File file = new File(url.getPath());
        Scanner scannerFromFile = new Scanner(file);
        for (int i = 0; i < arraySize; i++) {
            people.add(Person.builder()
                    .setGender(Gender.get(scannerFromFile.next()))
                    .setAge(scannerFromFile.nextInt())
                    .setLastName(scannerFromFile.next())
                    .build());
        }
    }

    private void inputArrayManually() throws UserInputException {
        String objectType = chooseObjectType();

        switch (objectType) {

            case "Животное": {
                fillAnimalsManually();
                break;
            }

            case "Бочка": {
                fillBarrelsManually();
                break;
            }
            case "Человек": {
                fillsPersonsManually();
                break;
            }
        }
    }

    private void inputArrayRandom() throws UserInputException {
        int randomChoice = random.nextInt(3) + 1;

        switch (randomChoice) {
            case 1: {
                System.out.println("Сгенерирован выбор заполнения массива объектом 'Животное'.");
                fillAnimalsRandom();
                SortUtil.sort(animals);
                System.out.println(animals);
                break;
            }
            case 2: {
                System.out.println("Сгенерирован выбор заполнения массива объектом 'Бочка'.");
                fillBarrelsRandom();
                SortUtil.sort(barrels);
                System.out.println(barrels);
                break;
            }
            case 3: {
                System.out.println("Сгенерирован выбор заполнения массива объектом 'Человек'.");
                fillPersonsRandom();
                SortUtil.sort(people);
                System.out.println(people);
                break;
            }
        }
    }

    private void fillAnimalsManually() throws UserInputException {
        animals.clear();
        int arraySize = inputLengthArray();
        for (int i = 1; i <= arraySize; i++) {
            System.out.println("Введите данные для животного номер " + i);
            System.out.println();
            animals.add(createAnimalFromConsole());
        }
    }

    private void fillBarrelsManually() throws UserInputException {
        barrels.clear();
        int arraySize = inputLengthArray();
        for (int i = 1; i <= arraySize; i++) {
            System.out.println("Введите данные для бочки номер " + i);
            System.out.println();
            barrels.add(createBarrelFromConsole());
        }
    }

    private void fillsPersonsManually() throws UserInputException {
        people.clear();
        int arraySize = inputLengthArray();
        for (int i = 1; i <= arraySize; i++) {
            System.out.println("Введите данные для человека номер " + i);
            System.out.println();
            people.add(createPersonFromConsole());
        }
    }

    private void fillAnimalsRandom() throws UserInputException {
        animals.clear();
        int arraySize = inputLengthArray();
        for (int i = 0; i < arraySize; i++) {
            animals.add(RandomDataGenerator.getRandomAnimal());
        }
    }

    private void fillBarrelsRandom() throws UserInputException {
        barrels.clear();
        int arraySize = inputLengthArray();
        for (int i = 0; i < arraySize; i++) {
            barrels.add(RandomDataGenerator.getRandomBarrel());
        }
    }

    private void fillPersonsRandom() throws UserInputException {
        people.clear();
        int arraySize = inputLengthArray();
        for (int i = 0; i < arraySize; i++) {
            people.add(RandomDataGenerator.getRandomPerson());
        }
    }

    private void searchElement() throws UserInputException {
        System.out.println();
        System.out.println("Вы выбрали опцию поиск элемента.");
        String objectType = chooseObjectType();
        int elementPosition = -1;
        switch (objectType) {

            case "Животное": {
                elementPosition = BinarySearchUtil.binarySearch(animals, createAnimalFromConsole());
                break;
            }

            case "Бочка": {
                elementPosition = BinarySearchUtil.binarySearch(barrels, createBarrelFromConsole());
                break;
            }
            case "Человек": {
                elementPosition = BinarySearchUtil.binarySearch(people, createPersonFromConsole());
                break;
            }
        }
        System.out.println(elementPosition == -1 ? "Объект не найден." :
                "Позиция объекта в списке после сортировки: " + elementPosition);;
    }

    private Animal createAnimalFromConsole() throws UserInputException {
        System.out.println("Введите тип животного: ");
        String type = scanner.next();
        System.out.println("Введите цвет глаз :");
        String eyeColor = scanner.next();
        System.out.println("Введите наличие шерсти ('true' / 'false') :");
        if (!scanner.hasNextBoolean()) {
            scanner.nextLine();
            throw new UserInputException("Неправильный тип данных.");
        }
        boolean hasFur = scanner.nextBoolean();
        return Animal.builder()
                .setType(type)
                .setEyeColor(eyeColor)
                .setHasFur(hasFur)
                .build();
    }

    private Barrel createBarrelFromConsole() throws UserInputException {
        System.out.println("Введите объем :");
        int volume = scanner.nextInt();
        System.out.println("Введите хранимый материал :");
        String content = scanner.next();
        System.out.println("Введите материал из которого она изготовлена :");
        String madeFrom = scanner.next();
        return Barrel.builder()
                .setVolume(volume)
                .setBarrelMaterial(madeFrom)
                .setContent(content)
                .build();
    }

    private Person createPersonFromConsole() throws UserInputException {
        System.out.println("Введите пол :");
        String gender = scanner.next();
        System.out.println("Введите возраст :");
        int age = scanner.nextInt();
        System.out.println("Введите фамилию :");
        String lastName = scanner.next();
        return Person.builder()
                .setAge(age)
                .setLastName(lastName)
                .setGender(Gender.get(gender))
                .build();
    }
}