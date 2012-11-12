package com.mclinic.android.api.dao;

import com.mclinic.android.api.model.Observation;
import com.mclinic.android.api.model.Patient;

import java.util.List;

public interface ObsDao {
	
    public Observation saveObservation(Observation observation);

    public Observation updateObservation(Observation observation);
    
    public Observation getObservationByUUID(String uuid);

    public List<Observation> getAllObservations(Patient patient);

    public void deleteObservation (Observation observation);

    public void deleteAllObservations(Patient patient);
}
