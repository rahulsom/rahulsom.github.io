<#include "header.ftl">
	
	<#include "menu.ftl">
	
	<div class="page-header">
		<h1>${content.title}</h1>
	</div>

	<p><em>${content.date?string("yyyy-MM-dd")}</em></p>

	<p>${content.body}</p>

	<hr />
	
<#include "footer.ftl">