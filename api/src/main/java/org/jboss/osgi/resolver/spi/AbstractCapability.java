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

import org.jboss.osgi.resolver.XAttachmentSupport;
import org.jboss.osgi.resolver.XAttributeSupport;
import org.jboss.osgi.resolver.XDirectiveSupport;
import org.osgi.framework.resource.Capability;
import org.osgi.framework.resource.Resource;

import java.util.Map;

/**
 * The abstract implementation of a {@link Capability}.
 * 
 * @author thomas.diesler@jboss.com
 * @since 02-Jul-2010
 */
public class AbstractCapability extends AbstractElement implements XAttachmentSupport, XAttributeSupport, XDirectiveSupport, Capability {

    private final Resource resource;
    private final String namespace;
    private final XAttributeSupport attributes;
    private final XDirectiveSupport directives;
    private XAttachmentSupport attachments;

    protected AbstractCapability(String namespace, Resource resource, Map<String, Object> attributes, Map<String, String> directives) {
        this.namespace = namespace;
        this.resource = resource;
        this.attributes = new AttributeSupporter(attributes);
        this.directives = new DirectiveSupporter(directives);
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public Map<String, String> getDirectives() {
        return directives.getDirectives();
    }

    @Override
    public String getDirective(String key) {
        return directives.getDirective(key);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes.getAttributes();
    }

    @Override
    public Object getAttribute(String key) {
        return attributes.getAttribute(key);
    }

    @Override
    public <T> T addAttachment(Class<T> clazz, T value) {
        if (attachments == null)
            attachments = new AttachmentSupporter();
        return attachments.addAttachment(clazz, value);
    }

    @Override
    public <T> T getAttachment(Class<T> clazz) {
        if (attachments == null)
            return null;
        return attachments.getAttachment(clazz);
    }

    @Override
    public <T> T removeAttachment(Class<T> clazz) {
        if (attachments == null)
            return null;
        return attachments.removeAttachment(clazz);
    }

    public String toString() {
        String attstr = !getAttributes().isEmpty() ? ",attributes=" + attributes : "";
        String dirstr = !getDirectives().isEmpty() ? ",directives=" + directives : "";
        return getClass().getSimpleName() + "[" + namespace + attstr + dirstr + "]";
    }
}