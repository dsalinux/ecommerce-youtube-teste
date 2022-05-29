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
import java.math.BigDecimal;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Danilo Souza Almeida
 */
@Entity
@Table(name = "item_venda")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemVenda implements Serializable {
    
    @EmbeddedId
    @EqualsAndHashCode.Include
    private ItemVendaId id;
    private Integer quantidade;
    private BigDecimal valor;
//    
}
