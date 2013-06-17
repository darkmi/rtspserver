<#if (actionMessages?exists && actionMessages?size > 0)>
<div <#rt/>
<#if parameters.id?if_exists != "">
 id="${parameters.id?html}"<#rt/>
<#else>
 id="actionMessage"<#rt/>
</#if>

<#if parameters.cssClass?exists>
 class="${parameters.cssClass?html}"<#rt/>
<#else>
 class="actionMessage"<#rt/>
</#if>
<#if parameters.cssStyle?exists>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
>
<#list actionMessages as message>
	<span>${message}</span>
	<#if message_has_next><br/><#rt/></#if>
</#list>
&nbsp;&nbsp;[<a href="#" onclick="javascript:
<#if parameters.id?if_exists != "">
 $('#${parameters.id?html}')<#rt/>
<#else>
 $('#actionMessage')<#rt/>
</#if>
.hide();return false;">hide</a>]</div><#rt/>
</#if>