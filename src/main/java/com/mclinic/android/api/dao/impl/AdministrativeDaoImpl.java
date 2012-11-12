package com.mclinic.android.api.dao.impl;

import java.io.File;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.google.inject.Inject;
import com.mclinic.android.api.dao.AdministrativeDao;
import com.mclinic.android.api.model.Cohort;
import com.mclinic.android.api.model.Patient;
import com.mclinic.android.api.model.algorithm.CohortAlgorithm;
import com.mclinic.android.api.model.algorithm.CohortMemberAlgorithm;
import com.mclinic.android.api.model.algorithm.PatientAlgorithm;
import com.mclinic.android.api.model.resolver.CohortMemberResolver;
import com.mclinic.android.api.model.resolver.CohortResolver;
import com.mclinic.android.api.model.resolver.PatientResolver;

@SuppressWarnings("unchecked")
public class AdministrativeDaoImpl implements AdministrativeDao {
	
	@Inject
	private Context searchAPIContext;
	
	@Inject
	private RestAssuredService	searchAPIService; 
	
	@Override
	public void initializeDB(File j2lFile) {
		try {
			searchAPIContext.registerObject(Patient.class, Cohort.class);
			searchAPIContext.registerAlgorithm(PatientAlgorithm.class, CohortAlgorithm.class, CohortMemberAlgorithm.class);
			searchAPIContext.registerResolver(PatientResolver.class, CohortResolver.class, CohortMemberResolver.class);
			searchAPIContext.registerResources(j2lFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadPatients(File jsonFilesDir) {
		try {
			searchAPIService.loadObjects("*", searchAPIContext.getResource("Patient"), jsonFilesDir);
			searchAPIService.commitIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadCohorts(File jsonFilesDir) {
		try {
			searchAPIService.loadObjects("*", searchAPIContext.getResource("Cohort"), jsonFilesDir);
			searchAPIService.commitIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadCohortMembers(File jsonFilesDir) {
		try {
			searchAPIService.loadObjects("*", searchAPIContext.getResource("Cohort Member"), jsonFilesDir);
			searchAPIService.commitIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}