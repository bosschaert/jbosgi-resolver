/*
 * #%L
 * JBossOSGi Resolver API
 * %%
 * Copyright (C) 2010 - 2012 JBoss by Red Hat
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
/*
 * Copyright (c) OSGi Alliance (2012). All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.osgi.framework.namespace;

import org.osgi.resource.Namespace;

/**
 * Wiring Capability and Requirement Namespaces base class.
 * 
 * <p>
 * This class is the common class shared by all OSGi defined wiring namespaces.
 * It defines the names for the common attributes and directives for the OSGi
 * specified wiring namespaces.
 * 
 * <p>
 * The values associated with these keys are of type {@code String}, unless
 * otherwise indicated.
 * 
 * @Immutable
 * @version $Id: 383e84df9190757ce6bb6fb722e80a3b7d6b68da $
 */
public abstract class AbstractWiringNamespace extends Namespace {

	/**
	 * The capability directive used to specify the comma separated list of
	 * mandatory attributes which must be specified in the
	 * {@link Namespace#REQUIREMENT_FILTER_DIRECTIVE filter} of a requirement in
	 * order for the capability to match the requirement.
	 */
	public final static String	CAPABILITY_MANDATORY_DIRECTIVE		= "mandatory";

	/**
	 * The capability attribute contains the {@code Version} of the resource
	 * providing the capability if one is specified or {@code 0.0.0} if not
	 * specified. The value of this attribute must be of type {@code Version}.
	 */
	public static final String	CAPABILITY_BUNDLE_VERSION_ATTRIBUTE	= "bundle-version";

	AbstractWiringNamespace() {
		// empty
	}
}
