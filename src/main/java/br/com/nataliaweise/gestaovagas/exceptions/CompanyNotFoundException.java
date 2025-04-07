package br.com.nataliaweise.gestaovagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(){
        super("A empresa informada não foi encontrada na base de dados.");
    }
}