package coding.insight.cleanuiloginregister;

public class custumor {
    String name;
    String adress;
    String mobile;
    String email;
    String company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public custumor(String name, String adress, String mobile, String email, String company) {
        this.name = name;
        this.adress = adress;
        this.mobile = mobile;
        this.email = email;
        this.company = company;
    }
    public custumor() {

    }
}
