package com.mclinic.android.api.dao.impl;

import java.util.List;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.util.StringUtil;
import com.google.inject.Inject;
import com.mclinic.android.api.dao.PatientDao;
import com.mclinic.android.api.model.Patient;

/**
 * This class should actually return actual objects from @SearchAPI
 * @author Samuel Mbugua
 */
public class PatientDaoImpl implements PatientDao {
	
	@Inject
	private Context searchAPIContext;
	
	@Inject
	private RestAssuredService	searchAPIService; 
	
	@Override
	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient getPatientByIdentifier(String identifier) {
		try {
			return searchAPIService.getObject("identifier: " + StringUtil.quote(identifier), Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Patient> getAllPatients() {
		try {
			return searchAPIService.getObjects(null, Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deletePatient(Patient patient) {
		try {
			searchAPIService.invalidate(patient, searchAPIContext.getResource("Patient"));
			searchAPIService.commitIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAllPatients() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Patient getPatientByUUID(String uuid) {
		try {
			return searchAPIService.getObject("uuid: " + StringUtil.quote(uuid), Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Patient> getPatientsByName(String name) {
		try {
			return searchAPIService.getObjects("name: " + StringUtil.quote(name + "*"), Patient.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}