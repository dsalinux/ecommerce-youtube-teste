//<editor-fold defaultstate="collapsed" desc="License Apache 2.0">
/*
 * Copyright 2022 Danilo Souza Almeida.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//</editor-fold>
package br.com.professordanilo.ecommerceyouteste.repository;

import br.com.professordanilo.ecommerceyouteste.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Danilo Souza Almeida
 */
public class GenericRepository<E, ID extends Serializable> implements Serializable {

    @Inject
    private EntityManager entityManager;
    private final Class<E> entityClass;

    public GenericRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public E save(E entity) throws ErroSistemaException {
        try {
            entity = this.entityManager.merge(entity);
            return entity;
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new ErroSistemaException("Erro ao salvar.", ex);
        }
    }

    public void remove(ID id) throws ErroSistemaException {
        try {

            E entity = this.entityManager.find(this.entityClass, id);
            this.entityManager.remove(entity);

        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new ErroSistemaException("Erro ao salvar.", ex);
        }
    }

    public E findById(ID id) throws ErroSistemaException {
        try {
            return this.entityManager.find(entityClass, id);
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new ErroSistemaException("Erro ao salvar.", ex);
        }
    }

    public List<E> findAll() throws ErroSistemaException {
        try {
            List<E> entitys;
            entitys = this.entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList();
            return entitys;
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new ErroSistemaException("Erro ao salvar.", ex);
        }
    }
}
