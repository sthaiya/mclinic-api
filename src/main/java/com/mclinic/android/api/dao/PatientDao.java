package com.mclinic.android.api.dao;

import com.mclinic.android.api.model.Patient;

import java.util.List;

public interface PatientDao {
	
    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);
    
    Patient getPatientByIdentifier(String identifier);
    
    Patient getPatientByUUID(String uuid);

    List<Patient> getAllPatients();

    void deletePatient(Patient patient);

    void deleteAllPatients();

	List<Patient> getPatientsByName(String name);

}
