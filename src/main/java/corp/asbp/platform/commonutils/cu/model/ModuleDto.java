package corp.asbp.platform.commonutils.cu.model;

public class ModuleDto {
	private Long id;
	private String name;
	private Integer displayOrder;
	private String config;
	private ModuleCategoryDto moduleCategory;
	
	public enum ModuleType {
		TMS(2L),
		EMS(22L);
		
		private Long moduleId;
		
		private ModuleType(long moduleId) {
			this.moduleId = moduleId;
		}
		
		public Long moduleId() {
			return this.moduleId;
		}
	}
	
	public ModuleDto() {
	}
	
	public ModuleDto(ModuleDtoBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.displayOrder = builder.displayOrder;
		this.config = builder.config;
		this.moduleCategory = builder.moduleCategory;
	}

	public static class ModuleDtoBuilder {
		private Long id;
		private String name;
		private Integer displayOrder;
		private String config;
		private ModuleCategoryDto moduleCategory;
		
		public ModuleDtoBuilder(Long id) {
			this.id = id;
		}
		
		public ModuleDtoBuilder(Long id, String name, Integer displayOrder) {
			this.id = id;
			this.name = name;
			this.displayOrder = displayOrder;
		}
		
		public ModuleDtoBuilder config(String config) {
			this.config = config;
			return this;
		}
		
		public ModuleDtoBuilder moduleCategory(ModuleCategoryDto moduleCategory) {
			this.moduleCategory = moduleCategory;
			return this;
		}
		
		public ModuleDto build() {
			return new ModuleDto(this);
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

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
	
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public ModuleCategoryDto getModuleCategory() {
		return moduleCategory;
	}

	public void setModuleCategory(ModuleCategoryDto moduleCategory) {
		this.moduleCategory = moduleCategory;
	}

	@Override
	public String toString() {
		return "ModuleDto [id=" + id + ", name=" + name + ", displayOrder="
				+ displayOrder + ", config=" + config + ", moduleCategory="
				+ moduleCategory + "]";
	}
}
