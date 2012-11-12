package com.mclinic.android.api.service;

import java.io.File;


/**
 * Service handling all administrative operations
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
public interface AdministrativeService {
	
	/**
	 * @param j2lFile the j2l file to use to convert json files into lucene objects
	 */
	void initializeDB(File j2lFile);
	
	/** Load all patients in to the lucene index. This method will load all <code>Patient</code> as represented
	 *  by all json files in @jsonFilesDir directory into the lucene search index engine. <br><br>
	 *  
	 *  The method should be called once in the life time of a project. If new json files have been added to
	 *  the Json directory then update should be the method to call. <br><br>
	 *  
	 *  @param jsonFilesDir the directory containing all patients json files. 
	 */
	void loadPatients(File jsonFilesDir);
	
	void loadCohorts(File jsonFilesDir);

	void loadCohortMembers(File jsonFilesDir);
}