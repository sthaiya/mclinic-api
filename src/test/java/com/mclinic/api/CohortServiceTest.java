package com.mclinic.api;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.logger.Logger;
import com.burkeware.search.api.resource.Resource;
import com.mclinic.api.model.Cohort;
import com.mclinic.api.model.Patient;
import com.mclinic.api.model.algorithm.CohortAlgorithm;
import com.mclinic.api.model.algorithm.CohortMemberAlgorithm;
import com.mclinic.api.model.algorithm.PatientAlgorithm;
import com.mclinic.api.model.resolver.CohortMemberResolver;
import com.mclinic.api.model.resolver.CohortResolver;
import com.mclinic.api.model.resolver.PatientResolver;
import com.mclinic.api.module.MclinicAPIModule;
import com.mclinic.api.service.CohortService;

public class CohortServiceTest {
	
	static RestAssuredService service; 
	
	static CohortService cService;
	
	static Logger log;
	
    /**
     * This is actually an {@CohortServiceTest} method it should be a copy
     * paste from there and actual test is in that Test class
     */
    @SuppressWarnings("unchecked")
    @Test
    public void initializeDB_shouldRegisterAllItems() throws Exception {
        URL j2l = CohortServiceTest.class.getResource("j2l");
        URL lucene = CohortServiceTest.class.getResource("lucene");
        
        MclinicAPIModule module = new MclinicAPIModule(lucene.getPath(), "uuid");
        module.setServer("http://localhost:8080/openmrs/");
        module.setUsername("admin");
        module.setPassword("test");
        Context.initialize(module);
        
		try {
			Context.registerObject(Patient.class, Cohort.class);
			Context.registerAlgorithm(PatientAlgorithm.class, CohortAlgorithm.class, CohortMemberAlgorithm.class);
			Context.registerResolver(PatientResolver.class, CohortResolver.class, CohortMemberResolver.class);
			Context.registerResources(new File(j2l.getPath()));
		} catch (Exception e) {
			log.error("CohortServiceTest", "Error initializing");
		}

        Resource resource = Context.getResource("Patient");
        Assert.assertNotNull(resource);
        
        resource = Context.getResource("Cohort");
        Assert.assertNotNull(resource);
        
        resource = Context.getResource("Cohort Member");
        Assert.assertNotNull(resource);
        
        service= Context.getService();
    }
    
    /**
     * This is actually an {@CohortServiceTest} method it should be a copy
     * paste from there and actual test is in that Test class
     */
    @Test
	public void loadCohorts_shouldLoadCohortsFromJsonFiles() {
    	URL cohortJsons = CohortServiceTest.class.getResource("json/cohort");
    	
 		try {
 			service.loadObjects("*", Context.getResource("Cohort"), new File(cohortJsons.getPath()));
 			Assert.assertNotNull(service.getObjects(null, Cohort.class));
 		} catch (Exception e) {
 			log.error("CohortServiceTest", "Error loading cohorts");
 		}
	}

    @Test
	public void getAllCohorts_shouldReturnAllCohortsInDB() {
    	cService = Context.getInstance(CohortService.class);
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
		getAllCohorts_shouldReturnAllCohortsInDB();
    }
}