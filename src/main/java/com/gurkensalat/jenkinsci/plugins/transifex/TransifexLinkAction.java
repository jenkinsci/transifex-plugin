package com.gurkensalat.jenkinsci.plugins.transifex;

import hudson.model.Action;

/**
 * Add the Transifex Logo/Icon to the sidebar.
 * 
 * @author Stefan Saasen <stefan@coravy.com>
 */
public final class TransifexLinkAction implements Action
{

	private final transient TransifexProjectProperty projectProperty;

	public TransifexLinkAction(TransifexProjectProperty transifexProjectProperty)
	{
		this.projectProperty = transifexProjectProperty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hudson.model.Action#getDisplayName()
	 */
	public String getDisplayName()
	{
		return "Transifex";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hudson.model.Action#getIconFileName()
	 */
	public String getIconFileName()
	{
		return "/plugin/transifex/images/24x24/tx_logo.png";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hudson.model.Action#getUrlName()
	 */
	public String getUrlName()
	{
		return projectProperty.getProjectUrl().baseUrl();
	}

}
