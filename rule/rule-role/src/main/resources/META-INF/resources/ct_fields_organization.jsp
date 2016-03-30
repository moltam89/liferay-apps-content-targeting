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
int organizationId = GetterUtil.getInteger(displayContext.get("organizationId"));

List<Organization> organizations = (List<Organization>)displayContext.get("organizations");
%>

<c:choose>
	<c:when test="<%= ListUtil.isEmpty(organizations) %>">
		<div class="alert alert-warning">
			<strong>
				<liferay-ui:message key="there-are-no-organizations-available" />
			</strong>

			<%
			String enableLocationLabel = LanguageUtil.get(request, "control-panel-users-and-organizations");

			String usersAdminURL = GetterUtil.getString(displayContext.get("usersAdminURL"));

			if (Validator.isNotNull(usersAdminURL)) {
				enableLocationLabel = "<a href=\"" + usersAdminURL + "\">" + enableLocationLabel + "</a>";
			}
			%>

			<liferay-ui:message arguments="<%= enableLocationLabel %>" key="organizations-can-be-managed-in-x" />
		</div>
	</c:when>
	<c:otherwise>
		<aui:select label="organization" name="organizationId">

			<%
			for (Organization organization : organizations) {
			%>

				<aui:option label="<%= organization.getName() %>" selected="<%= organization.getOrganizationId() == organizationId %>" value="<%= organization.getOrganizationId() %>" />

			<%
				}
			%>

		</aui:select>

		<aui:select label="role" name="roleId">

			<%
			for (Role role : roles) {
			%>

				<aui:option label="<%= role.getTitle(locale) %>" selected="<%= role.getRoleId() == roleId %>" value="<%= role.getRoleId() %>" />

			<%
			}
			%>

		</aui:select>
	</c:otherwise>
</c:choose>