package ui;

import java.util.List;

import Classes_shop.Person;
import Classes_shop.Purchase;
import Classes_shop.Shoes;

public interface Keeping {
    public void save_persons(List<Person>persons);
    public List<Person> load_persons();

    public void save_shoeses(List<Shoes> shoeses);
    public List<Shoes> load_shoes();
    
    public void save_purchase(List<Purchase> purchases);
    public List<Purchase> load_purchase();
}
