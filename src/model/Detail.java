package model;

public class Detail {
	private String idCol1;
	private String jIdCol;
	private String jName;
	private int qty;
	public Detail(String idCol1, String jIdCol, String jName, int qty) {
		super();
		this.idCol1 = idCol1;
		this.jIdCol = jIdCol;
		this.jName = jName;
		this.qty = qty;
	}
	public String getIdCol1() {
		return idCol1;
	}
	public void setIdCol1(String idCol1) {
		this.idCol1 = idCol1;
	}
	public String getjIdCol() {
		return jIdCol;
	}
	public void setjIdCol(String jIdCol) {
		this.jIdCol = jIdCol;
	}
	public String getjName() {
		return jName;
	}
	public void setjName(String jName) {
		this.jName = jName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
