package coding.insight.cleanuiloginregister;

public class purshasy {
    String nameprd;
    String quantity;
    String Date;
    String namevend;
    String price;
    String type;


    public purshasy(String nameprd, String quantity, String date, String namevend, String price, String type) {
        this.nameprd = nameprd;
        this.quantity = quantity;
        Date = date;
        this.namevend = namevend;
        this.price = price;
        this.type = type;
    }
    public purshasy() {

    }
    public String getNameprd() {
        return nameprd;
    }

    public void setNameprd(String nameprd) {
        this.nameprd = nameprd;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNamevend() {
        return namevend;
    }

    public void setNamevend(String namevend) {
        this.namevend = namevend;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}