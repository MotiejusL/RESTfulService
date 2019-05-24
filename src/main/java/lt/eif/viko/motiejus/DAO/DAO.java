/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.eif.viko.motiejus.DAO;


/**
 * Interface for creating objects
 *
 * @author motsa
 */
public interface DAO<T> {

    T get(T object);

    void insert(T object);

    void update(T object);

    void delete(T object);

}
