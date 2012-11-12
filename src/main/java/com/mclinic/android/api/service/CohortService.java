package com.mclinic.android.api.service;

import com.mclinic.android.api.model.Cohort;

import java.util.List;

/**
 * Service handling all operation to the @{Cohort} actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
public interface CohortService {
	
    Cohort saveCohort(Cohort cohort);

    Cohort updateCohort(Cohort cohort);
    
    Cohort getCohortByUUID(String uuid);
    
    List<Cohort> getCohortsByName(String name);

    List<Cohort> getAllCohorts();

    void deleteCohort(Cohort cohort);

    void deleteAllCohorts();
}
