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
package br.com.professordanilo.ecommerceyouteste.bean;

import br.com.professordanilo.ecommerceyouteste.entity.Categoria;
import br.com.professordanilo.ecommerceyouteste.logic.CategoriaLogic;
import br.com.professordanilo.ecommerceyouteste.util.exception.ErroNegocioException;
import br.com.professordanilo.ecommerceyouteste.util.exception.ErroSistemaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Danilo Souza Almeida
 */
@Named
@SessionScoped
public class CategoriaBean extends CrudBean<Categoria, CategoriaLogic> {

    @Inject
    private CategoriaLogic logic;

    public CategoriaBean() {
	super(Categoria.class);
    }

    @Override
    public CategoriaLogic getLogic() {
	return this.logic;
    }

    public List<Categoria> getCategorias() {
	try {
	    return this.logic.buscar(null);
	} catch (ErroNegocioException ex) {
	    Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ErroSistemaException ex) {
	    Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

}
