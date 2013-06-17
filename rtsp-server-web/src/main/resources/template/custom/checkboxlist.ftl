<#assign itemCount = 0/>
<#assign columnCount = 3/>
<#if parameters.list??>
<table class="checkboxTable" style="margin-bottom:0em;">
    <@s.iterator value="parameters.list">
        <#if parameters.listKey??>
            <#assign itemKey = stack.findValue(parameters.listKey)/>
        <#else>
            <#assign itemKey = stack.findValue('top')/>
        </#if>
        <#if parameters.listValue??>
            <#assign itemValue = stack.findString(parameters.listValue)?default("")/>
        <#else>
            <#assign itemValue = stack.findString('top')/>
        </#if>
        <#if (itemCount%columnCount) == 0>
<tr>
        </#if>
<#assign itemKeyStr=itemKey.toString() />
	<td class="checkboxTd"><input type="checkbox" name="${parameters.name?html}" value="${itemKeyStr?html}" id="${parameters.name?html}-${itemKeyStr?html}"<#rt/>
        <#if tag.contains(parameters.nameValue, itemKey)>
 checked="checked"<#rt/>
        </#if>
        <#if parameters.disabled?default(false)>
 disabled="disabled"<#rt/>
        </#if>
        <#if parameters.title??>
 title="${parameters.title?html}"<#rt/>
        </#if>
        <#include "/${parameters.templateDir}/simple/scripting-events.ftl" />
        <#include "/${parameters.templateDir}/simple/common-attributes.ftl" />
/><#rt/>
	<label for="${parameters.name?html}-${itemKeyStr?html}" class="checkboxLabel">${itemValue?html}</label></td>
	<#assign itemCount = itemCount + 1/>
    <#if (itemCount%columnCount) == 0>
</tr>
	</#if>
    </@s.iterator>
    <#if (itemCount%columnCount) != 0>
</tr>
	</#if>
</table>
<#else>
  &nbsp;
</#if>