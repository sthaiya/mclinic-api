package com.mclinic.android.api.service.impl;

import com.google.inject.Inject;
import com.mclinic.android.api.dao.AdministrativeDao;
import com.mclinic.android.api.service.AdministrativeService;

import java.io.File;

public class AdministrativeServiceImpl implements AdministrativeService {
	
	@Inject
    private AdministrativeDao dao;
	
	@Override
	public void initializeDB(File j2lFile) {
		dao.initializeDB(j2lFile);
	}
	
	@Override
	public void loadPatients(File jsonFilesDir) {
		dao.loadPatients(jsonFilesDir);
	}

	@Override
	public void loadCohorts(File jsonFilesDir) {
		dao.loadCohorts(jsonFilesDir);
	}

	@Override
	public void loadCohortMembers(File jsonFilesDir) {
		dao.loadCohortMembers(jsonFilesDir);
	}

}