package com.mclinic.android.api.dao.impl;

import java.util.List;

import com.burkeware.search.api.Context;
import com.burkeware.search.api.RestAssuredService;
import com.burkeware.search.api.util.StringUtil;
import com.google.inject.Inject;
import com.mclinic.android.api.dao.CohortDao;
import com.mclinic.android.api.model.Cohort;

public class CohortDaoImpl implements CohortDao {
	
	@Inject
	private Context searchAPIContext;
	
	@Inject
	private RestAssuredService	searchAPIService; 
	
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
		try {
			return searchAPIService.getObject("uuid: " + StringUtil.quote(uuid), Cohort.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Cohort> getCohortsByName(String name) {
		try {
			return searchAPIService.getObjects("name: " + StringUtil.quote(name + "*"), Cohort.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cohort> getAllCohorts() {
		try {
			return searchAPIService.getObjects(null, Cohort.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteCohort(Cohort cohort) {
		try {
			searchAPIService.invalidate(cohort, searchAPIContext.getResource("Cohort"));
			searchAPIService.commitIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAllCohorts() {
		// TODO Auto-generated method stub
	}
}