package com.projeto.demo.exceptions;

public record ErrorResponse(
        String status,
        String message
){ }
