/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.osgi.resolver.spi;

import java.util.Map;

import org.jboss.osgi.resolver.XBundleCapability;
import org.jboss.osgi.resolver.XEnvironment;
import org.jboss.osgi.resolver.XPackageCapability;
import org.osgi.framework.Version;
import org.osgi.resource.Capability;
import org.osgi.resource.Resource;
import org.osgi.resource.Wiring;

/**
 * A comparator based on defined framework preferences.
 *
 * @author thomas.diesler@jboss.com
 * @since 02-Jul-2010
 */
class FrameworkPreferencesComparator extends ResourceIndexComparator {

    public FrameworkPreferencesComparator(XEnvironment environment) {
        super(environment);
    }
    
    @Override
    public int compare(Capability o1, Capability o2) {
        Resource res1 = o1.getResource();
        Resource res2 = o2.getResource();

        // prefer system bundle
        Long in1 = getResourceIndex(o1.getResource());
        Long in2 = getResourceIndex(o2.getResource());
        if (in1 == 0 || in2 == 0) {
            return (int)(in1 - in2);
        }
        
        Map<Resource, Wiring> wirings = getEnvironment().getWirings();
        Wiring w1 = wirings.get(res1);
        Wiring w2 = wirings.get(res2);

        // prefer wired
        if (w1 != null && w2 == null)
            return -1;
        if (w1 == null && w2 != null)
            return +1;

        // prefer higher package version
        if (o1 instanceof XPackageCapability && o2 instanceof XPackageCapability) {
            Version v1 = ((XPackageCapability) o1).getVersion();
            Version v2 = ((XPackageCapability) o2).getVersion();
            if (!v1.equals(v2))
                return v2.compareTo(v1);
        }
        
        // prefer higher resource version
        if (o1 instanceof XBundleCapability && o2 instanceof XBundleCapability) {
            Version v1 = ((XBundleCapability) o1).getVersion();
            Version v2 = ((XBundleCapability) o2).getVersion();
            if (!v1.equals(v2))
                return v2.compareTo(v1);
        }

        // prefer lower index
        return in1.compareTo(in2);
    }
}