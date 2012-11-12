package com.mclinic.android.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.android.api.dao.PatientDao;
import com.mclinic.android.api.model.Patient;
import com.mclinic.android.api.service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {
	
	@Inject
    private PatientDao dao;
	
	@Override
	public Patient savePatient(Patient patient) {
		return dao.savePatient(patient);
	}

	@Override
	public Patient updatePatient(Patient patient) {
		return dao.updatePatient(patient);
	}

	@Override
	public Patient getPatientByIdentifier(String identifier) {
		return dao.getPatientByIdentifier(identifier);
	}

	@Override
	public List<Patient> getAllPatients() {
		return dao.getAllPatients();
	}

	@Override
	public void deletePatient(Patient patient) {
		dao.deletePatient(patient);
	}

	@Override
	public void deleteAllPatients() {
		dao.deleteAllPatients();
	}

	@Override
	public Patient getPatientByUUID(String uuid) {
		return dao.getPatientByUUID(uuid);
	}

	@Override
	public List<Patient> getPatientsByName(String name) {
		return dao.getPatientsByName(name);
	}
}