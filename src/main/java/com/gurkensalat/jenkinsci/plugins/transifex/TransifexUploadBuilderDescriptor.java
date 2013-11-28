package com.gurkensalat.jenkinsci.plugins.transifex;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

@Extension
public final class TransifexUploadBuilderDescriptor extends BuildStepDescriptor<Builder>
{
	public TransifexUploadBuilderDescriptor()
	{
		super(TransifexUploadBuilder.class);
		load();
		// if (...) { set defaults }
	}

	@Override
	public boolean isApplicable(Class<? extends AbstractProject> jobType)
	{
		return true;
	}

	@Override
	public String getDisplayName()
	{
		return Messages.transifex_TransifexUploadBuilder_DisplayName();
	}

}
