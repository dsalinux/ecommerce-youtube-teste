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

import br.com.professordanilo.ecommerceyouteste.entity.Usuario;
import br.com.professordanilo.ecommerceyouteste.logic.UsuarioLogic;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Danilo Souza Almeida
 */
@Named
@SessionScoped
public class UsuarioBean extends CrudBean<Usuario, UsuarioLogic> {

    @Inject
    private UsuarioLogic logic;

    public UsuarioBean() {
	super(Usuario.class);
    }

    @Override
    public UsuarioLogic getLogic() {
	return this.logic;
    }

}