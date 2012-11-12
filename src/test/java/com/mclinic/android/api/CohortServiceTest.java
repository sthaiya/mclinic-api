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

public class CohortServiceTest {
	
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
        URL j2l = CohortServiceTest.class.getResource("j2l");
        URL lucene = CohortServiceTest.class.getResource("lucene");
        
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
	public void getAllCohorts_shouldReturnAllCohortsInDB() {
    	cService = injector.getInstance(CohortService.class);
		System.out.println("ALL COHORTS");
		List<Cohort> cohorts = cService.getAllCohorts();
		Assert.assertNotNull(cohorts);
		if (cohorts != null && cohorts.size() > 0) {
			for (Cohort cohort : cohorts) {
				System.out.print("[" + cohort.getUuid() + "]");
				System.out.print("-[" + cohort.getName() + "]");
				System.out.println();
			}
		} else
			System.out.println("NO COHORTS");
		
    }
	
    @Test
	public void getCohortsByName_shouldReturnCohortsListGivenName() {
    	System.out.println("COHORT BY NAME");
    	List<Cohort> cohorts = cService.getCohortsByName("Male");
    	Assert.assertNotNull(cohorts);
		if (cohorts != null && cohorts.size() > 0) {
			for (Cohort cohort : cohorts) {
				System.out.print("[" + cohort.getUuid() + "]");
				System.out.print("-[" + cohort.getName() + "]");
				System.out.println();
			}
		} else
			System.out.println("Cohort is null");
    }
    
    @Test
	public void getCohortByUUID_shouldReturnOneCohort() {
    	System.out.println("\nCOHORT BY UUID");
		Cohort cohort = cService.getCohortByUUID("0ca78602-738f-408d-8ced-386ad12367db");
		Assert.assertNotNull(cohort);
		if (cohort != null) {
			System.out.print("[" + cohort.getUuid() + "]");
			System.out.print("-[" + cohort.getName() + "]");
			System.out.println();
		} else
			System.out.println("Cohort is null");
    }
	
    @Test
	public void deleteCohort_shouldDeleteOneCohort() {
    	System.out.println("DELETE COHORT");
    	Cohort cohort = cService.getCohortByUUID("0ca78602-738f-408d-8ced-386ad12367db");
		cService.deleteCohort(cohort);
		Assert.assertNotNull(cohort);
    }
}