package edu.vet.controller;

import java.util.ArrayList;
import java.util.List;

import edu.vet.entity.Veterinario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VeterinarioController {
    
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty especialidade = new SimpleStringProperty("");
    private StringProperty crv = new SimpleStringProperty("");

    private ObservableList<Veterinario> lista = FXCollections.observableArrayList();

    public Veterinario toEntity(){
        Veterinario v = new Veterinario();
        v.setNome(nome.get());
        v.setEspecialidade(especialidade.get());
        v.setCrv(crv.get());

        return v;
    }

    public void fromEntity(Veterinario v){
        if(v != null){
            nome.set(v.getNome());
            especialidade.set(v.getEspecialidade());
            crv.set(v.getCrv());
        }
    }


    public void salvar(){

        lista.add(toEntity());
        System.out.println("Vet salvo com sucesso");
    }

    public void pesquisarPorNome(){

        for(Veterinario v : lista){
            if(v.getNome().contains(nome.get())){
                fromEntity(v);
                break;
            }
        }
    }

    public void pesquisarPorCrv(){
        for(Veterinario v : lista){
            if(v.getCrv().contains(crv.get())){
                fromEntity(v);
                break;
            }
        }
    }

    public void listar(){
        for(Veterinario v: lista){
            System.out.println(v.getNome());
        }
    }

    public StringProperty nomeProperty(){
        return nome;
    }

    public StringProperty especialidadeProperty(){
        return especialidade;
    }

    public StringProperty crvProperty(){
        return crv;
    }

    public ObservableList<Veterinario> getLista(){
        return lista;
    }
}
