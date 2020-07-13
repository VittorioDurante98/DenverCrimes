/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Crime;
import it.polito.tdp.crimes.model.Event;
import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCategoria"
    private ComboBox<String> boxCategoria; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="boxArco"
    private ComboBox<Crime> boxArco; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	List<String> percorso;
    	percorso = new ArrayList<>(model.trovaPercorso(boxArco.getValue().getOffense_type_1(), boxArco.getValue().getOffense_type_2()));
    	txtResult.appendText("\n"+percorso);
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	model.creaGrafo(boxMese.getValue(), boxCategoria.getValue());
    	boxArco.getItems().addAll(model.getArchiGrafo());
		txtResult.appendText(model.getArchiGrafo().toString());
		
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	for(Event e: model.getCrimini().values()) {
    		if(!boxCategoria.getItems().contains(e.getOffense_category_id()))
    			this.boxCategoria.getItems().add(e.getOffense_category_id());
    		if(!boxMese.getItems().contains(e.getReported_date().getMonthValue()))
    			this.boxMese.getItems().add(e.getReported_date().getMonthValue());
    	}
    	Collections.sort(this.boxCategoria.getItems());
    	Collections.sort(this.boxMese.getItems());
    	txtResult.setEditable(false);
    }
}
