<#include "header.ftl">
	
	<#include "menu.ftl">

	<div class="page-header">
		<h1>Blog</h1>
	</div>
	<#list posts as post>
  		<#if (post.status == "published")>
		<div class="post">
  			<a href="${post.uri}"><h1>${post.title}</h1></a>
  			<p>${post.date?string("dd MMMM yyyy")}</p>
  			<p>${post.body}</p>
        </div>
  		</#if>
  	</#list>
	
	<hr />
	
	<p>Older posts are available in the <a href="<#if (content.rootpath)??>${content.rootpath}<#else></#if>${config.archive_file}">archive</a>.</p>

<#include "footer.ftl">