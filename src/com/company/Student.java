package com.company;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Student {
    public Student(){}
    public Student(String name, String surname, int age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    private String name;
    private String surname;
    private int age;


    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    @XmlAttribute
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    @XmlElement(required = true)
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + ", " + surname + ", age: " + age;
    }
}
