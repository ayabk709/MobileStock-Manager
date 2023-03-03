package coding.insight.cleanuiloginregister;

import android.view.View;

public class sales {
    String nameprd;
    String quantity;
    String Date;
    String namecust;
    String price;
    String type;

    public String getNameprd() {
        return nameprd;
    }

    public sales(String nameprd, String quantity, String date, String namecust, String price, String type) {
        this.nameprd = nameprd;
        this.quantity = quantity;
        Date = date;
        this.namecust = namecust;
        this.price = price;
        this.type = type;
    }

    public sales() {

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

    public String getNamecust() {
        return namecust;
    }

    public void setNamecust(String namecust) {
        this.namecust = namecust;
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
