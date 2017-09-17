<#include "header.ftl">

	<#--<#include "menu.ftl">-->
	
	<div class="page-header">
		<h1><#escape x as x?xml>${content.title}</#escape></h1>
		<table>
			<tr>
				<td>
					<#if content.author??>
						<p>${content.author}</p>
					</#if>
				</td>
				<td>
					<#if content.date??>
                        <p class="right"><em>${content.date?string("dd MMMM yyyy")}</em></p>
					</#if>
				</td>
			</tr>
		</table>
	</div>

	<article>${content.body}</article>

	<hr />

<#include "footer.ftl">