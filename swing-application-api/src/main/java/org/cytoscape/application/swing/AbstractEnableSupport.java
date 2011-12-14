/*
 File: AbstractEnableSupport.java

 Copyright (c) 2006, 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package org.cytoscape.application.swing;

import org.cytoscape.application.CyApplicationManager;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.swing.DynamicSubmenuListener;

import javax.swing.Action;
import javax.swing.JMenuItem;

abstract class AbstractEnableSupport {

	private final Action action;
	private final JMenuItem menuItem;
	private final DynamicSubmenuListener submenuListener; 
	private boolean enableState;

	/**
	 * Constructor.
	 * @param submenuListener The submenu listener whose enabled state will be updated.
	 */
	public AbstractEnableSupport(DynamicSubmenuListener submenuListener) {
		this.submenuListener = submenuListener;
		this.action = null;
		this.menuItem = null;
		this.enableState = true;
	}

	/**
	 * Constructor.
	 * @param action The action whose enabled state will be updated.
	 */
	public AbstractEnableSupport(Action action) {
		this.submenuListener = null;
		this.action = action;
		this.menuItem = null;
		this.enableState = true;
	}

	/**
	 * Constructor.
	 * @param menuItem The menuItem whose enabled state will be updated.
	 */
	public AbstractEnableSupport(JMenuItem menuItem) {
		this.submenuListener = null;
		this.action = null;
		this.menuItem = menuItem;
		this.enableState = true;
	}

	/**
	 * Must be implemented by subclass and must call the setEnabled() method
	 * based on the policy of the subclass.
	 */
	public abstract void updateEnableState();

	protected synchronized final void setEnabled(boolean b) {
		enableState = b;
		if ( submenuListener != null )
			submenuListener.setEnabled(enableState);
		if ( action != null )
			action.setEnabled(enableState);
		if ( menuItem != null )
			menuItem.setEnabled(enableState);
	}

	/**
	 * Returns true if the action/menuListener/menuItem is enabled, false otherwise.
	 * @return true if the action/menuListener/menuItem is enabled, false otherwise.
	 */
	public synchronized final boolean isCurrentlyEnabled() {
		return enableState;
	}
}
