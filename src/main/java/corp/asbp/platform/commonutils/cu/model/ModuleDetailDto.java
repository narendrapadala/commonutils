/**
 * 
 */
package corp.asbp.platform.commonutils.cu.model;

/**
 * @author rakesh
 *
 */
public class ModuleDetailDto {

	private Long id;
	private String name;
	private Integer displayOrder;
	private String config;
	private ModuleCategoryDto moduleCategory;
	private Long zoneId;
	private Long roleId;
	private Long clientId;
	
	public ModuleDetailDto() {
		
	}
	
	public ModuleDetailDto(ModuleDetailDtoBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.displayOrder = builder.displayOrder;
		this.config = builder.config;
		this.moduleCategory = builder.moduleCategory;
		this.zoneId = builder.zoneId;
		this.roleId = builder.roleId;
		this.clientId = builder.clientId;
	}
	
	public static class ModuleDetailDtoBuilder {
		
		private Long id;
		private String name;
		private Integer displayOrder;
		private String config;
		private ModuleCategoryDto moduleCategory;
		private Long zoneId;
		private Long roleId;
		private Long clientId;
		
		public ModuleDetailDtoBuilder(Long id, String name, Integer displayOrder) {
			this.id = id;
			this.name = name;
			this.displayOrder = displayOrder;
		}
		
		public ModuleDetailDtoBuilder(Long id) {
			this.id = id;
		}
				
		public ModuleDetailDtoBuilder config(String config) {
			this.config = config;
			return this;
		}
		
		public ModuleDetailDtoBuilder moduleCategory(ModuleCategoryDto moduleCategory) {
			this.moduleCategory = moduleCategory;
			return this;
		}
		
		public ModuleDetailDtoBuilder zoneId(Long zoneId) {
			this.zoneId = zoneId;
			return this;
		}
		
		public ModuleDetailDtoBuilder roleId(Long roleId) {
			this.roleId = roleId;
			return this;
		}
		
		public ModuleDetailDtoBuilder clientId(Long clientId) {
			this.clientId = clientId;
			return this;
		}
		
		public ModuleDetailDto build() {
			return new ModuleDetailDto(this);
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public ModuleCategoryDto getModuleCategory() {
		return moduleCategory;
	}

	public void setModuleCategory(ModuleCategoryDto moduleCategory) {
		this.moduleCategory = moduleCategory;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "ModuleDetailDto [id=" + id + ", name=" + name
				+ ", displayOrder=" + displayOrder + ", config=" + config
				+ ", moduleCategory=" + moduleCategory + ", zoneId=" + zoneId
				+ ", roleId=" + roleId + ", clientId=" + clientId + "]";
	}
}
