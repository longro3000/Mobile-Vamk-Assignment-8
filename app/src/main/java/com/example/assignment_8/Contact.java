package com.example.assignment_8;

public class Contact {
    String firstName, lastName, phoneNumber, educationLevel, hobbies;

    public Contact(String first, String last, String phone, String edu, String hobbies) {
        this.firstName = first;
        this.lastName = last;
        this.phoneNumber = phone;
        this.educationLevel = edu;
        this.hobbies = hobbies;
    }

    public boolean Search (String term) {
        if ((this.firstName.contains(term)) || (this.lastName.contains(term)) || (this.phoneNumber.contains(term))) {
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return this.firstName + "\t || \t" + this.lastName + "\t || \t" + this.phoneNumber + "\t || \t" + this.educationLevel + "\t || \t" + this.hobbies;
    }
}
