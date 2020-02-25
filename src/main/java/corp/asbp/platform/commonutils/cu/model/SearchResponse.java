package corp.asbp.platform.commonutils.cu.model;

public class SearchResponse {
	private Long entityId;
	private Long userId;
	private String name;
	private String email;
	
	public SearchResponse() {
	}
	
	public SearchResponse(SearchResponseBuilder builder) {
		this.entityId = builder.entityId;
		this.userId = builder.userId;
		this.name = builder.name;
		this.email = builder.email;
	}

	public static class SearchResponseBuilder {
		private Long entityId;
		private Long userId;
		private String name;
		private String email;
		
		public SearchResponseBuilder(Long entityId, String name) {
			this.entityId = entityId;
			this.name = name; 
		}
		
		public SearchResponseBuilder(Long userId, String name, String email) {
			this.userId = userId;
			this.name = name; 
			this.email = email;
		}
		
		public SearchResponse build() {
			return new SearchResponse(this);
		}
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SearchResponse [entityId=" + entityId + ", userId=" + userId
				+ ", name=" + name + ", email=" + email + "]";
	}
}
