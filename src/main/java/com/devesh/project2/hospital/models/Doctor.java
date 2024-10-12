package com.devesh.project2.hospital.models;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

    private String name;
    private String speciality;
    private String contactNo;
    private List<Slot> availableSlots;
    private List<Slot> bookedSlots;

    public Doctor() {
        bookedSlots = new ArrayList<>();
        availableSlots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public List<Slot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Slot availableSlot) {
        this.availableSlots.add(availableSlot);
    }

    public List<Slot> getBookedSlots() {
        return bookedSlots;
    }

    public void setBookedSlots(Slot bookedSlot) {
        this.bookedSlots.add(bookedSlot);
    }
}
