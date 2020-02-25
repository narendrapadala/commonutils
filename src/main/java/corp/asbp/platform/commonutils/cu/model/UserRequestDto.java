/**
 * 
 */
package corp.asbp.platform.commonutils.cu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Narendra
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserRequestDto {
	private String sessionId;
	private String requestUri;
	private String cpVersion;
	private Long zoneId;
	private Long moduleId;
	
	public UserRequestDto(UserRequestDtoBuilder builder) {
		this.sessionId = builder.sessionId;
		this.requestUri = builder.requestUri;
		this.cpVersion = builder.cpVersion;
		this.zoneId = builder.zoneId;
		this.moduleId = builder.moduleId;
	}
	
	public static class UserRequestDtoBuilder {
		private String sessionId;
		private String requestUri;
		private String cpVersion;
		private Long zoneId;
		private Long moduleId;
		
		public UserRequestDtoBuilder(String sessionId, 
									 String cpVersion, 
									 String requestUri, 
									 Long zoneId, 
									 Long moduleId) {
			this.sessionId = sessionId;
			this.cpVersion = cpVersion;
			this.requestUri = requestUri;
			this.zoneId = zoneId;
			this.moduleId = moduleId;
		}
		
		public UserRequestDto build() {
			return new UserRequestDto(this);
		}
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getCpVersion() {
		return cpVersion;
	}

	public void setCpVersion(String cpVersion) {
		this.cpVersion = cpVersion;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	
}
