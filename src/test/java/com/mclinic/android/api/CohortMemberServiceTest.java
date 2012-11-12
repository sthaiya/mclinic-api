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
import com.mclinic.android.api.service.CohortService;
import com.mclinic.android.api.service.PatientService;

public class CohortMemberServiceTest {
	
	static Context searchAPIContext;
	
	static RestAssuredService	searchAPIService; 
	
	static CohortService cService;
	
	static Injector injector;

    /**
     * This is actually an {@AdministrativeServiceTest} method it should be a copy
     * paste from there and actual test is in that Test class
     */
    @SuppressWarnings("unchecked")
    @Test
    public void initializeDB_shouldRegisterAllItems() throws Exception {
        URL j2l = CohortMemberServiceTest.class.getResource("j2l");
        URL lucene = CohortMemberServiceTest.class.getResource("lucene");
        
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
    
    @Test
	public void loadCohortMembers_shouldLoadCohortMembersFromJsonFiles() {
    	URL cohortMemberJsons = AdministrativeServiceTest.class.getResource("json/cohort_member");
    	
    	searchAPIService = injector.getInstance(RestAssuredService.class);
    	
 		try {
 			searchAPIService.loadObjects("*", searchAPIContext.getResource("Cohort Member"), new File(cohortMemberJsons.getPath()));
 			searchAPIService.commitIndex();
 			Assert.assertNotNull(searchAPIService.getObjects(null, Patient.class));
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
	}
    
    @Test
	public void getAllPatients_shouldReturnAllPatientsInDB() {
	    PatientService pService = injector.getInstance(PatientService.class);
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
}