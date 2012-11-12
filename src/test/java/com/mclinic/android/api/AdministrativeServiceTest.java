package com.mclinic.android.api;

import java.io.File;
import java.net.URL;

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
import com.mclinic.android.api.service.AdministrativeService;

public class AdministrativeServiceTest {
	
	static Context searchAPIContext;
	
	static RestAssuredService	searchAPIService; 
	
	static Injector injector;

    /**
     * @verifies index data from the rest resource
     * @see AdministrativeService#initializeDB(File)
     * @
     */
    @SuppressWarnings("unchecked")
    @Test
    public void initializeDB_shouldRegisterAllItems() throws Exception {
        URL j2l = AdministrativeServiceTest.class.getResource("j2l");
        URL lucene = AdministrativeServiceTest.class.getResource("lucene");
        
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
	public void loadPatients_shouldLoadPatientsFromJsonFiles() {
    	URL patientJsons = AdministrativeServiceTest.class.getResource("json/patient");
    	
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
	public void loadCohorts_shouldLoadCohortsFromJsonFiles() {
    	URL cohortJsons = AdministrativeServiceTest.class.getResource("json/cohort");
    	
    	searchAPIService = injector.getInstance(RestAssuredService.class);
    	
 		try {
 			searchAPIService.loadObjects("*", searchAPIContext.getResource("Cohort"), new File(cohortJsons.getPath()));
 			searchAPIService.commitIndex();
 			Assert.assertNotNull(searchAPIService.getObjects(null, Cohort.class));
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
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
}