package corp.asbp.platform.commonutils.cu.model;

public class ModuleCategoryDto {
	private Long id;
	private String name;
	private Integer displayOrder;
	
	public ModuleCategoryDto() {
	}
	
	public ModuleCategoryDto(ModuleCategoryDtoBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.displayOrder = builder.displayOrder;
	}

	public static class ModuleCategoryDtoBuilder {
		private Long id;
		private String name;
		private Integer displayOrder;
		
		public ModuleCategoryDtoBuilder(Long id, String name, Integer displayOrder) {
			this.id = id;
			this.name = name;
			this.displayOrder = displayOrder;
		}
		
		public ModuleCategoryDto build() {
			return new ModuleCategoryDto(this);
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

	@Override
	public String toString() {
		return "ModuleCategoryDto [id=" + id + ", name=" + name
				+ ", displayOrder=" + displayOrder + "]";
	}
}
