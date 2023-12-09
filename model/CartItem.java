package model;

public class CartItem {
    private String juiceName;
    private int quantity;
    private int totalPrice;
    private String juiceId;

    public CartItem(String juiceName, int quantity, int totalPrice, String juiceId) {
        this.juiceName = juiceName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.juiceId = juiceId;
    }

	public String getJuiceName() {
		return juiceName;
	}

	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getJuiceId() {
		return juiceId;
	}

	public void setJuiceId(String juiceId) {
		this.juiceId = juiceId;
	}


}
