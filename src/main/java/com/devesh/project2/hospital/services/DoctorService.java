package com.devesh.project2.hospital.services;

import com.devesh.project2.hospital.models.Doctor;
import com.devesh.project2.hospital.models.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorService {

    List<Doctor> doctors;
    Map<String, Doctor> doctorMap;

    public DoctorService() {
        doctorMap = new HashMap<>();
    }

    public void registerDoctor(String name, String speciality) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpeciality(speciality);
        doctorMap.put(name, doctor);
        System.out.println("Welcome Dr." + name);
    }

    public void addAvailableSlots(String slots, String doctorName) {
        Doctor doctor = doctorMap.get(doctorName);
        String slotsArray[] = slots.split(",");
        for(String slot : slotsArray) {
            int startTime = Integer.valueOf(slot.substring(0,2));
            int endTime = Integer.valueOf(slot.substring(3,5));
            Slot newSlot = new Slot();
            newSlot.setStartTime(startTime);
            newSlot.setEndTime(endTime);
            newSlot.setDoctorName(doctorName);
            doctor.setAvailableSlots(newSlot);
        }
        doctorMap.put(doctorName, doctor);
        System.out.println("Done doc!!");
    }

    public List<Slot> getAvailableSlots(String doctorName) {
        return doctorMap.get(doctorName).getAvailableSlots();
    }

    public Map<String, List<Slot>> getAvailableDoctorsForGivenSpeciality(String speciality) {
        Map<String, List<Slot>> availableSlots = new HashMap<>();

        for(Doctor doctor : doctorMap.values()) {
            if(doctor.getSpeciality().equals(speciality) && doctor.getAvailableSlots().size()>0) {
                for(Slot slot : doctor.getAvailableSlots()) {
                    System.out.println(doctor.getName() + " -> "+ slot.getStartTime()+":"+slot.getEndTime() );
                }
                availableSlots.put(doctor.getName(), doctor.getAvailableSlots());
            }
        }

        return availableSlots;
    }

    public Doctor getDoctorByName(String name) {
        return doctorMap.get(name);
    }

}
