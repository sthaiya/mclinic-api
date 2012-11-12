package com.mclinic.android.api.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.mclinic.android.api.dao.AdministrativeDao;
import com.mclinic.android.api.dao.CohortDao;
import com.mclinic.android.api.dao.PatientDao;
import com.mclinic.android.api.dao.impl.AdministrativeDaoImpl;
import com.mclinic.android.api.dao.impl.CohortDaoImpl;
import com.mclinic.android.api.dao.impl.PatientDaoImpl;
import com.mclinic.android.api.service.AdministrativeService;
import com.mclinic.android.api.service.CohortService;
import com.mclinic.android.api.service.FormService;
import com.mclinic.android.api.service.ObsService;
import com.mclinic.android.api.service.PatientService;
import com.mclinic.android.api.service.impl.AdministrativeServiceImpl;
import com.mclinic.android.api.service.impl.CohortServiceImpl;
import com.mclinic.android.api.service.impl.FormServiceImpl;
import com.mclinic.android.api.service.impl.ObsServiceImpl;
import com.mclinic.android.api.service.impl.PatientServiceImpl;
import com.mclinic.android.util.Constants;

public class MclinicAPIModule extends AbstractModule {
	
	private String luceneDir;
	private String luceneDocumentKey;

    public MclinicAPIModule(String luceneDir, String luceneDocumentKey) {
    	super();
		this.luceneDir=luceneDir;
		this.luceneDocumentKey=luceneDocumentKey;
	}

	@Override
    protected void configure() {
        guiceBindDataProvisionFromSearchAPI();
        guiceBindDaos();
        guiceBindServices();
    }

    private void guiceBindDaos() {
    	bind(PatientDao.class).to(PatientDaoImpl.class).in(Singleton.class);
    	bind(AdministrativeDao.class).to(AdministrativeDaoImpl.class).in(Singleton.class);
    	bind(CohortDao.class).to(CohortDaoImpl.class).in(Singleton.class);
    }
    
    private void guiceBindDataProvisionFromSearchAPI() {
    	bind(String.class).annotatedWith(Names.named(Constants.LUCENE_DIR_NAME)).toInstance(luceneDir);
        bind(String.class).annotatedWith(Names.named(Constants.LUCENE_DOCUMENT_KEY)).toInstance(luceneDocumentKey);
    }

    private void guiceBindServices() {
        bind(FormService.class).to(FormServiceImpl.class).in(Singleton.class);
        bind(AdministrativeService.class).to(AdministrativeServiceImpl.class).in(Singleton.class);
        bind(CohortService.class).to(CohortServiceImpl.class).in(Singleton.class);
        bind(PatientService.class).to(PatientServiceImpl.class).in(Singleton.class);
        bind(ObsService.class).to(ObsServiceImpl.class).in(Singleton.class);
    }
}