package com.devesh.project2.hospital;

import com.devesh.project2.hospital.services.DoctorService;
import com.devesh.project2.hospital.services.PatientService;

public class HospitalDriver {

    public void driver() {
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        doctorService.registerDoctor("Curious", "Cardiologist");
        doctorService.addAvailableSlots("09-10,12-13,16-17", "Curious");
        doctorService.registerDoctor("Dreadful", "Dermatologist");
        doctorService.addAvailableSlots("09-10,11-12,13-14", "Dreadful");
        doctorService.getAvailableDoctorsForGivenSpeciality("Cardiologist");
        patientService.registerPatient("PatientA", "AAA123456");
        patientService.makeBooking(12, "Curious", "PatientA");
        doctorService.getAvailableDoctorsForGivenSpeciality("Cardiologist");
        doctorService.registerDoctor("Daring", "Dermatologist");
        doctorService.addAvailableSlots("11-12,16-17", "Daring");
        doctorService.getAvailableDoctorsForGivenSpeciality("Dermatologist");
        patientService.makeBooking(11, "PatientF", "Daring");


    }
}
