package com.app.springbootjunit5mockito.model;

public class User {
    String name;
    int age;
    String firstName;
    String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String name, int age, String firstName, String lastName) {
        this.name = name;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {}
}
