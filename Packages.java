import java.io.Serializable;

public class Packages implements Serializable {
    private int id; // ID don hang
    private String sender;  // Ho ten nguoi gui
    private String receiver;  // Ho ten nguoi nhan
    private String address; // Dia chi nguoi nhan
    private String goods;  // Ten hang hoa
    private double weight; // Trong luong hang hoa (kg)
    private String service; // true: Hoa toc  false: Thuong

    public Packages(int id, String sender, String receiver, String address, String goods, double weight, String service) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.address = address;
        this.goods = goods;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
