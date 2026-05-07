package edu.vet.boundary;

import edu.vet.entity.Veterinario;
import edu.vet.controller.VeterinarioController;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class VeterinarioBoundary extends Application {
    
    private TextField txtNome = new TextField();
    private TextField txtEspecialidade = new TextField();
    private TextField txtCrv = new TextField();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnListar = new Button("Listar");

    private VeterinarioController ctrl = new VeterinarioController();

    private TableView<Veterinario> table = new TableView<>();

    public void start(Stage stage) {
        
        BorderPane panePrincipal = new BorderPane();

        GridPane pane = new GridPane();

        pane.add(new Label("Nome: "), 0,0);
        pane.add(txtNome, 1, 0);

        pane.add(new Label("Especialidade: "),0,1);
        pane.add(txtEspecialidade, 1,1);

        pane.add(new Label("CRV: "), 0,2);
        pane.add(txtCrv, 1, 2);

        pane.add(btnSalvar,0,3);
        pane.add(btnPesquisar,1,3);
        pane.add(btnListar,2,3);

        Scene scn = new Scene(pane, 400, 300);

        btnSalvar.setOnAction(e -> ctrl.salvar());
        btnPesquisar.setOnAction(e -> ctrl.pesquisarPorNome());
        btnListar.setOnAction(e -> ctrl.listar());

        Bindings.bindBidirectional(txtNome.textProperty(), ctrl.nomeProperty());
        Bindings.bindBidirectional(txtEspecialidade.textProperty(), ctrl.especialidadeProperty());
        Bindings.bindBidirectional(txtCrv.textProperty(), ctrl.crvProperty());

        TableColumn<Veterinario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );

        TableColumn<Veterinario, String> colEspecialidade = new TableColumn<>("Especialidade");
        colEspecialidade.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEspecialidade())
        );

        table.getColumns().add(colNome);
        table.getColumns().add(colEspecialidade);

        table.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> ctrl.fromEntity(novo)
        );

        table.setItems(ctrl.getLista());

        stage.setScene(scn);

        stage.show();
    }

}
