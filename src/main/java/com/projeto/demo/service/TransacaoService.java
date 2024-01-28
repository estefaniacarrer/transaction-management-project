package com.projeto.demo.service;


import com.projeto.demo.controller.dto.TransacaoRequest;
import com.projeto.demo.exceptions.NotFoundException;
import com.projeto.demo.exceptions.ValueNotAllowed;
import com.projeto.demo.model.Cliente;
import com.projeto.demo.model.Empresa;
import com.projeto.demo.model.enums.TipoTransacao;
import com.projeto.demo.model.Transacao;
import com.projeto.demo.model.util.TaxaUtil;
import com.projeto.demo.repository.ClienteRepository;
import com.projeto.demo.repository.EmpresaRepository;
import com.projeto.demo.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void realizarTransacao(TransacaoRequest transacaoRequest)  {
        Cliente cliente = clienteRepository.findById(transacaoRequest.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));

        Empresa empresa = empresaRepository.findById(transacaoRequest.getEmpresaId())
                .orElseThrow(() -> new NotFoundException("Empresa não encontrada!"));

        BigDecimal valorTransacao = transacaoRequest.getValor();
        BigDecimal saldoEmpresa = empresa.getSaldo();

        if (transacaoRequest.getTipo() == TipoTransacao.SAQUE) {
            valorTransacao = valorTransacao.add(TaxaUtil.TAXA_SAQUE);
        }

        if (saldoEmpresa.compareTo(valorTransacao) < 0) {
            throw new ValueNotAllowed("Saldo insuficiente na empresa para realizar a transação!");
        }

        if (transacaoRequest.getTipo() == TipoTransacao.SAQUE) {
            empresa.setSaldo(saldoEmpresa.subtract(valorTransacao));
        } else {
            empresa.setSaldo(saldoEmpresa.add(valorTransacao));
        }
        empresaRepository.save(empresa);

        Transacao transacao = new Transacao();
        transacao.setCliente(cliente);
        transacao.setEmpresa(empresa);
        transacao.setTipo(transacaoRequest.getTipo());
        transacao.setValor(valorTransacao);
        transacao.setData(LocalDateTime.now());
        transacaoRepository.save(transacao);

        enviarCallbackParaEmpresa(transacao);
    }

    private void enviarCallbackParaEmpresa(Transacao transacao) {
        String callbackUrl = "https://webhook.site//9c551553-409a-4d36-aced-aa2c36fc502e";

        String dadosParaEmpresa = "Nova transação realizada: " + transacao.getId() + " - Valor: " + transacao.getValor();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(callbackUrl, dadosParaEmpresa, String.class);
    }

}

