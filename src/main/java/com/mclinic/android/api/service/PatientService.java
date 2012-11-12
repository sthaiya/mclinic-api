package com.mclinic.android.api.service;

import com.mclinic.android.api.model.Patient;

import java.util.List;

/**
 * Service handling all operation to the @{Patient} actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
public interface PatientService {
	
    Patient savePatient(Patient patient);

    Patient updatePatient(Patient patient);
    
    Patient getPatientByIdentifier(String identifier);
    
    Patient getPatientByUUID(String uuid);

    List<Patient> getAllPatients();
    
    List<Patient> getPatientsByName(String name);

    void deletePatient(Patient patient);

    void deleteAllPatients();

}
