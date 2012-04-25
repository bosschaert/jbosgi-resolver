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
package org.jboss.osgi.resolver.felix;

import static org.jboss.osgi.resolver.internal.ResolverLogger.LOGGER;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.jboss.osgi.resolver.internal.ResolverLogger;

/**
 * An integration with the resolver Logger.
 * 
 * This Logger delegates framework log messages to JBoss Logging.
 * 
 * @author thomas.diesler@jboss.com
 * @since 14-Feb-2012
 */
public class LoggerDelegate implements org.apache.felix.resolver.Logger {

	public LoggerDelegate() {
	}

	@Override
	public boolean isEnabled(int level) {
		switch (level) {
		/*    
		case LOG_ERROR:
			return log.isEnabled(Level.ERROR);
		case LOG_WARNING:
			return log.isEnabled(Level.WARN);
		case LOG_INFO:
			return log.isEnabled(Level.INFO);
	    */
		case LOG_DEBUG:
			return LOGGER.isEnabled(Level.DEBUG);
		case LOG_TRACE:
			return LOGGER.isEnabled(Level.TRACE);
	    default:
			return false;
		}
	}

	@Override
	public void log(int level, String msg) {
		log(level, msg, null);
	}

	@Override
	public void log(int level, String msg, Throwable throwable) {
		switch (level) {
		/*
		case LOG_ERROR:
			log.error(msg, throwable);
			break;
		case LOG_WARNING:
			log.warn(msg, throwable);
			break;
		case LOG_INFO:
			log.info(msg, throwable);
			break;
	    */
		case LOG_DEBUG:
		    LOGGER.debug(msg, throwable);
			break;
		case LOG_TRACE:
		    LOGGER.trace(msg, throwable);
			break;
		}
	}
}