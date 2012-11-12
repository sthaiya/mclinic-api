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
package com.mclinic.android.api.model.resolver;

import com.burkeware.search.api.resolver.Resolver;

public abstract class AbstractResolver implements Resolver {

    protected final String WEB_CONTEXT = "openmrs-standalone/";

    protected final String WEB_SERVER = "http://localhost:8081/";

    @Override
    public String getPassword() {
        return "test";
    }

    @Override
    public String getUser() {
        return "admin";
    }
}
