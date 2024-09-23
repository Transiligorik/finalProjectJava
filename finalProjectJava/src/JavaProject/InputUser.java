package JavaProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InputUser {

    private Scanner scanner = new Scanner(System.in);
    private List listOfAnimals = new ArrayList<>();
    private List listOfHumans = new ArrayList<>();
    private List listOfBarrels = new ArrayList<>();
    Random random = new Random();

    // исправить ввести sout на то чтобы ввел цифры при выборе главного меню
    public void inputNumder() {
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
        System.out.println("Выберите какой объект необходимо добавить в массив :");
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

        // обработать при указании длины массива в файле меньше обектов -- - - ОШИБКА

        if (path > 0 && path < 4) {
            switch (path) {
                case 1: {
                    createObjectAnimalFromFile();
                    System.out.println(listOfAnimals);
                    break;
                }
                case 2: {
                    createObjectBarrelFromFile();
                    System.out.println(listOfBarrels);
                    break;
                }
                case 3: {
                    createObjectHumanFromFile();
                    System.out.println(listOfHumans);
                    break;
                }
            }
        } else {
            throw new UserInputException("Неправильный тип данных.");
        }
    }

    private void createObjectAnimalFromFile() throws FileNotFoundException, UserInputException {
        int arraySize = inputLengthArray();
        URL url = getClass().getResource("Animal.txt");
        File file = new File(url.getPath());
        Scanner scannerFromFile = new Scanner(file);
        for (int i = 0; i < arraySize; i++) {
            String typeAnaimalFromFile = scannerFromFile.next();
            String typeColorFromFile = scannerFromFile.next();
            Boolean haveWoolFromFile = scannerFromFile.nextBoolean();
            listOfAnimals.addAll(List.of(typeAnaimalFromFile, typeColorFromFile, haveWoolFromFile));
        }
    }

    private void createObjectBarrelFromFile() throws FileNotFoundException, UserInputException {
        int arraySize = inputLengthArray();
        URL url = getClass().getResource("Barrel.txt");
        File file = new File(url.getPath());
        Scanner scannerFromFile = new Scanner(file);
        for (int i = 0; i < arraySize; i++) {
            int volumeBarrelFromFile = scannerFromFile.nextInt();
            String keepMaterialFromFile = scannerFromFile.next();
            String materialFromWhichMadeFromFile = scannerFromFile.next();
            listOfBarrels.addAll(List.of(volumeBarrelFromFile, keepMaterialFromFile, materialFromWhichMadeFromFile));
        }
    }

    private void createObjectHumanFromFile() throws FileNotFoundException, UserInputException {
        int arraySize = inputLengthArray();
        URL url = getClass().getResource("Human.txt");
        File file = new File(url.getPath());
        Scanner scannerFromFile = new Scanner(file);
        for (int i = 0; i < arraySize; i++) {
            int genderHumanFromFile = scannerFromFile.nextInt();
            String ageHumanFromFile = scannerFromFile.next();
            String SurnameHumanromFile = scannerFromFile.next();
            listOfHumans.addAll(List.of(genderHumanFromFile, ageHumanFromFile, SurnameHumanromFile));
        }
    }

    private void inputArrayManually() throws UserInputException {
        String objectType = chooseObjectType();

        switch (objectType) {

            case "Животное": {
                createAnimalInManuallyArray();
                break;
            }

            case "Бочка": {
                createBarrelInManuallyArray();
                break;
            }
            case "Человек": {
                createHumanInManuallyArray();
                break;
            }
        }
    }

    private void inputArrayRandom() throws UserInputException {
        int randomChoice = random.nextInt(3) + 1;

        switch (randomChoice) {
            case 1: {
                System.out.println("Сгенерирован выбор заполнения массива объектом 'Животное'.");
                createAnimalRandomArray();
                System.out.println(listOfAnimals);
                break;
            }
            case 2: {
                System.out.println("Сгенерирован выбор заполнения массива объектом 'Бочка'.");
                createBarrelRandomArray();
                System.out.println(listOfBarrels);
                break;
            }
            case 3: {
                System.out.println("Сгенерирован выбор заполнения массива объектом 'Человек'.");
                createHumanRandomArray();
                System.out.println(listOfHumans);
                break;
            }
        }
    }

    private void createAnimalInManuallyArray() throws UserInputException {
        int arraySize = inputLengthArray();
        for (int i = 1; i <= arraySize; i++) {
            System.out.println("Введите данные для животного номер " + i);
            System.out.println();
            System.out.println("Введите тип животного: ");
            String typeAnimal = scanner.next();
            System.out.println("Введите цвет глаз :");
            String colorsEyeAnimal = scanner.next();
            System.out.println("Введите наличие шерсти ('true' / 'false') :");
            if (!scanner.hasNextBoolean()) {
                scanner.nextLine();
                throw new UserInputException("Неправильный тип данных.");
            }
            Boolean haveWoolAnimal = scanner.nextBoolean();
            listOfAnimals.add("Животное");

        }
    }

    private void createBarrelInManuallyArray() throws UserInputException {

        int arraySize = inputLengthArray();
        for (int i = 1; i <= arraySize; i++) {
            System.out.println("Введите данные для бочки номер " + i);
            System.out.println();
            System.out.println("Введите объем :");
            int volumeBarrel = scanner.nextInt();
            System.out.println("Введите хранимый материал :");
            String keepMaterialBarrel = scanner.next();
            System.out.println("Введите материал из которого она изготовлена :");
            String materialFromWhichMadeBarrel = scanner.next();
            listOfBarrels.add("Бочка");
        }
    }

    private void createHumanInManuallyArray() throws UserInputException {

        int arraySize = inputLengthArray();
        for (int i = 1; i <= arraySize; i++) {
            System.out.println("Введите данные для человека номер " + i);
            System.out.println();
            System.out.println("Введите пол :");
            String genderHuman = scanner.next();
            System.out.println("Введите возраст :");
            int ageHuman = scanner.nextInt();
            System.out.println("Введите фамилию :");
            String surnameHuman = scanner.next();
            listOfHumans.add("Человек");
        }
    }

    private void createAnimalRandomArray() throws UserInputException {

        int arraySize = inputLengthArray();
        for (int i = 0; i < arraySize; i++) {

            String[] randomTypeAnimalArray = {"Енот", "Волк", "Жираф"};
            int positionType = random.nextInt(randomTypeAnimalArray.length);
            listOfAnimals.add(randomTypeAnimalArray[positionType]);

            String[] randomcolorsEyeAnimalArray = {"Желтый", "Черный", "Серый"};
            int positionColor = random.nextInt(randomcolorsEyeAnimalArray.length);
            listOfAnimals.add(randomcolorsEyeAnimalArray[positionColor]);

            Boolean[] randomHaveWoolArray = {true, false};
            int positionHave = random.nextInt(randomHaveWoolArray.length);
            listOfAnimals.add(randomHaveWoolArray[positionHave]);
        }
    }

    private void createBarrelRandomArray() throws UserInputException {

        int arraySize = inputLengthArray();
        for (int i = 0; i < arraySize; i++) {

            int randomVolumeBarrel = random.nextInt(100);
            listOfBarrels.add(randomVolumeBarrel);

            String[] randomKeepMaterialBarrelArray = {"Кофе", "Чай", "Рис"};
            int positionKeep = random.nextInt(randomKeepMaterialBarrelArray.length);
            listOfBarrels.add(randomKeepMaterialBarrelArray[positionKeep]);

            String[] randomMaterialFromWhichMadeBarrel = {"Дерево", "Металл", "Пластмасса"};
            int positionMaterial = random.nextInt(randomMaterialFromWhichMadeBarrel.length);
            listOfBarrels.add(randomMaterialFromWhichMadeBarrel[positionMaterial]);
        }
    }

    private void createHumanRandomArray() throws UserInputException {

        int arraySize = inputLengthArray();
        for (int i = 0; i < arraySize; i++) {

            String[] randomGenderHumanArray = {"Мужской", "Женский"};
            int positionGender = random.nextInt(randomGenderHumanArray.length);
            listOfHumans.add(randomGenderHumanArray[positionGender]);

            int randomAgeHuman = random.nextInt(100);
            listOfHumans.add(randomAgeHuman);

            String[] randomSurnameHuman = {"Иванов", "Смирнов", "Сидоров"};
            int positionSurname = random.nextInt(randomSurnameHuman.length);
            if (randomGenderHumanArray[positionGender] == "Женский") {
                listOfHumans.add(randomSurnameHuman[positionSurname] + "a");
            } else {
                listOfHumans.add(randomSurnameHuman[positionSurname]);
            }
        }
    }

    private void searchElement() throws UserInputException {
        System.out.println();
        System.out.println("Вы выбрали опцию поиск элемента.");
        chooseObjectType();
    }
}