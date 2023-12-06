package model;
public class Products {

	public String juiceID;
	public String juiceName;
	public int juicePrice;
	public String juiceDescription;
	
	public Products(String juiceID, String juiceName, int juicePrice, String juiceDescription) {
		super();
		this.juiceID = juiceID;
		this.juiceName = juiceName;
		this.juicePrice = juicePrice;
		this.juiceDescription = juiceDescription;
	}

	public String getJuiceID() {
		return juiceID;
	}

	public void setJuiceID(String juiceID) {
		this.juiceID = juiceID;
	}

	public String getJuiceName() {
		return juiceName;
	}

	public void setJuiceName(String juiceName) {
		this.juiceName = juiceName;
	}

	public int getJuicePrice() {
		return juicePrice;
	}

	public void setJuicePrice(int juicePrice) {
		this.juicePrice = juicePrice;
	}

	public String getJuiceDescription() {
		return juiceDescription;
	}

	public void setJuiceDescription(String juiceDescription) {
		this.juiceDescription = juiceDescription;
	}
	
	
	
	
}
