package model;

public class Products {
	private String jIdCol;
	private String jNameCol;
	private int priceCol;
	private String jDescCol;
	
	public Products(String jId, String jName, int price, String jDesc) {
		super();
		this.jIdCol = jId;
		this.jNameCol = jName;
		this.priceCol = price;
		this.jDescCol = jDesc;
	}
	public String getjId() {
		return jIdCol;
	}
	public void setjId(String jId) {
		this.jIdCol = jId;
	}
	public String getjName() {
		return jNameCol;
	}
	public void setjName(String jName) {
		this.jNameCol = jName;
	}
	public int getPrice() {
		return priceCol;
	}
	public void setPrice(int price) {
		this.priceCol = price;
	}
	public String getjDesc() {
		return jDescCol;
	}
	public void setjDesc(String jDesc) {
		this.jDescCol = jDesc;
	}
	
	
}
