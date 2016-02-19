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

<%@ include file="/html/init.jsp" %>

<%
boolean instantiableExists = false;

Collection<Report> reports = (Collection<Report>)request.getAttribute("reports");

for (Report report : reports) {
	if (report.isInstantiable()) {
		instantiableExists = true;

		break;
	}
}
%>

<aui:nav-bar>
	<aui:nav>
		<c:choose>
			<c:when test="<%= Campaign.class.getName().equals(className) %>">
				<c:if test="<%= CampaignPermission.contains(permissionChecker, classPK, ActionKeys.UPDATE) && instantiableExists %>">
					<liferay-portlet:renderURL var="redirectURL">
						<portlet:param
							name="mvcRenderCommandName"
							value="<%= ContentTargetingMVCCommand.EDIT_CAMPAIGN %>"
						/>
						<portlet:param
							name="backURL"
							value="<%= backURL.toString() %>"
						/>
						<portlet:param
							name="campaignId"
							value="<%= String.valueOf(classPK) %>"
						/>
						<portlet:param
							name="className"
							value="<%= Campaign.class.getName() %>"
						/>
						<portlet:param
							name="classPK"
							value="<%= String.valueOf(classPK) %>"
						/>
						<portlet:param
							name="tabs2"
							value="reports"
						/>
					</liferay-portlet:renderURL>

					<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-plus" id="addButtonContainer" label='<%= LanguageUtil.get(portletConfig.getResourceBundle(locale), "add") %>'>

						<%
						for (Report report : reports) {
							if (report.isInstantiable()) {
						%>

							<liferay-portlet:renderURL var="addReportURL">
								<portlet:param name="mvcRenderCommandName" value="<%= ContentTargetingMVCCommand.EDIT_REPORT %>" />
								<portlet:param name="redirect" value="<%= redirect %>" />
								<portlet:param name="campaignId" value="<%= String.valueOf(classPK) %>" />
								<portlet:param name="className" value="<%= Campaign.class.getName() %>" />
								<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
								<portlet:param name="reportKey" value="<%= report.getReportKey() %>" />
							</liferay-portlet:renderURL>

							<aui:nav-item
								href="<%= addReportURL %>"
								iconCssClass="<%= report.getIcon() %>"
								id="add-<%= report.getReportKey() %>"
								label="<%= report.getName(locale) %>"
							/>

						<%
							}
						}
						%>

					</aui:nav-item>

					<aui:nav-item
						cssClass="hide"
						iconCssClass="icon-remove"
						id="deleteReports"
						label='<%= LanguageUtil.get(portletConfig.getResourceBundle(locale), "delete") %>'
					/>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="<%= UserSegmentPermission.contains(permissionChecker, classPK, ActionKeys.UPDATE) && instantiableExists %>">
					<liferay-portlet:renderURL var="redirectURL">
						<portlet:param
							name="mvcRenderCommandName"
							value="<%= ContentTargetingMVCCommand.EDIT_USER_SEGMENT %>"
						/>
						<portlet:param
							name="backURL"
							value="<%= redirect %>"
						/>
						<portlet:param
							name="userSegmentId"
							value="<%= String.valueOf(classPK) %>"
						/>
						<portlet:param
							name="className"
							value="<%= UserSegment.class.getName() %>"
						/>
						<portlet:param
							name="classPK"
							value="<%= String.valueOf(classPK) %>"
						/>
						<portlet:param
							name="tabs2"
							value="report"
						/>
					</liferay-portlet:renderURL>

					<aui:nav-item dropdown="<%= true %>" iconCssClass="icon-plus" id="addButtonContainer" label='<%= LanguageUtil.get(portletConfig.getResourceBundle(locale), "add") %>'>

						<%
						for (Report report : reports) {
							if (report.isInstantiable()) {
						%>

							<liferay-portlet:renderURL var="addReportURL">
								<portlet:param name="mvcRenderCommandName" value="<%= ContentTargetingMVCCommand.EDIT_REPORT %>" />
								<portlet:param name="redirect" value="<%= redirectURL.toString() %>" />
								<portlet:param name="userSegmentId" value="<%= String.valueOf(classPK) %>" />
								<portlet:param name="className" value="<%= UserSegment.class.getName() %>" />
								<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
								<portlet:param name="reportKey" value="<%= report.getReportKey() %>" />
							</liferay-portlet:renderURL>

							<aui:nav-item
								href="<%= addReportURL %>"
								iconCssClass="<%= report.getIcon() %>"
								id="add-<%= report.getReportKey() %>"
								label="<%= report.getName(locale) %>"
							/>

						<%
							}
						}
						%>

					</aui:nav-item>

					<aui:nav-item
						cssClass="hide"
						iconCssClass="icon-remove"
						id="deleteReports"
						label='<%= LanguageUtil.get(portletConfig.getResourceBundle(locale), "delete") %>'
					/>
				</c:if>
			</c:otherwise>
		</c:choose>
	</aui:nav>

	<aui:nav-bar-search cssClass="pull-right">
		<div class="form-search">
			<liferay-ui:input-search
				id="reportkeywords"
				name="reportKeywords"
				placeholder='<%= LanguageUtil.get(portletConfig.getResourceBundle(locale), "keywords") %>'
			/>
		</div>
	</aui:nav-bar-search>
</aui:nav-bar>