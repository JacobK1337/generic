package de.cronn.person;

public class YoungPerson extends Person implements Student{
    public YoungPerson(String name, String surname) {
        super(name, surname);
    }

    @Override
    public void study() {
        System.out.println("Im studying");
    }
}
