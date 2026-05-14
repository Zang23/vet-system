package edu.vet.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.vet.entity.Veterinario;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VeterinarioController {

    private static final String DB_JDBC_URI = "jdbc:mariadb://localhost:3307/agenda?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "alunofatec";

    private Connection con;

    public VeterinarioController(){
        

        try {        
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Classe carregada...");
            con = DriverManager.getConnection(DB_JDBC_URI, DB_USER, DB_PASS);
            System.out.println("Conexao foi feita com sucesso");
        } catch (ClassNotFoundException e) { 
            System.out.println("Erro ao carregar a classe");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }

    }
    
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


        Veterinario vet = toEntity();

         try { 
            String sql = "INSERT INTO veterinario (nome, especialidade, crv) VALUES " +
           "(?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, vet.getNome());
            stm.setString(2, vet.getEspecialidade());
            stm.setString(3, vet.getCrv());

            stm.executeUpdate();
            System.out.println("Comando executado com sucesso");   
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        }

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
