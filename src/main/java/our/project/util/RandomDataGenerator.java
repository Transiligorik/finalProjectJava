package main.java.our.project.util;

import main.java.our.project.model.Animal;
import main.java.our.project.model.Barrel;
import main.java.our.project.model.Gender;
import main.java.our.project.model.Person;

import java.util.Random;

public final class RandomDataGenerator {
    private RandomDataGenerator() {
    }
    private static final String[] randomType = {"Енот", "Волк", "Жираф"};
    private static final String[] randomEyeColor = {"Желтый", "Черный", "Серый"};
    private static final boolean[] randomHasFur = {true, false};

    private static final String[] randomContent = {"Кофе", "Чай", "Рис"};
    private static final String[] randomMaterial = {"Дерево", "Металл", "Пластмасса"};

    private static final String[] randomLastName = {"Иванов", "Смирнов", "Сидоров"};

    private static Random random = new Random();

    public static Animal getRandomAnimal() {
        String type = randomType[random.nextInt(randomType.length)];
        String eyeColor = randomEyeColor[random.nextInt(randomEyeColor.length)];
        boolean hasFur = randomHasFur[random.nextInt(randomHasFur.length)];
        return Animal.builder()
                .setType(type)
                .setEyeColor(eyeColor)
                .setHasFur(hasFur)
                .build();
    }

    public static Barrel getRandomBarrel() {
        String content = randomContent[random.nextInt(randomContent.length)];
        String madeFrom = randomMaterial[random.nextInt(randomMaterial.length)];
        long volume = random.nextInt(10000);
        return Barrel.builder()
                .setVolume(volume)
                .setBarrelMaterial(madeFrom)
                .setContent(content)
                .build();
    }

    public static Person getRandomPerson() {
        int age = random.nextInt(100);
        String lastName = randomLastName[random.nextInt(randomLastName.length)];
        Gender gender = Gender.values()[random.nextInt(Gender.values().length)];
        return Person.builder()
                .setAge(age)
                .setLastName(lastName)
                .setGender(gender)
                .build();
    }
}
