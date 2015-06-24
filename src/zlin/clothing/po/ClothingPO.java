package zlin.clothing.po;

public class ClothingPO {
	private long id; //id
	private String clothnum; //����
	private String type; //Ʒ��
	private String color; //��ɫ
	private String size; //��С 
	private String fabric; //����
	private String clothingMaterial;//����
	private String factoryPrice; //������
	private String retailPrice;//���ۼ�
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
