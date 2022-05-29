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
package br.com.professordanilo.ecommerceyouteste.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author danilo
 */
@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String nome;
    private String sobrenome;
    private Sexo sexo;
    private String cpf;
    private String rg;
    @Column(name = "orgao_expedidor")
    private String orgaoExpedidor;
    private String email;
    private String senha;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Column(name = "data_expiracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExpiracao;
    @Column(name = "data_desativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDesativacao;
    
    @OneToMany
    @JoinColumn(name = "cliente_id")
    private List<Endereco> enderecos;
    
    enum Sexo {
        MASCULINO,
        FEMININO,
        NAO_BINARIO
    }
    
}
