package com.company;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class School {
    public School(){}
    public School(String name, Address address){
        this.name = name;
        this.address = address;
        studentsList = new ArrayList<Student>(30);
    }

    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }
    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void AddStudent(Student student){
        studentsList.add(student);
    }

    private ArrayList<Student> studentsList;
    private String name;
    private Address address;

    @Override
    public String toString() {
        String str;
        str = "Name: " + name + ", address: " + address.toString() + "\r\n";
        for (int i = 0; i < studentsList.size(); i++) {
            str +=  (i+1) + ". " + studentsList.get(i).toString() + "\r\n";
        }
        return str;
    }
}
