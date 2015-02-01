package ShoppingSite;

import java.util.Date;

public class OrdersEntity {
	private int order_number;
	private String member_id;
	private String receiver;
	private String receiver_address;
	private String receiver_province;
	private String receiver_town;
	private int phone_number;
	private String notice;
	private String deposit_confirmation;
	private int payment;
	private Date payment_day;
	
	public String getReceiver_province() {
		return receiver_province;
	}
	public void setReceiver_province(String receiver_province) {
		this.receiver_province = receiver_province;
	}
	public String getReceiver_town() {
		return receiver_town;
	}
	public void setReceiver_town(String receiver_town) {
		this.receiver_town = receiver_town;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getDeposit_confirmation() {
		return deposit_confirmation;
	}
	public void setDeposit_confirmation(String deposit_confirmation) {
		this.deposit_confirmation = deposit_confirmation;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public Date getPayment_day() {
		return payment_day;
	}
	public void setPayment_day(Date payment_day) {
		this.payment_day = payment_day;
	}
}
