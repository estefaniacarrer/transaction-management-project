package com.projeto.demo.controller;

import com.projeto.demo.controller.dto.TransacaoRequest;
import com.projeto.demo.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<String> realizarTransacao(@RequestBody TransacaoRequest transacaoRequest) {
        transacaoService.realizarTransacao(transacaoRequest);
        return new ResponseEntity<>("Transação realizada com sucesso", HttpStatus.OK);
    }
}
