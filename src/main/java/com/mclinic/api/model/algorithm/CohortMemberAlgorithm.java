/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package com.mclinic.api.model.algorithm;

import com.burkeware.search.api.serialization.Algorithm;
import com.jayway.jsonpath.JsonPath;
import com.mclinic.api.model.Patient;

public class CohortMemberAlgorithm implements Algorithm {

    /**
     * Implementation of this method will define how the patient will be serialized from the JSON representation.
     *
     * @param serialized the json representation
     * @return the concrete patient object
     */
    @Override
    public Patient deserialize(final String serialized) {
        Patient patient = new Patient();

        // get the full json object representation and then pass this around to the next JsonPath.read()
        // this should minimize the time for the subsequent read() call
        Object jsonObject = JsonPath.read(serialized, "$");

        String uuid = JsonPath.read(jsonObject, "$.patient.uuid");
        patient.setUuid(uuid);

        String name = JsonPath.read(jsonObject, "$.patient.person.display");
        patient.setName(name);
        
        //this an identifier of type OpenMRS Identification Number = 1984MP-5
        String identifier = JsonPath.read(jsonObject, "$.patient.identifiers[0].display");
        //extract the identifier
        int index = identifier.indexOf("=");
        if (index != -1){
        	identifier= identifier.substring(index+1);
        }
        patient.setIdentifier(identifier.trim());

        String gender = JsonPath.read(jsonObject, "$.patient.person.gender");
        patient.setGender(gender);

        patient.setJson(serialized);

        return patient;
    }

    /**
     * Implementation of this method will define how the patient will be deserialized into the JSON representation.
     *
     * @param object the patient
     * @return the json representation
     */
    @Override
    public String serialize(final Object object) {
        Patient patient = (Patient) object;
        return patient.getJson();
    }
}
