// Namespaces
l = namespace("/lib/layout")
st = namespace("jelly:stapler")
j = namespace("jelly:core")
t = namespace("/lib/hudson")
f = namespace("/lib/form")
d = namespace("jelly:define")


f.entry(field: "name", title: _("Name")) {
  f.textbox() 
}
f.entry(field: "home", title: "Path to Tx executable") {
  f.textbox() 
}
def toolDescriptor = descriptor
f.descriptorList(field: "properties", descriptors: descriptor.propertyDescriptors) 
