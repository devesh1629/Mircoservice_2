package com.devesh.project2.hospital.models;

import java.util.ArrayList;
import java.util.List;

public class Patient {

    private String name;
    private String contactNo;
    private List<Slot> bookings;

    public Patient() {
        bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public List<Slot> getBookings() {
        return bookings;
    }

    public void setBookings(Slot slot) {
        this.bookings.add(slot);
    }
}
