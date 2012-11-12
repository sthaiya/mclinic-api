package com.mclinic.android.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.android.api.dao.CohortDao;
import com.mclinic.android.api.model.Cohort;
import com.mclinic.android.api.service.CohortService;

import java.util.List;

public class CohortServiceImpl implements CohortService {
	
	@Inject
	private CohortDao dao;

	@Override
	public Cohort saveCohort(Cohort cohort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cohort updateCohort(Cohort cohort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cohort getCohortByUUID(String uuid) {
		return dao.getCohortByUUID(uuid);
	}
	
	@Override
	public List<Cohort> getCohortsByName(String name) {
		return dao.getCohortsByName(name);
	}

	@Override
	public List<Cohort> getAllCohorts() {
		return dao.getAllCohorts();
	}

	@Override
	public void deleteCohort(Cohort cohort) {
		dao.deleteCohort(cohort);
	}

	@Override
	public void deleteAllCohorts() {
		// TODO Auto-generated method stub
		
	}
}