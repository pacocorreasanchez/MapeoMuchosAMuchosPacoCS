/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.persistencia.HibernateUtil;
import java.io.Serializable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 *
 * @author paco
 */
public class GenericoDAO<T> implements IGenericoDAO<T> {
    
    private Session sesion;
    
    private void startTransaction(){
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
        System.out.println(sesion);
    }
    
    private void endTransaction(){
        if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            sesion.getTransaction().commit();
        }
        sesion.close();
    }
    
    private void handleExcepcion(HibernateException he) throws HibernateException {
        if (sesion.getTransaction().isActive()) {
            sesion.getTransaction().rollback();
        }
        throw he;
    } 

    @Override
    public void insertOrUpdate(T objeto) {
        try {
            startTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.flush();
            
        } catch (HibernateException he){
            handleExcepcion(he);
        } finally {
            endTransaction();
        }
    }

    @Override
    public <T> Set<T> get(Class<T> entidad) {
        List<T> listadoResultados = null;
        Set<T> targetSet = null;
        try {
            startTransaction();
            listadoResultados =  sesion.createQuery(" from " + entidad.getSimpleName()).list();
            targetSet = new HashSet<T>(listadoResultados);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return targetSet;
    }

    @Override
    public <T> T getById(Serializable pk, Class<T> claseEntidad) {
        T objetoRecuperado = null;
        
        try {
            startTransaction();
            objetoRecuperado = sesion.get(claseEntidad, pk);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        
        return objetoRecuperado;
    }

    @Override
    public void delete(T objeto) {
        try {
            startTransaction();
            sesion.delete(objeto);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
    }
    
 }
