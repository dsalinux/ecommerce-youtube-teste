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
package br.com.professordanilo.ecommerceyouteste.logic;

import br.com.professordanilo.ecommerceyouteste.entity.Usuario;
import br.com.professordanilo.ecommerceyouteste.repository.UsuarioRepository;
import br.com.professordanilo.ecommerceyouteste.util.exception.ErroNegocioException;
import br.com.professordanilo.ecommerceyouteste.util.exception.ErroSistemaException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Danilo Souza Almeida
 */
public class UsuarioLogic implements CrudLogic<Usuario, Integer>{

    @Inject
    private UsuarioRepository repository;
    
    @Override
    public Usuario salvar(Usuario entidade) throws ErroNegocioException, ErroSistemaException {
        return this.repository.save(entidade);
    }

    @Override
    public void deletar(Usuario entidade) throws ErroNegocioException, ErroSistemaException {
        this.repository.remove(entidade.getId());
    }

    @Override
    public Usuario bucarPorID(Usuario categoria) throws ErroNegocioException, ErroSistemaException {
        return this.repository.findById(categoria.getId());
    }

    @Override
    public List<Usuario> buscar(Usuario entidade) throws ErroNegocioException, ErroSistemaException {
        return this.repository.findAll();
    }

}
