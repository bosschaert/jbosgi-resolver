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


import org.jboss.osgi.resolver.XElement;
import org.jboss.osgi.spi.AttachableSupport;
import org.jboss.osgi.spi.AttachmentKey;
import org.jboss.osgi.spi.Attachable;

/**
 * The abstract implementation of a {@link XElement}.
 *
 * @author thomas.diesler@jboss.com
 * @since 02-Jul-2010
 */
public abstract class AbstractElement implements XElement {

    private Attachable attachments;

    @Override
    public <T> T putAttachment(AttachmentKey<T> key, T value) {
        if (attachments == null)
            attachments = new AttachableSupport();
        return attachments.putAttachment(key, value);
    }

    @Override
    public <T> T getAttachment(AttachmentKey<T> key) {
        if (attachments == null)
            return null;
        return attachments.getAttachment(key);
    }

    @Override
    public <T> T removeAttachment(AttachmentKey<T> key) {
        if (attachments == null)
            return null;
        return attachments.removeAttachment(key);
    }
}
