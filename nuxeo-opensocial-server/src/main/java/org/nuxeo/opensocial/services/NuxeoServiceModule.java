/*
 * (C) Copyright 2006-2009 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Leroy Merlin (http://www.leroymerlin.fr/) - initial implementation
 */

package org.nuxeo.opensocial.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shindig.social.opensocial.spi.ActivityService;
import org.apache.shindig.social.opensocial.spi.ActivityStreamService;
import org.apache.shindig.social.opensocial.spi.AlbumService;
import org.apache.shindig.social.opensocial.spi.AppDataService;
import org.apache.shindig.social.opensocial.spi.GroupService;
import org.apache.shindig.social.opensocial.spi.MediaItemService;
import org.apache.shindig.social.opensocial.spi.MessageService;
import org.apache.shindig.social.opensocial.spi.PersonService;
import org.nuxeo.runtime.api.Framework;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class NuxeoServiceModule extends AbstractModule {

    private static final Log LOG = LogFactory.getLog(NuxeoServiceModule.class);

    @Override
    protected final void configure() {

        try {
        	
            // TODO - check if we really need this
        	bind(String.class).annotatedWith(Names.named("shindig.canonical.json.db"))
            	.toInstance("sampledata/canonicaldb.json");
        	
            bind(ActivityStreamService.class).toInstance(
            		Framework.getService(ActivityStreamService.class));
            bind(AlbumService.class).toInstance(
                	Framework.getService(AlbumService.class));
            bind(MediaItemService.class).toInstance(
                	Framework.getService(MediaItemService.class));
            bind(MessageService.class).toInstance(
                	Framework.getService(MessageService.class));
            bind(GroupService.class).toInstance(
                	Framework.getService(GroupService.class));    
            bind(PersonService.class).toInstance(
                    Framework.getService(PersonService.class));
            bind(ActivityService.class).toInstance(
                    Framework.getService(ActivityService.class));
            bind(AppDataService.class).toInstance(
                    Framework.getService(AppDataService.class));

        } catch (Exception e) {
            LOG.error("Unable to bind Shindig services to Nuxeo components");
            LOG.error(e.getMessage());
        }

    }

}
