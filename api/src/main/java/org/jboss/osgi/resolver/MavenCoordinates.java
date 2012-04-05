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
package org.jboss.osgi.resolver;

import static org.jboss.osgi.resolver.internal.ResolverMessages.MESSAGES;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The artifact coordinates.
 *
 * @author thomas.diesler@jboss.com
 * @since 16-Jan-2012
 */
public final class MavenCoordinates {

    private final String groupId;
    private final String artifactId;
    private final String type;
    private final String version;
    private final String classifier;

    public static MavenCoordinates parse(String coordinates) {
        MavenCoordinates result;
        String[] parts = coordinates.split(":");
        if (parts.length == 3) {
            result = new MavenCoordinates(parts[0], parts[1], null, parts[2], null);
        } else if (parts.length == 4) {
            result = new MavenCoordinates(parts[0], parts[1], parts[2], parts[3], null);
        } else if (parts.length == 5) {
            result = new MavenCoordinates(parts[0], parts[1], parts[2], parts[3], parts[4]);
        } else {
            throw MESSAGES.illegalArgumentInvalidCoordinates(coordinates);
        }
        return result;
    }

    public static MavenCoordinates create(String groupId, String artifactId, String version, String type, String classifier) {
        return new MavenCoordinates(groupId, artifactId, type, version, classifier);
    }

    private MavenCoordinates(String groupId, String artifactId, String type, String version, String classifier) {
        if (groupId == null)
            throw MESSAGES.illegalArgumentNull("groupId");
        if (artifactId == null)
            throw MESSAGES.illegalArgumentNull("artifactId");
        if (version == null)
            throw MESSAGES.illegalArgumentNull("version");

        this.groupId = groupId;
        this.artifactId = artifactId;
        this.type = (type != null ? type : "jar");
        this.version = version;
        this.classifier = classifier;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getClassifier() {
        return classifier;
    }

    public String toExternalForm() {
        String clstr = classifier != null ? ":" + classifier : "";
        return groupId + ":" + artifactId + ":" + type + ":" + version + clstr;
    }

    public URL toArtifactURL(URL baseURL) {
        String base = baseURL.toExternalForm();
        if (base.endsWith("/") == false)
            base += "/";
        String dirstr = base + groupId.replace('.', '/') + "/" + artifactId + "/" + version;
        String clstr = classifier != null ? "-" + classifier : "";
        String urlstr = dirstr + "/" + artifactId + "-" + version + clstr + "." + type;
        try {
            return new URL(urlstr);
        } catch (MalformedURLException e) {
            throw MESSAGES.illegalStateInvalidArtifactURL(urlstr);
        }
    }

    @Override
    public String toString() {
        return "MavenCoordinates[" + toExternalForm() + "]";
    }
}