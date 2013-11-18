package com.gurkensalat.jenkinsci.plugins.transifex;

import static hudson.init.InitMilestone.PLUGINS_STARTED;
import hudson.EnvVars;
import hudson.Extension;
import hudson.Functions;
import hudson.init.Initializer;
import hudson.model.EnvironmentSpecific;
import hudson.model.TaskListener;
import hudson.model.Node;
import hudson.slaves.NodeSpecific;
import hudson.tools.ToolDescriptor;
import hudson.tools.ToolProperty;
import hudson.tools.ToolInstallation;
import hudson.util.FormValidation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;

import jenkins.model.Jenkins;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Information about Transifex installation. Ideas stolen shamelessly from the
 * git-client plugin source.
 * 
 * @author Hakan Tandoga
 */
public final class TransifexTool extends ToolInstallation implements NodeSpecific<TransifexTool>,
        EnvironmentSpecific<TransifexTool>
{
	public static transient final String DEFAULT = "Default";

	@DataBoundConstructor
	public TransifexTool(String name, String home, List<? extends ToolProperty<?>> properties)
	{
		super(name, home, properties);
	}

	public String getTxExe()
	{
		return getHome();
	}

	private static TransifexTool[] getInstallations(DescriptorImpl descriptor)
	{
		TransifexTool[] installations = null;
		try
		{
			installations = descriptor.getInstallations();
		}
		catch (NullPointerException e)
		{
			installations = new TransifexTool[0];
		}
		return installations;
	}

	public static TransifexTool getDefaultInstallation()
	{
		DescriptorImpl TransifexTools = Jenkins.getInstance().getDescriptorByType(TransifexTool.DescriptorImpl.class);
		TransifexTool tool = TransifexTools.getInstallation(TransifexTool.DEFAULT);
		if (tool != null)
		{
			return tool;
		}
		else
		{
			TransifexTool[] installations = TransifexTools.getInstallations();
			if (installations.length > 0)
			{
				return installations[0];
			}
			else
			{
				onLoaded();
				return TransifexTools.getInstallations()[0];
			}
		}
	}

	public TransifexTool forNode(Node node, TaskListener log) throws IOException, InterruptedException
	{
		return new TransifexTool(getName(), translateFor(node, log), Collections.<ToolProperty<?>> emptyList());
	}

	public TransifexTool forEnvironment(EnvVars environment)
	{
		return new TransifexTool(getName(), environment.expand(getHome()), Collections.<ToolProperty<?>> emptyList());
	}

	@Override
	public DescriptorImpl getDescriptor()
	{
		return (DescriptorImpl) Jenkins.getInstance().getDescriptorOrDie(getClass());
	}

	@Initializer(after = PLUGINS_STARTED)
	public static void onLoaded()
	{
		// Creates default tool installation if needed. Uses "tx" or migrates
		// data from previous versions

		DescriptorImpl descriptor = (DescriptorImpl) Jenkins.getInstance().getDescriptor(TransifexTool.class);
		TransifexTool[] installations = getInstallations(descriptor);

		if (installations != null && installations.length > 0)
		{
			// No need to initialize if there's already something
			return;
		}

		String defaultTxExe = Functions.isWindows() ? "tx.exe" : "tx";
		TransifexTool tool = new TransifexTool(DEFAULT, defaultTxExe, Collections.<ToolProperty<?>> emptyList());
		descriptor.setInstallations(new TransifexTool[] { tool });
		descriptor.save();
	}

	@Extension
	public static class DescriptorImpl extends ToolDescriptor<TransifexTool>
	{

		public DescriptorImpl()
		{
			super();
			load();
		}

		@Override
		public String getDisplayName()
		{
			return Messages.transifex_TransifexTool_DisplayName();
		}

		@Override
		public boolean configure(StaplerRequest req, JSONObject json) throws FormException
		{
			setInstallations(req.bindJSONToList(clazz, json.get("tool")).toArray(new TransifexTool[0]));
			save();
			return true;
		}

		public FormValidation doCheckHome(@QueryParameter File value) throws IOException, ServletException
		{
			Jenkins.getInstance().checkPermission(Jenkins.ADMINISTER);
			String path = value.getPath();

			return FormValidation.validateExecutable(path);
		}

		public TransifexTool getInstallation(String name)
		{
			for (TransifexTool i : getInstallations())
			{
				if (i.getName().equals(name))
				{
					return i;
				}
			}
			// if (name.length() > 0)
			// {
			// LOGGER.log(Level.WARNING, "invalid TransifexTool selection {0}",
			// name);
			// }
			return null;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List<ToolDescriptor<? extends TransifexTool>> getApplicableDescriptors()
		{
			List<ToolDescriptor<? extends TransifexTool>> r = new ArrayList<ToolDescriptor<? extends TransifexTool>>();
			for (ToolDescriptor td : Jenkins.getInstance().<ToolInstallation, ToolDescriptor<?>> getDescriptorList(
			        ToolInstallation.class))
			{
				if (TransifexTool.class.isAssignableFrom(td.clazz))
					r.add(td);
			}
			return r;
		}
	}
}
