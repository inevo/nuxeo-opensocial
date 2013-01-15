/*
 * (C) Copyright 2010 Nuxeo SAS (http://nuxeo.com/) and contributors.
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
 *     Nuxeo - initial API and implementation
 */

package org.nuxeo.opensocial.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.shindig.config.ContainerConfig;

public class FakeContainerConfig implements ContainerConfig {

	@Override
	public void addConfigObserver(ConfigObserver arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getBool(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<String> getContainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInt(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> List<T> getList(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Map<String, T> getMap(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProperties(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getProperty(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return "insecure";
	}

	@Override
	public Transaction newTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

   
    
    
}
