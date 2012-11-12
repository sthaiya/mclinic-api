package com.mclinic.android.api.dao;

import com.mclinic.android.api.model.Cohort;

import java.util.List;

public interface CohortDao {
	
    Cohort saveCohort(Cohort cohort);

    Cohort updateCohort(Cohort cohort);
    
    Cohort getCohortByUUID(String uuid);

    List<Cohort> getCohortsByName(String name);
    
    List<Cohort> getAllCohorts();

    void deleteCohort(Cohort cohort);

    void deleteAllCohorts();

}
