package br.com.nataliaweise.gestaovagas.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException(){
        super("Usuário já existente na base.");
    }
}
