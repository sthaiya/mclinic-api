package com.mclinic.android.api.dao;

import java.io.File;

public interface AdministrativeDao {

	void initializeDB(File j2lFile);

	void loadPatients(File jsonFilesDir);

	void loadCohorts(File jsonFilesDir);

	void loadCohortMembers(File jsonFilesDir);
	
}