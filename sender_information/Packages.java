package sender_information;

import java.io.Serializable;

public class Packages implements Serializable {
    private int id; // ID đơn hàng
    private String sender;  // Họ tên người gửi
    private String receiver;  // Họ tên người nhận
    private String address; // Địa chỉ người nhận
    private String goods;  // Tên đơn hàng
    private String phonenumber; // Số điện thoại người nhận
    private String service; // Dịch vụ giao hàng

    public Packages(int id, String sender, String receiver, String address, String goods, String phonenumber, String service) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.goods = goods;
        this.phonenumber = phonenumber;
        this.service = service;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Đơn hàng[ " +
                "Mã:" + id +
                ", Người gửi: " + sender +
                ", người nhận: " + receiver +
                ", Địa chỉ: " + address +
                ", Tên sản phẩm: " + goods +
                ", SĐT: " + phonenumber +
                ']';
    }
}
