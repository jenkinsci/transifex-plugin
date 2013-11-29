package com.gurkensalat.jenkinsci.plugins.transifex.TransifexProjectProperty;

// Namespaces
l = namespace("/lib/layout")
st = namespace("jelly:stapler")
j = namespace("jelly:core")
t = namespace("/lib/hudson")
f = namespace("/lib/form")
d = namespace("jelly:define")


f.entry(title: _("transifex.project"),
help: "/descriptor/com.gurkensalat.jenkinsci.plugins.transifex.TransifexProjectProperty/help/projectUrl")
{
	f.textbox(name: "transifex.projectUrl", value: instance.projectUrl)
}
