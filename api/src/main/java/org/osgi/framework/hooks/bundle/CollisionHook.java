/*
 * Copyright (c) OSGi Alliance (2011). All Rights Reserved.
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
package org.osgi.framework.hooks.bundle;

import java.util.Collection;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * OSGi Framework Bundle Collision Hook Service.
 * 
 * <p>
 * Bundles registering this service will be called during framework bundle
 * install and update operations to determine if an install or update operation
 * will result in a bundle symbolic name and version collision.
 * 
 * @ThreadSafe
 * @version $Id: f7358d518bf34ba1f5edcdccb45d85813b23e3be $
 */
public interface CollisionHook {

	/**
	 * Specifies a bundle install operation is being performed.
	 */
	int	INSTALLING	= 1;
	/**
	 * Specifies a bundle update operation is being performed.
	 */
	int	UPDATING	= 2;

	/**
	 * Filter bundle collisions hook method. This method is called during the
	 * install or update operation. The operation type will be
	 * {@link #INSTALLING installing} or {@link #UPDATING updating}. Depending
	 * on the operation type the target bundle and the collision candidate
	 * collection are the following:
	 * <ul>
	 * <li> {@link #INSTALLING installing} - The target is the bundle associated
	 * with the {@link BundleContext} used to call one of the
	 * {@link BundleContext#installBundle(String) install} methods. The
	 * collision candidate collection contains the existing bundles installed
	 * which have the same symbolic name and version as the bundle being
	 * installed.
	 * <li> {@link #UPDATING updating} - The target is the bundle used to call
	 * one of the {@link Bundle#update() update} methods. The collision
	 * candidate collection contains the existing bundles installed which have
	 * the same symbolic name and version as the content the target bundle is
	 * being updated to.
	 * </ul>
	 * This method can filter the list of collision candidates by removing
	 * potential collisions. Removing a collision candidate will allow the
	 * specified operation to succeed as if the collision candidate is not
	 * installed.
	 * 
	 * @param operationType the operation type. Must be the value of
	 *        {@link #INSTALLING installing} or {@link #UPDATING updating}.
	 * @param target the target bundle used to determine what collision
	 *        candidates to filter.
	 * @param collisionCandidates a collection of collision candidates. The
	 *        collection supports all the optional {@code Collection} operations
	 *        except {@code add} and {@code addAll}. Attempting to add to the
	 *        collection will result in an {@code UnsupportedOperationException}
	 *        . The collection is not synchronized.
	 */
	void filterCollisions(int operationType, Bundle target,
			Collection<Bundle> collisionCandidates);
}
