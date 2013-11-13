package com.gurkensalat.jenkinsci.plugins.transifex;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
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
	public DescriptorImpl getDescriptor()
	{
		return (DescriptorImpl) super.getDescriptor();
	}

	@Extension
	public static final class DescriptorImpl extends BuildStepDescriptor<Builder>
	{
		@Override
		public boolean isApplicable(Class<? extends AbstractProject> aClass)
		{
			return true;
		}

		@Override
		public String getDisplayName()
		{
			return Messages.transifex_TransifexUploadBuilder_DisplayName();
		}

	}
}
