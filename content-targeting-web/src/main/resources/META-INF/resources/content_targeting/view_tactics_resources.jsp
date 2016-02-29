<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
long campaignId = ParamUtil.getLong(request, "campaignId", 0);

String tacticKeywords = ParamUtil.getString(request, "tacticKeywords");

RowChecker tacticsRowChecker = new RowChecker(liferayPortletResponse);

SearchContainerIterator searchContainerIterator = new TacticSearchContainerIterator(campaignId, scopeGroupId, tacticKeywords);
%>

<liferay-portlet:renderURL varImpl="viewTacticsURL">
	<portlet:param name="mvcRenderCommandName" value="<%= ContentTargetingMVCCommand.VIEW_TACTICS %>" />
	<portlet:param name="campaignId" value="<%= String.valueOf(campaignId) %>" />
	<portlet:param name="className" value="<%= Campaign.class.getName() %>" />
	<portlet:param name="classPK" value="<%= String.valueOf(campaignId) %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-promotions-were-found"
	iteratorURL="<%= viewTacticsURL %>"
	rowChecker="<%= tacticsRowChecker %>"
	total="<%= searchContainerIterator.getTotal() %>"
>
	<liferay-ui:search-container-results
		results="<%= searchContainerIterator.getResults(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.content.targeting.model.Tactic"
		keyProperty="tacticId"
		modelVar="tactic"
	>
		<liferay-ui:search-container-column-text
			name="name"
			value="<%= tactic.getName(locale) %>"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			value="<%= tactic.getDescription(locale) %>"
		/>

		<liferay-ui:search-container-column-jsp
			path="/content_targeting/tactic_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:script use="liferay-util-list-fields">
	var deleteTactics = A.one('#<portlet:namespace />deleteTactics');

	if (deleteTactics) {
		A.one('#<portlet:namespace /><%= searchContainerReference.getId(request) %>SearchContainer').on(
			'click',
			function() {
				var hide = (Liferay.Util.listCheckedExcept(document.<portlet:namespace />fmTactics, '<portlet:namespace />allRowIds').length == 0);

				deleteTactics.toggle(!hide);
			},
			'input[type=checkbox]'
		);

		deleteTactics.on(
			'click',
			function(event) {
				if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
					document.<portlet:namespace />fmTactics.<portlet:namespace />tacticsIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fmTactics, '<portlet:namespace />allRowIds');

					<portlet:actionURL name="deleteTactic" var="deleteTacticsURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:actionURL>

					submitForm(document.<portlet:namespace />fmTactics, '<%= deleteTacticsURL %>');
				}
			}
		);
	}
</aui:script>