package com.mclinic.android.api.service;

import com.mclinic.android.api.model.Form;

import java.util.List;

/**
 * Service handling all operation to the @{Form} actor/model
 * @author nribeka 
 * @author Samuel Mbugua
 *
 */
public interface FormService {
	
    public Form saveForm(Form form);

    public Form updateForm(Form form);
    
    public Form getFormById(Integer id);

    public List<Form> getAllForms();

    public void deleteForm(Form form);

    public void deleteAllForms();
}
