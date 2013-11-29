package com.gurkensalat.jenkinsci.plugins.transifex;

import hudson.Extension;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import hudson.model.AbstractProject;
import hudson.model.Job;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

@Extension
public final class TransifexProjectPropertyDescriptor extends JobPropertyDescriptor
{

	public TransifexProjectPropertyDescriptor()
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
		return Messages.transifex_TransifexProjectProperty_DisplayName();
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
