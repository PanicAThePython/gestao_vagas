package br.com.nataliaweise.gestaovagas.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(){
        super("Vaga não encontrada.");
    }
}
