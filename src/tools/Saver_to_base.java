/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import Classes_shop.Person;
import Classes_shop.Purchase;
import Classes_shop.Shoes;
import ui.Keeping;

/**
 *
 * @author pupil
 */
public class Saver_to_base implements Keeping{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopshoesPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void save_persons(List<Person> persons) {
        tx.begin();
            for (int i = 0; i < persons.size(); i++) {
                if (persons.get(i).getId() == null) {
                    em.persist(persons.get(i));
                }else{
                    em.merge(persons.get(i));
                }
            }
        tx.commit();    
    }

    @Override
    public List<Person> load_persons() {
        List<Person> persons = null;
        try {
            persons = em.createQuery("SELECT person FROM Person person").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return persons;    }

    @Override
    public void save_shoeses(List<Shoes> shoeses) {
        tx.begin();
            for (int i = 0; i < shoeses.size(); i++) {
                if (shoeses.get(i).getId() == null) {
                    em.persist(shoeses.get(i));
                }else{
                    em.merge(shoeses.get(i));
                }
            }
        tx.commit(); 
    }

    @Override
    public List<Shoes> load_shoes() {
        List<Shoes> shoeses = null;
        try {
            shoeses = em.createQuery("SELECT shoeses FROM Shoes shoeses").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return shoeses;  
    }

    @Override
    public void save_purchase(List<Purchase> purchases) {
        tx.begin();
            for (int i = 0; i < purchases.size(); i++) {
                if (purchases.get(i).getId() == null) {
                    em.persist(purchases.get(i));
                }else{
                em.merge(purchases.get(i));
                }
            }
        tx.commit(); 
    }

    @Override
    public List<Purchase> load_purchase() {
        List<Purchase> purchase = null;
        try {
            purchase = em.createQuery("SELECT purchase FROM Purchase purchase").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return purchase; 
    }
    
    
}
