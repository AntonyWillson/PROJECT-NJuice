package model;

public class Detail {
	private String transId;
	private String juiceId;
	private String juiceName;
	private int qty;
	public Detail(String transId, String juiceId, String juiceName, int qty) {
		super();
		this.transId = transId;
		this.juiceId = juiceId;
		this.juiceName = juiceName;
		this.qty = qty;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getJuiceId() {
		return juiceId;
	}
	public void setJuiceId(String juiceId) {
		this.juiceId = juiceId;
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
	
	
}
