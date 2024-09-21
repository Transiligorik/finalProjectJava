package JavaProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputUser {

    private Scanner scanner = new Scanner(System.in);
    private List listOfAnimals = new ArrayList<>();
    private List listOfHumans = new ArrayList();
    private List listOfBarrels = new ArrayList<>();

    public void inputNumder() {


        boolean exit = false;

        while (!exit) {

            //Вход в приложение
            showMainMenu();

            int inputNumber = scanner.nextInt();

            // invoke exeption incorrect inputNumber String or Char, another Number

            switch(inputNumber) {
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
                    System.out.println("Вы вышли из программы");
                    exit = true;
                    break;
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

    private void inputDataInArray() {
        int inputNumber;
        System.out.println();
        System.out.println("Вы выбрали опцию вариант заполнения массива.");
        System.out.println();
        System.out.println("Выберете вариант заполнения массива : ");
        System.out.println();

        System.out.println("Введите 1, если хотите сделать выбор из файла.");
        System.out.println("Введите 2, если хотите сделать выбор рандом.");
        System.out.println("Введите 3, если хотите сделать выбор вручную.");

        inputNumber = scanner.nextInt();

        // сделать проверку на написания слово -- файл
        // invoke exeption

        if (inputNumber > 0 && inputNumber < 4) {
            switch (inputNumber) {
                case 1:
                    System.out.println();
                    System.out.println("Вы выбрали опцию заполнение массива из файла");
                    inputLengthArray();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Вы выбрали опцию заполнение массива рандом");
                    inputLengthArray();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Вы выбрали опцию заполнение массива вручную");

                    inputLengthArray();
                    System.out.println();
                    System.out.println();
                    inputArrayManually();
                    break;
            }
        }
    }

    private int inputLengthArray() {
        System.out.println();
        System.out.println("Введите длину массива");
        int arrayLength = scanner.nextInt();
        return arrayLength;
    }

    private String chooseObjectType() {
        System.out.println("Выберите какой объект необходимо добавить в массив :");
        System.out.println();
        System.out.println("Введите 1, если хотите выбрать объект 'Животное'.");
        System.out.println("Введите 2, если хотите выбрать объект 'Бочка'.");
        System.out.println("Введите 3, если хотите выбрать объект 'Человек'.");
        int inputNumber = scanner.nextInt();
        switch(inputNumber) {

            case 1:
                return "Животное";

            case 2:
                return "Бочка";

            case 3:
                return "Человек";
        }

        return "0";
    }

    private void inputArrayManually() {

        String objectType = chooseObjectType();
        int arraySize = inputLengthArray();

        switch (objectType) {
            case "Животное": {
                for(int i = 0; i < arraySize; i++) {

                }
                break;
            }
            case "Бочка" : {

                break;
            }
            case "Человек" : {

                break;
            }
        }
    }

    private void searchElement() {
        System.out.println();
        System.out.println("Вы выбрали опцию поиск элемента.");
        chooseObjectType();
    }
}
