/**
 * 
 */
package corp.asbp.platform.commonutils.cu.model;

import org.apache.commons.lang.StringUtils;

/**
 * @author Narendra
 *
 */
public enum CommonUserStatus {

	ACTIVE,PENDING_ACTIVATION,UNAPPROVED,BLOCKED,DELETED,ENABLED,DISABLED;
	
	private String status;
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static CommonUserStatus getCommonUserStatus(String status) {
		return StringUtils.isEmpty(status) ? null:CommonUserStatus.valueOf(status);
	}
}
