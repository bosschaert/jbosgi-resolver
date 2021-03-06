/*
 * #%L
 * JBossOSGi Resolver API
 * %%
 * Copyright (C) 2010 - 2012 JBoss by Red Hat
 * %%
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
 * #L%
 */

package org.jboss.osgi.resolver.spi;

import org.jboss.osgi.resolver.XWiring;
import org.jboss.osgi.resolver.XWiringSupport;

/**
 * The abstract implementation of an {@link XWiringSupport}.
 *
 * @author thomas.diesler@jboss.com
 * @since 08-Feb-2013
 */
public class AbstractWiringSupport extends AbstractElement implements XWiringSupport {

    private XWiring wiring;
    private boolean effective = true;

    @Override
    public synchronized boolean isEffective() {
        return effective;
    }

    @Override
    public synchronized void makeUneffective() {
        this.effective = false;
    }

    @Override
    public synchronized XWiring getWiring(boolean checkEffective) {
        return checkEffective ? (effective ? wiring : null) : wiring;
    }

    @Override
    public synchronized void setWiring(XWiring wiring) {
        this.wiring = wiring;
    }

    @Override
    public synchronized void refresh() {
        wiring = null;
    }
}
