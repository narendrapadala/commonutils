package corp.asbp.platform.commonutils.cu.model;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;

import corp.asbp.platform.commonutils.cu.util.NumberUtil;


public class UserFilter {

	private List<Long> roleIds;
	private List<Long> moduleIds;
	
	public UserFilter() {
	}
	
	public UserFilter(UserFilterBuilder builder) {
		this.roleIds = builder.roleIds;
		this.moduleIds = builder.moduleIds;
	}
	
	public static class UserFilterBuilder {
		private List<Long> roleIds;
		private List<Long> moduleIds;
		
		public UserFilterBuilder() {
		}

		
		public UserFilterBuilder roleIds(List<Long> roleIds) {
			this.roleIds = roleIds;
			return this;
		}

		
		public UserFilterBuilder moduleIds(List<Long> moduleIds) {
			this.moduleIds = moduleIds;
			return this;
		}
		
		public UserFilter build() {
			return new UserFilter(this);
		}
	}


	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleId) {
		this.roleIds = roleId;
	}



	public List<Long> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<Long> moduleId) {
		this.moduleIds = moduleId;
	}
	
	public static boolean invalid(List<UserFilter> userFilters) {
		if (CollectionUtils.isNotEmpty(userFilters)) {
			for (UserFilter userFilter : userFilters) {
				if (userFilter != null && !userFilter.isInvalid()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void validate(List<UserFilter> userFilters) {
		if (CollectionUtils.isNotEmpty(userFilters)) {
			for (UserFilter userFilter : userFilters) {
				if (userFilter != null) {
					userFilter.validate();
				}
			}
		}
	}

	private void validate() {
		List<Long> ids = Lists.newArrayList();
		ids.addAll(roleIds);
		ids.addAll(moduleIds);
		NumberUtil.validateAny(ids, "All fields can't be empty!");
	}

	private boolean isInvalid() {
		if ( NumberUtil.isAnyNotNull(roleIds)  || NumberUtil.isAnyNotNull(moduleIds)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserFilter [ roleId=" + roleIds + ", moduleId=" + moduleIds + "]";
	}
}
