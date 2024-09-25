package main.java.our.project.model;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable<Person>, HasFieldForEvenSort{
    private int age;
    private String lastName;
    private Gender gender;

    public Person(PersonBuilder personBuilder) {
        age = personBuilder.age;
        lastName = personBuilder.lastName;
        gender = personBuilder.gender;
    }

    public Person(int age, String lastName, Gender gender) {
        this.age = age;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(lastName, person.lastName) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, lastName, gender);
    }

    @Override
    public int compareTo(Person o) {
        return Comparator.comparingInt(Person::getAge)
                .thenComparing(Person::getLastName, Comparator.naturalOrder())
                .thenComparing(Person::getGender, Comparator.naturalOrder()).compare(this,o);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                '}';
    }

    public static PersonBuilder builder(){
        return new PersonBuilder();
    }

    @Override
    public long getFieldForEvenSort() {
        return age;
    }

    public static class PersonBuilder{
        private int age;
        private String lastName;
        private Gender gender;

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }
        public PersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public PersonBuilder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }
        public Person build(){
            return new Person(this);
        }
    }
}
