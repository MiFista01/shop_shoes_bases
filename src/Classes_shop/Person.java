package Classes_shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private List<Purchase> purchase = new ArrayList<>();
    private String name;
    private String password;
    private String phone;
    private double purse;
    private double incom = 0;
    
    @Override
    public String toString() {
        return "Person [incom=" + incom + ", name=" + name + ", password=" + password + ", phone=" + phone
                + ", purchase=" + purchase + ", purse=" + purse + "]";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public double getPurse() {
        return purse;
    }
    public void setPurse(double purse) {
        this.purse = purse;
    }
    public List<Purchase> getPurchase() {
        return purchase;
    }
    public void setPurchase(List<Purchase> purchase) {
        this.purchase = purchase;
    }
    public double getIncom() {
        return incom;
    }
    public void setIncom(double incom) {
        this.incom = incom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
 