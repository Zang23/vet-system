package edu.vet.entity;

public class Veterinario {
    
    private String nome;
    private String especialidade;
    private String crv;

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getCrv() {
        return crv;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setCrv(String crv) {
        this.crv = crv;
    }

}
