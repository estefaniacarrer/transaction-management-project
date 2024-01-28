package com.projeto.demo.controller.dto;

import com.projeto.demo.model.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequest {

    private Long clienteId;
    private Long empresaId;
    private TipoTransacao tipo;
    private BigDecimal valor;

}

