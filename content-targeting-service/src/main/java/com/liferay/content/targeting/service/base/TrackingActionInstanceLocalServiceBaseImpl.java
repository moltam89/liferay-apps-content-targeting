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

package com.liferay.content.targeting.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.content.targeting.model.TrackingActionInstance;
import com.liferay.content.targeting.service.TrackingActionInstanceLocalService;
import com.liferay.content.targeting.service.persistence.AnonymousUserUserSegmentPersistence;
import com.liferay.content.targeting.service.persistence.CampaignFinder;
import com.liferay.content.targeting.service.persistence.CampaignPersistence;
import com.liferay.content.targeting.service.persistence.ChannelInstancePersistence;
import com.liferay.content.targeting.service.persistence.ReportInstancePersistence;
import com.liferay.content.targeting.service.persistence.RuleInstancePersistence;
import com.liferay.content.targeting.service.persistence.TacticPersistence;
import com.liferay.content.targeting.service.persistence.TrackingActionInstancePersistence;
import com.liferay.content.targeting.service.persistence.UserSegmentPersistence;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.SystemEventPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the tracking action instance local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.content.targeting.service.impl.TrackingActionInstanceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.content.targeting.service.impl.TrackingActionInstanceLocalServiceImpl
 * @see com.liferay.content.targeting.service.TrackingActionInstanceLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class TrackingActionInstanceLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements TrackingActionInstanceLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.content.targeting.service.TrackingActionInstanceLocalServiceUtil} to access the tracking action instance local service.
	 */

	/**
	 * Adds the tracking action instance to the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackingActionInstance the tracking action instance
	 * @return the tracking action instance that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TrackingActionInstance addTrackingActionInstance(
		TrackingActionInstance trackingActionInstance) {
		trackingActionInstance.setNew(true);

		return trackingActionInstancePersistence.update(trackingActionInstance);
	}

	/**
	 * Creates a new tracking action instance with the primary key. Does not add the tracking action instance to the database.
	 *
	 * @param trackingActionInstanceId the primary key for the new tracking action instance
	 * @return the new tracking action instance
	 */
	@Override
	public TrackingActionInstance createTrackingActionInstance(
		long trackingActionInstanceId) {
		return trackingActionInstancePersistence.create(trackingActionInstanceId);
	}

	/**
	 * Deletes the tracking action instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackingActionInstanceId the primary key of the tracking action instance
	 * @return the tracking action instance that was removed
	 * @throws PortalException if a tracking action instance with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TrackingActionInstance deleteTrackingActionInstance(
		long trackingActionInstanceId) throws PortalException {
		return trackingActionInstancePersistence.remove(trackingActionInstanceId);
	}

	/**
	 * Deletes the tracking action instance from the database. Also notifies the appropriate model listeners.
	 *
	 * @param trackingActionInstance the tracking action instance
	 * @return the tracking action instance that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TrackingActionInstance deleteTrackingActionInstance(
		TrackingActionInstance trackingActionInstance)
		throws PortalException {
		return trackingActionInstancePersistence.remove(trackingActionInstance);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(TrackingActionInstance.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return trackingActionInstancePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.TrackingActionInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return trackingActionInstancePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.TrackingActionInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return trackingActionInstancePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return trackingActionInstancePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return trackingActionInstancePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public TrackingActionInstance fetchTrackingActionInstance(
		long trackingActionInstanceId) {
		return trackingActionInstancePersistence.fetchByPrimaryKey(trackingActionInstanceId);
	}

	/**
	 * Returns the tracking action instance matching the UUID and group.
	 *
	 * @param uuid the tracking action instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tracking action instance, or <code>null</code> if a matching tracking action instance could not be found
	 */
	@Override
	public TrackingActionInstance fetchTrackingActionInstanceByUuidAndGroupId(
		String uuid, long groupId) {
		return trackingActionInstancePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the tracking action instance with the primary key.
	 *
	 * @param trackingActionInstanceId the primary key of the tracking action instance
	 * @return the tracking action instance
	 * @throws PortalException if a tracking action instance with the primary key could not be found
	 */
	@Override
	public TrackingActionInstance getTrackingActionInstance(
		long trackingActionInstanceId) throws PortalException {
		return trackingActionInstancePersistence.findByPrimaryKey(trackingActionInstanceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.content.targeting.service.TrackingActionInstanceLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TrackingActionInstance.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"trackingActionInstanceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.content.targeting.service.TrackingActionInstanceLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(TrackingActionInstance.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"trackingActionInstanceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.content.targeting.service.TrackingActionInstanceLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TrackingActionInstance.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"trackingActionInstanceId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<TrackingActionInstance>() {
				@Override
				public void performAction(
					TrackingActionInstance trackingActionInstance)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						trackingActionInstance);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(
					TrackingActionInstance.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return trackingActionInstanceLocalService.deleteTrackingActionInstance((TrackingActionInstance)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return trackingActionInstancePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the tracking action instances matching the UUID and company.
	 *
	 * @param uuid the UUID of the tracking action instances
	 * @param companyId the primary key of the company
	 * @return the matching tracking action instances, or an empty list if no matches were found
	 */
	@Override
	public List<TrackingActionInstance> getTrackingActionInstancesByUuidAndCompanyId(
		String uuid, long companyId) {
		return trackingActionInstancePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of tracking action instances matching the UUID and company.
	 *
	 * @param uuid the UUID of the tracking action instances
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of tracking action instances
	 * @param end the upper bound of the range of tracking action instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching tracking action instances, or an empty list if no matches were found
	 */
	@Override
	public List<TrackingActionInstance> getTrackingActionInstancesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<TrackingActionInstance> orderByComparator) {
		return trackingActionInstancePersistence.findByUuid_C(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	 * Returns the tracking action instance matching the UUID and group.
	 *
	 * @param uuid the tracking action instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching tracking action instance
	 * @throws PortalException if a matching tracking action instance could not be found
	 */
	@Override
	public TrackingActionInstance getTrackingActionInstanceByUuidAndGroupId(
		String uuid, long groupId) throws PortalException {
		return trackingActionInstancePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the tracking action instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.content.targeting.model.impl.TrackingActionInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of tracking action instances
	 * @param end the upper bound of the range of tracking action instances (not inclusive)
	 * @return the range of tracking action instances
	 */
	@Override
	public List<TrackingActionInstance> getTrackingActionInstances(int start,
		int end) {
		return trackingActionInstancePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of tracking action instances.
	 *
	 * @return the number of tracking action instances
	 */
	@Override
	public int getTrackingActionInstancesCount() {
		return trackingActionInstancePersistence.countAll();
	}

	/**
	 * Updates the tracking action instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param trackingActionInstance the tracking action instance
	 * @return the tracking action instance that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TrackingActionInstance updateTrackingActionInstance(
		TrackingActionInstance trackingActionInstance) {
		return trackingActionInstancePersistence.update(trackingActionInstance);
	}

	/**
	 * Returns the anonymous user user segment local service.
	 *
	 * @return the anonymous user user segment local service
	 */
	public com.liferay.content.targeting.service.AnonymousUserUserSegmentLocalService getAnonymousUserUserSegmentLocalService() {
		return anonymousUserUserSegmentLocalService;
	}

	/**
	 * Sets the anonymous user user segment local service.
	 *
	 * @param anonymousUserUserSegmentLocalService the anonymous user user segment local service
	 */
	public void setAnonymousUserUserSegmentLocalService(
		com.liferay.content.targeting.service.AnonymousUserUserSegmentLocalService anonymousUserUserSegmentLocalService) {
		this.anonymousUserUserSegmentLocalService = anonymousUserUserSegmentLocalService;
	}

	/**
	 * Returns the anonymous user user segment persistence.
	 *
	 * @return the anonymous user user segment persistence
	 */
	public AnonymousUserUserSegmentPersistence getAnonymousUserUserSegmentPersistence() {
		return anonymousUserUserSegmentPersistence;
	}

	/**
	 * Sets the anonymous user user segment persistence.
	 *
	 * @param anonymousUserUserSegmentPersistence the anonymous user user segment persistence
	 */
	public void setAnonymousUserUserSegmentPersistence(
		AnonymousUserUserSegmentPersistence anonymousUserUserSegmentPersistence) {
		this.anonymousUserUserSegmentPersistence = anonymousUserUserSegmentPersistence;
	}

	/**
	 * Returns the campaign local service.
	 *
	 * @return the campaign local service
	 */
	public com.liferay.content.targeting.service.CampaignLocalService getCampaignLocalService() {
		return campaignLocalService;
	}

	/**
	 * Sets the campaign local service.
	 *
	 * @param campaignLocalService the campaign local service
	 */
	public void setCampaignLocalService(
		com.liferay.content.targeting.service.CampaignLocalService campaignLocalService) {
		this.campaignLocalService = campaignLocalService;
	}

	/**
	 * Returns the campaign persistence.
	 *
	 * @return the campaign persistence
	 */
	public CampaignPersistence getCampaignPersistence() {
		return campaignPersistence;
	}

	/**
	 * Sets the campaign persistence.
	 *
	 * @param campaignPersistence the campaign persistence
	 */
	public void setCampaignPersistence(CampaignPersistence campaignPersistence) {
		this.campaignPersistence = campaignPersistence;
	}

	/**
	 * Returns the campaign finder.
	 *
	 * @return the campaign finder
	 */
	public CampaignFinder getCampaignFinder() {
		return campaignFinder;
	}

	/**
	 * Sets the campaign finder.
	 *
	 * @param campaignFinder the campaign finder
	 */
	public void setCampaignFinder(CampaignFinder campaignFinder) {
		this.campaignFinder = campaignFinder;
	}

	/**
	 * Returns the channel instance local service.
	 *
	 * @return the channel instance local service
	 */
	public com.liferay.content.targeting.service.ChannelInstanceLocalService getChannelInstanceLocalService() {
		return channelInstanceLocalService;
	}

	/**
	 * Sets the channel instance local service.
	 *
	 * @param channelInstanceLocalService the channel instance local service
	 */
	public void setChannelInstanceLocalService(
		com.liferay.content.targeting.service.ChannelInstanceLocalService channelInstanceLocalService) {
		this.channelInstanceLocalService = channelInstanceLocalService;
	}

	/**
	 * Returns the channel instance persistence.
	 *
	 * @return the channel instance persistence
	 */
	public ChannelInstancePersistence getChannelInstancePersistence() {
		return channelInstancePersistence;
	}

	/**
	 * Sets the channel instance persistence.
	 *
	 * @param channelInstancePersistence the channel instance persistence
	 */
	public void setChannelInstancePersistence(
		ChannelInstancePersistence channelInstancePersistence) {
		this.channelInstancePersistence = channelInstancePersistence;
	}

	/**
	 * Returns the report instance local service.
	 *
	 * @return the report instance local service
	 */
	public com.liferay.content.targeting.service.ReportInstanceLocalService getReportInstanceLocalService() {
		return reportInstanceLocalService;
	}

	/**
	 * Sets the report instance local service.
	 *
	 * @param reportInstanceLocalService the report instance local service
	 */
	public void setReportInstanceLocalService(
		com.liferay.content.targeting.service.ReportInstanceLocalService reportInstanceLocalService) {
		this.reportInstanceLocalService = reportInstanceLocalService;
	}

	/**
	 * Returns the report instance persistence.
	 *
	 * @return the report instance persistence
	 */
	public ReportInstancePersistence getReportInstancePersistence() {
		return reportInstancePersistence;
	}

	/**
	 * Sets the report instance persistence.
	 *
	 * @param reportInstancePersistence the report instance persistence
	 */
	public void setReportInstancePersistence(
		ReportInstancePersistence reportInstancePersistence) {
		this.reportInstancePersistence = reportInstancePersistence;
	}

	/**
	 * Returns the rule instance local service.
	 *
	 * @return the rule instance local service
	 */
	public com.liferay.content.targeting.service.RuleInstanceLocalService getRuleInstanceLocalService() {
		return ruleInstanceLocalService;
	}

	/**
	 * Sets the rule instance local service.
	 *
	 * @param ruleInstanceLocalService the rule instance local service
	 */
	public void setRuleInstanceLocalService(
		com.liferay.content.targeting.service.RuleInstanceLocalService ruleInstanceLocalService) {
		this.ruleInstanceLocalService = ruleInstanceLocalService;
	}

	/**
	 * Returns the rule instance persistence.
	 *
	 * @return the rule instance persistence
	 */
	public RuleInstancePersistence getRuleInstancePersistence() {
		return ruleInstancePersistence;
	}

	/**
	 * Sets the rule instance persistence.
	 *
	 * @param ruleInstancePersistence the rule instance persistence
	 */
	public void setRuleInstancePersistence(
		RuleInstancePersistence ruleInstancePersistence) {
		this.ruleInstancePersistence = ruleInstancePersistence;
	}

	/**
	 * Returns the tactic local service.
	 *
	 * @return the tactic local service
	 */
	public com.liferay.content.targeting.service.TacticLocalService getTacticLocalService() {
		return tacticLocalService;
	}

	/**
	 * Sets the tactic local service.
	 *
	 * @param tacticLocalService the tactic local service
	 */
	public void setTacticLocalService(
		com.liferay.content.targeting.service.TacticLocalService tacticLocalService) {
		this.tacticLocalService = tacticLocalService;
	}

	/**
	 * Returns the tactic persistence.
	 *
	 * @return the tactic persistence
	 */
	public TacticPersistence getTacticPersistence() {
		return tacticPersistence;
	}

	/**
	 * Sets the tactic persistence.
	 *
	 * @param tacticPersistence the tactic persistence
	 */
	public void setTacticPersistence(TacticPersistence tacticPersistence) {
		this.tacticPersistence = tacticPersistence;
	}

	/**
	 * Returns the tracking action instance local service.
	 *
	 * @return the tracking action instance local service
	 */
	public TrackingActionInstanceLocalService getTrackingActionInstanceLocalService() {
		return trackingActionInstanceLocalService;
	}

	/**
	 * Sets the tracking action instance local service.
	 *
	 * @param trackingActionInstanceLocalService the tracking action instance local service
	 */
	public void setTrackingActionInstanceLocalService(
		TrackingActionInstanceLocalService trackingActionInstanceLocalService) {
		this.trackingActionInstanceLocalService = trackingActionInstanceLocalService;
	}

	/**
	 * Returns the tracking action instance persistence.
	 *
	 * @return the tracking action instance persistence
	 */
	public TrackingActionInstancePersistence getTrackingActionInstancePersistence() {
		return trackingActionInstancePersistence;
	}

	/**
	 * Sets the tracking action instance persistence.
	 *
	 * @param trackingActionInstancePersistence the tracking action instance persistence
	 */
	public void setTrackingActionInstancePersistence(
		TrackingActionInstancePersistence trackingActionInstancePersistence) {
		this.trackingActionInstancePersistence = trackingActionInstancePersistence;
	}

	/**
	 * Returns the user segment local service.
	 *
	 * @return the user segment local service
	 */
	public com.liferay.content.targeting.service.UserSegmentLocalService getUserSegmentLocalService() {
		return userSegmentLocalService;
	}

	/**
	 * Sets the user segment local service.
	 *
	 * @param userSegmentLocalService the user segment local service
	 */
	public void setUserSegmentLocalService(
		com.liferay.content.targeting.service.UserSegmentLocalService userSegmentLocalService) {
		this.userSegmentLocalService = userSegmentLocalService;
	}

	/**
	 * Returns the user segment persistence.
	 *
	 * @return the user segment persistence
	 */
	public UserSegmentPersistence getUserSegmentPersistence() {
		return userSegmentPersistence;
	}

	/**
	 * Sets the user segment persistence.
	 *
	 * @param userSegmentPersistence the user segment persistence
	 */
	public void setUserSegmentPersistence(
		UserSegmentPersistence userSegmentPersistence) {
		this.userSegmentPersistence = userSegmentPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the system event local service.
	 *
	 * @return the system event local service
	 */
	public com.liferay.portal.kernel.service.SystemEventLocalService getSystemEventLocalService() {
		return systemEventLocalService;
	}

	/**
	 * Sets the system event local service.
	 *
	 * @param systemEventLocalService the system event local service
	 */
	public void setSystemEventLocalService(
		com.liferay.portal.kernel.service.SystemEventLocalService systemEventLocalService) {
		this.systemEventLocalService = systemEventLocalService;
	}

	/**
	 * Returns the system event persistence.
	 *
	 * @return the system event persistence
	 */
	public SystemEventPersistence getSystemEventPersistence() {
		return systemEventPersistence;
	}

	/**
	 * Sets the system event persistence.
	 *
	 * @param systemEventPersistence the system event persistence
	 */
	public void setSystemEventPersistence(
		SystemEventPersistence systemEventPersistence) {
		this.systemEventPersistence = systemEventPersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.content.targeting.model.TrackingActionInstance",
			trackingActionInstanceLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.content.targeting.model.TrackingActionInstance");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return TrackingActionInstanceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return TrackingActionInstance.class;
	}

	protected String getModelClassName() {
		return TrackingActionInstance.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = trackingActionInstancePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.content.targeting.service.AnonymousUserUserSegmentLocalService.class)
	protected com.liferay.content.targeting.service.AnonymousUserUserSegmentLocalService anonymousUserUserSegmentLocalService;
	@BeanReference(type = AnonymousUserUserSegmentPersistence.class)
	protected AnonymousUserUserSegmentPersistence anonymousUserUserSegmentPersistence;
	@BeanReference(type = com.liferay.content.targeting.service.CampaignLocalService.class)
	protected com.liferay.content.targeting.service.CampaignLocalService campaignLocalService;
	@BeanReference(type = CampaignPersistence.class)
	protected CampaignPersistence campaignPersistence;
	@BeanReference(type = CampaignFinder.class)
	protected CampaignFinder campaignFinder;
	@BeanReference(type = com.liferay.content.targeting.service.ChannelInstanceLocalService.class)
	protected com.liferay.content.targeting.service.ChannelInstanceLocalService channelInstanceLocalService;
	@BeanReference(type = ChannelInstancePersistence.class)
	protected ChannelInstancePersistence channelInstancePersistence;
	@BeanReference(type = com.liferay.content.targeting.service.ReportInstanceLocalService.class)
	protected com.liferay.content.targeting.service.ReportInstanceLocalService reportInstanceLocalService;
	@BeanReference(type = ReportInstancePersistence.class)
	protected ReportInstancePersistence reportInstancePersistence;
	@BeanReference(type = com.liferay.content.targeting.service.RuleInstanceLocalService.class)
	protected com.liferay.content.targeting.service.RuleInstanceLocalService ruleInstanceLocalService;
	@BeanReference(type = RuleInstancePersistence.class)
	protected RuleInstancePersistence ruleInstancePersistence;
	@BeanReference(type = com.liferay.content.targeting.service.TacticLocalService.class)
	protected com.liferay.content.targeting.service.TacticLocalService tacticLocalService;
	@BeanReference(type = TacticPersistence.class)
	protected TacticPersistence tacticPersistence;
	@BeanReference(type = com.liferay.content.targeting.service.TrackingActionInstanceLocalService.class)
	protected TrackingActionInstanceLocalService trackingActionInstanceLocalService;
	@BeanReference(type = TrackingActionInstancePersistence.class)
	protected TrackingActionInstancePersistence trackingActionInstancePersistence;
	@BeanReference(type = com.liferay.content.targeting.service.UserSegmentLocalService.class)
	protected com.liferay.content.targeting.service.UserSegmentLocalService userSegmentLocalService;
	@BeanReference(type = UserSegmentPersistence.class)
	protected UserSegmentPersistence userSegmentPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.SystemEventLocalService.class)
	protected com.liferay.portal.kernel.service.SystemEventLocalService systemEventLocalService;
	@ServiceReference(type = SystemEventPersistence.class)
	protected SystemEventPersistence systemEventPersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}