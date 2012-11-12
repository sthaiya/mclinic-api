package com.mclinic.android.api.dao;

import com.mclinic.android.api.model.Form;

import java.util.List;

public interface FormDao {
	
    public Form saveForm(Form form);

    public Form updateForm(Form form);
    
    public Form getFormById(Integer id);

    public List<Form> getAllForms();

    public void deleteForm(Form form);

    public void deleteAllForms();
}
