package br.com.michael_fausto.formulaSAE.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details){}
