package coding.insight.cleanuiloginregister;

import android.widget.EditText;

public class Product {
   String name;
    String  color;
    String category;
    String price;
     String quantity;
   String number_code ;
   String image;

   public  Product(){

   }
    public Product(String name, String color, String category, String price, String quantity, String number_code,String image) {
        this.name = name;
        this.color = color;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.number_code = number_code;
this. image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String  getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNumber_code() {
        return number_code;
    }

    public void setNumber_code(String number_code) {
        this.number_code = number_code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
