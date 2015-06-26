package zlin.clothing.po;

public class ClothingPO {
	private long id; //id
	private String clothnum; //货号
	private String type; //品名
	private String color; //颜色
	private String size; //大小
	private String fabric; //面料
	private String clothingMaterial;//里料
	private String factoryPrice; //成本价
	private String retailPrice;//零售价
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClothnum() {
		return clothnum;
	}
	public void setClothnum(String clothnum) {
		this.clothnum = clothnum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFabric() {
		return fabric;
	}
	public void setFabric(String fabric) {
		this.fabric = fabric;
	}
	public String getClothingMaterial() {
		return clothingMaterial;
	}
	public void setClothingMaterial(String clothingMaterial) {
		this.clothingMaterial = clothingMaterial;
	}
	public String getFactoryPrice() {
		return factoryPrice;
	}
	public void setFactoryPrice(String factoryPrice) {
		this.factoryPrice = factoryPrice;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
}
