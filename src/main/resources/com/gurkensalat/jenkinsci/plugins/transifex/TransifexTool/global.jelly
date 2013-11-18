<?jelly escape-by-default='true'?>
<j:jelly xmlns:j="jelly:core" xmlns:st="jelly:stapler" xmlns:d="jelly:define" xmlns:l="/lib/layout" xmlns:t="/lib/hudson" xmlns:f="/lib/form">
  <f:section title="${%transifex.project}">
    <f:entry title="${%Transifex installations}" description="${%transifex.toolDescription}">
      <f:hetero-list name="tool" items="${descriptor.installations}"
                     descriptors="${descriptor.getApplicableDescriptors()}"
                     addCaption="${transifex.addToolCaption}" hasHeader="true"
                     deleteCaption="${transifex.deleteToolCaption}"/>
    </f:entry>
  </f:section>
</j:jelly>
