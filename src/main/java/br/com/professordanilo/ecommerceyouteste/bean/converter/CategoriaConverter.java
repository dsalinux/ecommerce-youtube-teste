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
package br.com.professordanilo.ecommerceyouteste.bean.converter;

import br.com.professordanilo.ecommerceyouteste.entity.Categoria;
import br.com.professordanilo.ecommerceyouteste.logic.CategoriaLogic;
import br.com.professordanilo.ecommerceyouteste.util.StringUtil;
import br.com.professordanilo.ecommerceyouteste.util.exception.ErroNegocioException;
import br.com.professordanilo.ecommerceyouteste.util.exception.ErroSistemaException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Danilo Souza Almeida
 */
@FacesConverter(value = "categoriaConverter", forClass = Categoria.class, managed = true)
public class CategoriaConverter implements Converter<Categoria>, Serializable {

//    @Inject
    private CategoriaLogic logic = CDI.current().select(CategoriaLogic.class).get();
    
    @Override
    public Categoria getAsObject(FacesContext fc, UIComponent uic, String chave) {
        if(!StringUtil.isEmpty(chave)){
            try {
                Integer id = Integer.parseInt(chave);
                return logic.bucarPorID(new Categoria(id));
            } catch (ErroNegocioException ex) {
                Logger.getLogger(CategoriaConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ErroSistemaException ex) {
                Logger.getLogger(CategoriaConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException ex) {
		Logger.getLogger(CategoriaConverter.class.getName()).log(Level.SEVERE, null, ex);
	    }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Categoria t) {
        if(t != null) {
            return t.getId().toString();
        }
        return null;
    }


}
