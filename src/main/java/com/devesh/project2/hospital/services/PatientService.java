package com.devesh.project2.hospital.services;

import com.devesh.project2.hospital.models.Doctor;
import com.devesh.project2.hospital.models.Patient;
import com.devesh.project2.hospital.models.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientService {

    Map<String, Patient> patientMap;

    private DoctorService doctorService;

    public PatientService() {
        patientMap = new HashMap<>();
        doctorService = new DoctorService();
    }

    public void registerPatient(String name, String contactNo) {
        if(patientMap.containsKey(name)) {
            System.out.println("Patient already exists");
            return;
        }
        Patient patient = new Patient();
        patient.setName(name);
        patient.setContactNo(contactNo);
        patientMap.put(name, patient);
        System.out.println(name + " registered successfully");
    }

    public Map<String, List<Slot>> checkSlots(String speciality) {
        return doctorService.getAvailableDoctorsForGivenSpeciality(speciality);
    }

    public void makeBooking(int startTime, String doctorName, String patientName) {
        Slot slot = new Slot();
        slot.setStartTime(startTime);
        slot.setEndTime(startTime+1);
        slot.setDoctorName(doctorName);
        slot.setPatientName(patientName);
        Doctor doctor = doctorService.getDoctorByName(doctorName);
        doctor.getAvailableSlots().remove(slot);
        doctor.getBookedSlots().add(slot);
    }

    public List<Slot> getBookedAppointments(String patientName) {
        Patient patient = patientMap.get(patientName);
        return patient.getBookings();
    }
}
