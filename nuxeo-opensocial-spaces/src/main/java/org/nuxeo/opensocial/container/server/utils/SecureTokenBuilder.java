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

package org.nuxeo.opensocial.container.server.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shindig.auth.AbstractSecurityToken;
import org.apache.shindig.auth.BlobCrypterSecurityToken;
import org.apache.shindig.auth.SecurityToken;
import org.apache.shindig.auth.SecurityTokenCodec;
import org.apache.shindig.common.crypto.BasicBlobCrypter;
import org.apache.shindig.common.crypto.BlobCrypter;
import org.apache.shindig.common.util.Utf8UrlCoder;
import org.nuxeo.opensocial.service.api.OpenSocialService;
import org.nuxeo.opensocial.service.impl.OpenSocialServiceImpl;
import org.nuxeo.runtime.api.Framework;

/**
 * @author Guillaume Cusnieux
 */
public class SecureTokenBuilder {

    private static final Log log = LogFactory.getLog(SecureTokenBuilder.class);

    public static String getSecureToken(String viewer, String owner,
            String gadgetUrl, boolean encode) throws Exception {
        OpenSocialService svc = Framework.getService(OpenSocialService.class);
        
        // TODO - Come up with another way to get the SecurityTokenCodec
        SecurityTokenCodec codec = ((OpenSocialServiceImpl)svc).getInjector().getInstance(SecurityTokenCodec.class);

        String container = "default";
        String domain = "localhost";
        if (svc.getPortalConfig() == null) {
            log.warn("portal configuration suggests that there are "
                    + svc.getPortalConfig().length
                    + " choices but we don't know how to pick the correct configuration!");
        }
          
        Map<String, String> params = new HashMap<String, String>();
        params.put(AbstractSecurityToken.Keys.VIEWER.getKey(), viewer);
        params.put(AbstractSecurityToken.Keys.OWNER.getKey(), owner);
        params.put(AbstractSecurityToken.Keys.APP_URL.getKey(), gadgetUrl);
        
        SecurityToken st = new BlobCrypterSecurityToken(container, domain, gadgetUrl, params);
        return codec.encodeToken(st);
    }

}
