/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author paco
 */
public interface IGenericoDAO<T> {
    
    public void insertOrUpdate(T objeto);

    public <T> Set<T> get(Class<T> entidad);

    public <T> T getById(Serializable pk, Class<T> claseEntidad);

    public void delete(T objeto);
    
}
