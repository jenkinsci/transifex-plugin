package com.gurkensalat.jenkinsci.plugins.transifex;

import jenkins.model.Jenkins;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.tasks.Builder;

import org.kohsuke.stapler.DataBoundConstructor;

public final class TransifexUploadBuilder extends Builder
{

	// Fields in config.jelly must match the parameter names in the
	// "DataBoundConstructor"
	@DataBoundConstructor
	public TransifexUploadBuilder()
	{

	}

	/**
	 * This human readable name is used in the configuration screen.
	 */
	public String getDisplayName()
	{
		// TODO I18N
		return "Upload translation sources to transifex.com";
	}

	/**
	 * This is where you 'build' the project.
	 */
	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener)
	{
		listener.getLogger().println("Perform called.\n");

		return true;
	}

	@Override
	public TransifexUploadBuilderDescriptor getDescriptor()
	{
		return (TransifexUploadBuilderDescriptor) Jenkins.getInstance().getDescriptorOrDie(getClass());
		// return (TransifexUploadBuilderDescriptor) super.getDescriptor();
	}
}
