package com.projeto.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import com.projeto.demo.model.util.TaxaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.projeto.demo.controller.dto.TransacaoRequest;
import com.projeto.demo.model.Cliente;
import com.projeto.demo.model.Empresa;
import com.projeto.demo.model.enums.TipoTransacao;
import com.projeto.demo.model.Transacao;
import com.projeto.demo.repository.ClienteRepository;
import com.projeto.demo.repository.EmpresaRepository;
import com.projeto.demo.repository.TransacaoRepository;
import com.projeto.demo.service.TransacaoService;

public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRealizarTransacao_SuccessfulTransaction() {
        TransacaoRequest transacaoRequest = new TransacaoRequest(1L, 1L,TipoTransacao.DEPOSITO, new BigDecimal("100.00"));

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setSaldo(new BigDecimal("500.00"));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        assertDoesNotThrow(() -> transacaoService.realizarTransacao(transacaoRequest));
        verify(transacaoRepository, times(1)).save(any(Transacao.class));
    }

    @Test
    public void testRealizarTransacao_ApplyWithdrawalFee() {
        TransacaoRequest transacaoRequest = new TransacaoRequest(1L, 1L, TipoTransacao.SAQUE, new BigDecimal("100.00"));

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        Empresa empresa = new Empresa();
        empresa.setId(1L);
        empresa.setSaldo(new BigDecimal("500.00"));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        assertDoesNotThrow(() -> transacaoService.realizarTransacao(transacaoRequest));
        verify(transacaoRepository, times(1)).save(any(Transacao.class));

        BigDecimal saldoEsperado = new BigDecimal("500.00").subtract(new BigDecimal("100.00").add(TaxaUtil.TAXA_SAQUE));
        assertEquals(saldoEsperado, empresa.getSaldo());
    }

}

