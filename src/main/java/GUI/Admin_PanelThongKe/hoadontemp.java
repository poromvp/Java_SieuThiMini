package GUI.Admin_PanelThongKe;

public class hoadontemp {
    public String id, name, price, date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public hoadontemp(String id, String name, String price, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
