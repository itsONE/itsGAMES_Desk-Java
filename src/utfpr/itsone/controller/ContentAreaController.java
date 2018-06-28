package utfpr.itsone.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ContentAreaController implements Initializable {

    public Label lbName;
    public HBox contentPane;
    public VBox vNode;
    @FXML
    private VBox content_area;
    @FXML
    private HBox menubar;

    /**
     * Initializes the controller class.
     */
    boolean flag = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        execute();
    }

    public void execute(){
        FXMLLoader sidebar = null;
        Parent layout = null;
        try {
            sidebar = new FXMLLoader(getClass().getResource("/utfpr/itsone/view/Header.fxml"));
            layout = sidebar.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HeaderController hc = sidebar.getController();
        content_area.getChildren().add(layout);
        sidebar = null;
        layout = null;
        try {
            sidebar = new FXMLLoader(getClass().getResource("/utfpr/itsone/view/Content.fxml"));
            layout = sidebar.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentController cc = sidebar.getController();
        cc.init(hc);
        content_area.getChildren().add(layout);
    }
    
    @FXML
    private void open_sidebar(ActionEvent event) throws IOException {
        BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        if (flag == true) {
            Parent sidebar = FXMLLoader.load(getClass().getResource("/utfpr/itsone/view/Sidebar.fxml"));
            border_pane.setLeft(sidebar);
            flag = false;
        } else {
            border_pane.setLeft(null);
            flag = true;
        }
        
    }

    @FXML
    private void exit(ActionEvent event){
        System.exit(0);
    }
    
}
