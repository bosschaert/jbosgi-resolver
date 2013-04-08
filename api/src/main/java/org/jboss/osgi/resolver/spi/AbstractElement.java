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


import org.jboss.osgi.resolver.XAttachmentKey;
import org.jboss.osgi.resolver.XAttachmentSupport;
import org.jboss.osgi.resolver.XElement;

/**
 * The abstract implementation of a {@link XElement}.
 *
 * @author thomas.diesler@jboss.com
 * @since 02-Jul-2010
 */
public abstract class AbstractElement implements XElement {

    private XAttachmentSupport attachments;

    @Override
    public <T> T addAttachment(XAttachmentKey<T> key, T value) {
        if (attachments == null)
            attachments = new AttachmentSupporter();
        return attachments.addAttachment(key, value);
    }

    @Override
    public <T> T getAttachment(XAttachmentKey<T> key) {
        if (attachments == null)
            return null;
        return attachments.getAttachment(key);
    }

    @Override
    public <T> T removeAttachment(XAttachmentKey<T> key) {
        if (attachments == null)
            return null;
        return attachments.removeAttachment(key);
    }
}
