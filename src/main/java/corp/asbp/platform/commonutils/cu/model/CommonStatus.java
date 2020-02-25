package corp.asbp.platform.commonutils.cu.model;

import org.apache.commons.lang.StringUtils;

public enum CommonStatus {
	ENABLED("ENABLED"),
	DISABLED("DISABLED");
	
	private String name;
	
	private CommonStatus(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public static CommonStatus getStatus(String status) {
		return StringUtils.isNotBlank(status) ? CommonStatus.valueOf(status) : null;
	}
	
//	ENABLED,
//	DISABLED;
}