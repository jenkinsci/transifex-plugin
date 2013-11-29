package com.gurkensalat.jenkinsci.plugins.transifex;

import hudson.model.Action;
import hudson.model.JobProperty;
import hudson.model.AbstractProject;

import java.util.Collection;
import java.util.Collections;

import jenkins.model.Jenkins;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * Stores the Transifex related project properties.
 * <p>
 * As of now this is only the URL to the Transifex project.
 * 
 * @todo Should we store the TransifexUrl instead of the String?
 * @author Hakan Tandogan <hakan@gurkensalat.com>
 */
public final class TransifexProjectProperty extends JobProperty<AbstractProject<?, ?>>
{

	/**
	 * This will the URL to the project main branch.
	 */
	String projectUrl;

	@DataBoundConstructor
	public TransifexProjectProperty(String projectUrl)
	{
		this.projectUrl = new TransifexUrl(projectUrl).baseUrl();
	}

	/**
	 * @return the projectUrl
	 */
	public TransifexUrl getProjectUrl()
	{
		return new TransifexUrl(projectUrl);
	}

	@Override
	public Collection<? extends Action> getJobActions(AbstractProject<?, ?> job)
	{
		if (null != projectUrl)
		{
			return Collections.singleton(new TransifexLinkAction(this));
		}
		return Collections.emptyList();
	}

	@Override
	public TransifexProjectPropertyDescriptor getDescriptor()
	{
		return (TransifexProjectPropertyDescriptor) Jenkins.getInstance().getDescriptorOrDie(getClass());
	}

}
