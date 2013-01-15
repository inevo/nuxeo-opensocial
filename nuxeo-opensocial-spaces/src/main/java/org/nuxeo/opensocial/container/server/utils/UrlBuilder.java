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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shindig.gadgets.uri.UriCommon.Param;
import org.nuxeo.common.utils.URIUtils;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.opensocial.container.shared.webcontent.OpenSocialData;
import org.nuxeo.opensocial.container.shared.webcontent.UserPref;
import org.nuxeo.runtime.api.Framework;

/**
 * URlBuilder is builder of gadget url; Util for render of gadget into Shinding
 * opensocial server
 *
 */
public class UrlBuilder {

    private static final String SERVLET_PATH = "opensocial/gadgets/ifr";

    private static final String CONTAINER_VALUE = "default";

    private static final String NOCACHE_VALUE = "1";

    private static final String COUNTRY_VALUE = "ALL";

    private static final String DEFAULT_LANG_VALUE = "ALL";

    private static final String VIEW_VALUE = "default";

    private static final String PERMISSION_KEY = "permission";

    private static final String PERMISSION_VALUE = "[]";

    private static final String PREF_PREFIX = "up_";

    private static final Log log = LogFactory.getLog(UrlBuilder.class);

    private static final boolean SHINDIG_DEBUG = ("true".equals(Framework.getProperty("shindig.gadget.debug")));

    protected static int containerId = 0;

    public static String buildShindigUrl(OpenSocialData data, String serverBase)
            throws ClientException {
        String gadgetDef = data.getGadgetDef();
        List<UserPref> userPrefs = data.getUserPrefs();

        Map<String, String> params = new LinkedHashMap<String, String>();

        // container=default&nocache=1&country=fr&lang=ALL&view=default
        params.putAll(getDefaultParams());

        params.put(Param.GADGET.getKey(), data.getGadgetName());
        params.put(Param.URL.getKey(), gadgetDef);
        
        params.putAll(getUserPrefsParams(userPrefs));
        // turn on debugging for the JS (avoid compression)
        params.put(Param.DEBUG.getKey(), (SHINDIG_DEBUG) ? "1" : "0");
        // st=qdlfjqsmfkjqf
        try {
            params.put(Param.SECURITY_TOKEN.getKey(), getSecurityToken(data, gadgetDef));
        } catch (Exception e) {
            log.warn("Unable to get security token");
        }

        // http://localhost:8080/nuxeo/opensocial/gadgets/ifr
        String url = URIUtils.getURIPath(serverBase + SERVLET_PATH);
        url = URIUtils.addParametersToURIQuery(url, params);

        return url;
    }

    // /**
    // * Get Gadget Definition with GadgetService Gadget Definition is google
    // xml
    // * gadget
    // *
    // * @param name
    // * @return
    // * @throws Exception
    // */
    // public static String getGadgetDef(String name) throws Exception {
    // return Framework.getService(GadgetService.class).getGadgetDefinition(
    // name).toString();
    // }

    private static String getSecurityToken(OpenSocialData data, String url)
            throws Exception {
        return SecureTokenBuilder.getSecureToken(data.getViewer(),
                data.getOwner(), url, false);
    }

    /**
     * Build parameters map with preferences of gadget Util for render
     * gadget into Shinding opensocial server
     *
     * @param prefs
     * @return Map<String, String> <up_key, value>...
     */
    protected static Map<String, String> getUserPrefsParams(List<UserPref> prefs) {
        Map<String, String> prefsParams = new LinkedHashMap<String, String>();
        for (UserPref pref : prefs) {
            String value;
            if (pref.getActualValue() == null) {
                value = pref.getDefaultValue();
            } else {
                value = pref.getActualValue();
            }
            prefsParams.put(PREF_PREFIX + pref.getName(), value);
        }
        return prefsParams;
    }

    private static Map<String, String> getDefaultParams() {
        Map<String, String> defaultParams = new LinkedHashMap<String, String>();
        defaultParams.put(Param.CONTAINER.getKey(), CONTAINER_VALUE);
        defaultParams.put(Param.NO_CACHE.getKey(), NOCACHE_VALUE);
        defaultParams.put(Param.COUNTRY.getKey(), COUNTRY_VALUE);
        defaultParams.put(Param.LANG.getKey(), DEFAULT_LANG_VALUE);
        defaultParams.put(Param.VIEW.getKey(), VIEW_VALUE);
        defaultParams.put(PERMISSION_KEY, PERMISSION_VALUE);
        return defaultParams;
    }

}
