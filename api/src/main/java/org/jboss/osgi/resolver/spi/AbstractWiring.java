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

package org.jboss.osgi.resolver.spi;

import static org.jboss.osgi.resolver.internal.ResolverMessages.MESSAGES;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jboss.osgi.resolver.XResource;
import org.osgi.resource.Capability;
import org.osgi.resource.Requirement;
import org.osgi.resource.Resource;
import org.osgi.resource.Wire;
import org.osgi.resource.Wiring;

/**
 * The abstract implementation of a {@link Wiring}.
 * 
 * @author thomas.diesler@jboss.com
 * @since 02-Jul-2010
 */
public class AbstractWiring implements Wiring {

    private final Resource resource;
    private final List<Wire> required;
    private final List<Wire> provided;

    public AbstractWiring(XResource resource, List<Wire> required, List<Wire> provided) {
        if (resource == null)
            throw MESSAGES.illegalArgumentNull("resource");
        this.resource = resource;
        List<Wire> emptywires = Collections.emptyList();
        this.required = required != null ? required : emptywires;
        this.provided = provided != null ? provided : emptywires;
    }

    @Override
    public List<Capability> getResourceCapabilities(String namespace) {
        return resource.getCapabilities(namespace);
    }

    @Override
    public List<Requirement> getResourceRequirements(String namespace) {
        return resource.getRequirements(namespace);
    }

    @Override
    public List<Wire> getProvidedResourceWires(String namespace) {
        List<Wire> result = new ArrayList<Wire>();
        if (provided != null) {
            for (Wire wire : provided) {
                Capability cap = wire.getCapability();
                if (namespace == null || namespace.equals(cap.getNamespace())) {
                    result.add(wire);
                }
            }
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public List<Wire> getRequiredResourceWires(String namespace) {
        List<Wire> result = new ArrayList<Wire>();
        if (required != null) {
            for (Wire wire : required) {
                Requirement req = wire.getRequirement();
                if (namespace == null || namespace.equals(req.getNamespace())) {
                    result.add(wire);
                }
            }
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return "Wiring[" + resource + "]";
    }
}