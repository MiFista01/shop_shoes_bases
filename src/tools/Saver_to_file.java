package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Classes_shop.Person;
import Classes_shop.Shoes;
import ui.Keeping;

import java.io.IOException;

public class Saver_to_file implements Keeping{

    @Override
    public List<Person> load_persons() {
        List<Person> persons = new ArrayList<>();
        FileInputStream fis= null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("zadania//shop_shoes//data//Persons");
            ois = new ObjectInputStream(fis);
            persons = (List<Person>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.INFO, "file not created", ex);
        } catch (IOException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.SEVERE, "error to read file", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.INFO, "class Person not found ore created", ex);
        }
        return persons;
    }

    @Override
    public List<Shoes> load_shoes() {
        List<Shoes> shoeses = new ArrayList<>();
        FileInputStream fis= null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream("zadania//shop_shoes//data//Shoeses");
            ois = new ObjectInputStream(fis);
            shoeses = (List<Shoes>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.INFO, "file not created", ex);
        } catch (IOException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.SEVERE, "error to read file", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.INFO, "class Person not found ore created", ex);
        }
        return shoeses;
    }

    @Override
    public void save_persons(List<Person> persons) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("zadania//shop_shoes//data//Persons");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(persons);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.INFO, "no such file", ex);
        } catch (IOException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.SEVERE, "no such file", ex);
        }
        
    }

    @Override
    public void save_shoeses(List<Shoes> shoeses) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("zadania//shop_shoes//data//Shoeses");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(shoeses);
            oos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.INFO, "no such file", ex);
        } catch (IOException ex) {
            Logger.getLogger(Saver_to_file.class.getName()).log(Level.SEVERE, "no such file", ex);
        }
        
    }
    
}
