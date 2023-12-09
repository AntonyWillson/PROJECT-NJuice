package model;

public class CartItem {
	private String juiceName;
	private int qty;
	private int price;
	public CartItem(String juiceName, int qty, int price) {
		super();
		this.juiceName = juiceName;
		this.qty = qty;
		this.price = price;
	}
	public String getJuiceName() {
		return juiceName;
	}
	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
