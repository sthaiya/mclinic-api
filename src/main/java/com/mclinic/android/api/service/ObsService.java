package com.mclinic.android.api.service;

import com.mclinic.android.api.model.Observation;
import com.mclinic.android.api.model.Patient;

import java.util.List;

/**
 * Service handling all operation to the @Observation actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
public interface ObsService {
	
    public Observation saveObservation(Observation observation);

    public Observation updateObservation(Observation observation);
    
    public Observation getObservationByUUID(String uuid);

    public List<Observation> getAllObservations(Patient patient);

    public void deleteObservation (Observation observation);

    public void deleteAllObservations(Patient patient);
}
