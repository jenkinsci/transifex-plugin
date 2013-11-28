// Namespaces
l = namespace("/lib/layout")
st = namespace("jelly:stapler")
j = namespace("jelly:core")
t = namespace("/lib/hudson")
f = namespace("/lib/form")
d = namespace("jelly:define")


f.section(title: _("transifex.project")) {
  f.entry(title: _("Transifex installations"), description: _("transifex.toolDescription")) {
    f.hetero-list(items: descriptor.installations, name: "tool", deleteCaption: transifex.deleteToolCaption, descriptors: descriptor.getApplicableDescriptors(), hasHeader: "true", addCaption: transifex.addToolCaption) 
  }
}
