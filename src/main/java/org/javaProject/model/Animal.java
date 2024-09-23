package org.javaProject.model;

import java.util.Comparator;
import java.util.Objects;

public class Animal implements Comparable<Animal>{
    private String type;
    private String eyeColor;
    private boolean hasFur;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public boolean isHasFur() {
        return hasFur;
    }

    public void setHasFur(boolean is) {
        this.hasFur = is;
    }

    public Animal(AnimalBuilder animalBuilder) {
        type = animalBuilder.type;
        eyeColor = animalBuilder.eyeColor;
        hasFur = animalBuilder.hasFur;
    }

    public Animal(String type, String eyeColor, boolean hasFur) {
        this();
        this.type = type;
        this.eyeColor = eyeColor;
        this.hasFur = hasFur;
    }
    public Animal() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return hasFur == animal.hasFur && Objects.equals(type, animal.type) && Objects.equals(eyeColor, animal.eyeColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, eyeColor, hasFur);
    }

    @Override
    public int compareTo(Animal o) {
        return Comparator.comparing(Animal::getType)
                .thenComparing(Animal::getEyeColor)
                .thenComparing(Animal::isHasFur).compare(this,o);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hasFur=" + hasFur +
                '}';
    }

    public static AnimalBuilder builder(){
        return new AnimalBuilder();
    }
    public static class AnimalBuilder{
        private String type;
        private String eyeColor;
        private boolean hasFur;

        public AnimalBuilder setType(String type) {
            this.type = type;
            return this;
        }
        public AnimalBuilder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }
        public AnimalBuilder setHasFur(boolean hasFur) {
            this.hasFur = hasFur;
            return this;
        }

        public Animal build(){
            return new Animal(this);
        }
    }
}
