package model;

public class Transaction {
	private String id;
	private String payment;
	private String name;
	public Transaction(String id, String payment, String name) {
		super();
		this.id = id;
		this.payment = payment;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
