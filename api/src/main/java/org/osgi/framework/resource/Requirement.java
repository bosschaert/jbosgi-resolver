/*
 * Copyright (c) OSGi Alliance (2011, 2012). All Rights Reserved.
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

package org.osgi.framework.resource;

import java.util.Map;

/**
 * A requirement that has been declared from a {@link Resource} .
 * 
 * <p>
 * Instances of this type must be <i>effectively immutable</i>. That is, for a
 * given instance of this interface, the methods defined by this interface must
 * always return the same result.
 * 
 * @ThreadSafe
 * @version $Id: 0f28402db2288a77016703f1b1514c0ebce29d0d $
 */
public interface Requirement {
	/**
	 * Returns the name space of this requirement.
	 * 
	 * @return The name space of this requirement.
	 */
	String getNamespace();

	/**
	 * Returns the directives of this requirement.
	 * 
	 * <p>
	 * Only the following list of directives have specified semantics:
	 * <ul>
	 * <li> {@link ResourceConstants#REQUIREMENT_EFFECTIVE_DIRECTIVE effective}
	 * <li> {@link ResourceConstants#REQUIREMENT_FILTER_DIRECTIVE filter}
	 * <li> {@link ResourceConstants#REQUIREMENT_CARDINALITY_DIRECTIVE
	 * cardinality}
	 * <li> {@link ResourceConstants#REQUIREMENT_RESOLUTION_DIRECTIVE resolution}
	 * <li> {@link ResourceConstants#REQUIREMENT_VISIBILITY_DIRECTIVE visibility}
	 * - only recognized for the
	 * {@link ResourceConstants#WIRING_BUNDLE_NAMESPACE osgi.wiring.bundle} name
	 * space.
	 * </ul>
	 * All other directives have no specified semantics and are considered extra
	 * user defined information. The OSGi Alliance reserves the right to extend
	 * the set of directives which have specified semantics.
	 * 
	 * @return An unmodifiable map of directive names to directive values for
	 *         this requirement, or an empty map if this requirement has no
	 *         directives.
	 */
	Map<String, String> getDirectives();

	/**
	 * Returns the attributes of this requirement.
	 * 
	 * <p>
	 * Requirement attributes have no specified semantics and are considered
	 * extra user defined information.
	 * 
	 * @return An unmodifiable map of attribute names to attribute values for
	 *         this requirement, or an empty map if this requirement has no
	 *         attributes.
	 */
	Map<String, Object> getAttributes();

	/**
	 * Returns the resource declaring this requirement.
	 * 
	 * @return The resource declaring this requirement.
	 */
	Resource getResource();

	/**
	 * Compares this {@code Requirement} to another {@code Requirement}.
	 * 
	 * <p>
	 * This {@code Requirement} is equal to another {@code Requirement} if they
	 * have the same name space, directives and attributes and are declared by
	 * the same resource.
	 * 
	 * @param obj The object to compare against this {@code Requirement}.
	 * @return {@code true} if this {@code Requirement} is equal to the other
	 *         object; {@code false} otherwise.
	 */
	boolean equals(Object obj);

	/**
	 * Returns the hashCode of this {@code Requirement}.
	 * 
	 * @return The hashCode of this {@code Requirement}.
	 */
	int hashCode();
}
