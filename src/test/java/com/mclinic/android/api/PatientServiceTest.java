package com.mclinic.android.api;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.module.FactoryModule;
import com.burkeware.search.api.module.SearchModule;
import com.burkeware.search.api.resource.Resource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mclinic.android.api.model.Cohort;
import com.mclinic.android.api.model.Patient;
import com.mclinic.android.api.model.algorithm.CohortAlgorithm;
import com.mclinic.android.api.model.algorithm.CohortMemberAlgorithm;
import com.mclinic.android.api.model.algorithm.PatientAlgorithm;
import com.mclinic.android.api.model.resolver.CohortMemberResolver;
import com.mclinic.android.api.model.resolver.CohortResolver;
import com.mclinic.android.api.model.resolver.PatientResolver;
import com.mclinic.android.api.module.MclinicAPIModule;
import com.mclinic.android.api.service.PatientService;

public class PatientServiceTest {
	
	static Context searchAPIContext;
	
	static RestAssuredService	searchAPIService; 
	
	static PatientService pService;
	
	static Injector injector;

    /**
     * This is actually an {@AdministrativeServiceTest} method it should be a copy
     * paste from there and actual test is in that Test class
     */
    @SuppressWarnings("unchecked")
    @Test
    public void initializeDB_shouldRegisterAllItems() throws Exception {
        URL j2l = PatientServiceTest.class.getResource("j2l");
        URL lucene = PatientServiceTest.class.getResource("lucene");
        
        injector = Guice.createInjector(new MclinicAPIModule(lucene.getPath(), "uuid"),new SearchModule(), new FactoryModule());
        
        searchAPIContext = injector.getInstance(Context.class);
        
		try {
			searchAPIContext.registerObject(Patient.class, Cohort.class);
			searchAPIContext.registerAlgorithm(PatientAlgorithm.class, CohortAlgorithm.class, CohortMemberAlgorithm.class);
			searchAPIContext.registerResolver(PatientResolver.class, CohortResolver.class, CohortMemberResolver.class);
			searchAPIContext.registerResources(new File(j2l.getPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}

        Resource resource = searchAPIContext.getResource("Patient");
        Assert.assertNotNull(resource);
        
        resource = searchAPIContext.getResource("Cohort");
        Assert.assertNotNull(resource);
        
        resource = searchAPIContext.getResource("Cohort Member");
        Assert.assertNotNull(resource);
    }
    
    /**
     * This is actually an {@AdministrativeServiceTest} method it should be a copy
     * paste from there and actual test is in that Test class
     */
    @Test
	public void loadPatients_shouldLoadPatientsFromJsonFiles() {
    	URL patientJsons = PatientServiceTest.class.getResource("json/patient");
    	
    	searchAPIService = injector.getInstance(RestAssuredService.class);
    	
 		try {
 			searchAPIService.loadObjects("*", searchAPIContext.getResource("Patient"), new File(patientJsons.getPath()));
 			searchAPIService.commitIndex();
 			Assert.assertNotNull(searchAPIService.getObjects(null, Patient.class));
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
	}
    
    @Test
	public void getAllPatients_shouldReturnAllPatientsInDB() {
	    pService = injector.getInstance(PatientService.class);
		System.out.println("ALL PATIENTS");
	    List<Patient> pats = pService.getAllPatients();
	    Assert.assertNotNull(pats);
		if (pats != null && pats.size() > 0) {
			for (Patient pat : pats) {
				System.out.print("[" + pat.getUuid() + "]");
				System.out.print("-[" + pat.getName() + "]");
				System.out.print("-[" + pat.getIdentifier() + "]");
				System.out.print("-[" + pat.getGender() + "]");
				System.out.print("-[" + pat.getBirthdate() + "]");
				System.out.println();
			}
		} else
			System.out.println("\nNO PATIENTS");
    }
	
    @Test
	public void getPatientsByName_shouldReturnPatientsListGivenName() {
		System.out.println("PATIENTS BY NAME");
		List<Patient> pats = pService.getPatientsByName("Testarius");
		Assert.assertNotNull(pats);
		if (pats != null && pats.size() > 0) {
			for (Patient pat : pats) {
				System.out.print("[" + pat.getUuid() + "]");
				System.out.print("-[" + pat.getName() + "]");
				System.out.print("-[" + pat.getIdentifier() + "]");
				System.out.print("-[" + pat.getGender() + "]");
				System.out.print("-[" + pat.getBirthdate() + "]");
				System.out.println();
			}
		} else
			System.out.println("Patients is null");
    }
    
    @Test
	public void getPatientByIdentifier_shouldReturnOnePatient() {
		System.out.println("PATIENT BY IDENTIFIER");
		Patient pat = pService.getPatientByIdentifier("363MO-5");
		Assert.assertNotNull(pat);
		if (pat != null) {
			System.out.print("[" + pat.getUuid() + "]");
			System.out.print("-[" + pat.getName() + "]");
			System.out.print("-[" + pat.getIdentifier() + "]");
			System.out.print("-[" + pat.getGender() + "]");
			System.out.print("-[" + pat.getBirthdate() + "]");
		} else {
			System.out.println("Patient is null");
		}
		System.out.println();
    }
	
    @Test
	public void getPatientByUUID_shouldReturnOnePatient() {
		System.out.println("\nPATIENT BY UUID");
		Patient pat = pService.getPatientByUUID("dd55e586-1693-11df-97a5-7038c432aabf");
		Assert.assertNotNull(pat);
		if (pat != null) {
			System.out.print("[" + pat.getUuid() + "]");
			System.out.print("-[" + pat.getName() + "]");
			System.out.print("-[" + pat.getIdentifier() + "]");
			System.out.print("-[" + pat.getGender() + "]");
			System.out.print("-[" + pat.getBirthdate() + "]");
		} else {
			System.out.println("Patient is null");
		}
		System.out.println();
    }
	
    @Test
	public void deletePatient_shouldDeleteOnePatient() {
		System.out.println("\nDELETE PATIENT");
		Patient pat = pService.getPatientByUUID("dd55e586-1693-11df-97a5-7038c432aabf");
		Assert.assertNotNull(pat);
		pService.deletePatient(pat);
    }
}