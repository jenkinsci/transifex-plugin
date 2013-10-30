package com.gurkensalat.jenkinsci.plugins.transifex;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import java.util.Collection;
import java.util.Collections;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Stores the Transifex related project properties.
 * <p>
 * As of now this is only the URL to the Transifex project.
 * 
 * @todo Should we store the TransifexUrl instead of the String?
 * @author Stefan Saasen <stefan@coravy.com>
 */
public final class TransifexProjectProperty extends JobProperty<AbstractProject<?, ?>>
{

	/**
	 * This will the URL to the project main branch.
	 */
	private String projectUrl;

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

	/*
	 * @Override public JobPropertyDescriptor getDescriptor() { return
	 * DESCRIPTOR; }
	 * 
	 * public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();
	 */
	@Extension
	public static final class DescriptorImpl extends JobPropertyDescriptor
	{

		public DescriptorImpl()
		{
			super(TransifexProjectProperty.class);
			load();
		}

		public boolean isApplicable(Class<? extends Job> jobType)
		{
			return AbstractProject.class.isAssignableFrom(jobType);
		}

		public String getDisplayName()
		{
			return "Transifex project page";
		}

		@Override
		public JobProperty<?> newInstance(StaplerRequest req, JSONObject formData) throws FormException
		{
			TransifexProjectProperty tpp = req.bindJSON(TransifexProjectProperty.class, formData);
			if (tpp.projectUrl == null)
			{
				tpp = null; // not configured
			}
			return tpp;
		}

	}
}
