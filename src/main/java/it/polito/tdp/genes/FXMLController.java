package it.polito.tdp.genes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Model;
import it.polito.tdp.genes.model.Vicina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear();
    	String localizzazione = boxLocalizzazione.getValue();
    	if(localizzazione == null) {
    		txtResult.appendText("ERRORE: Selezionare prima una localizzazione dal menu a tendina!\n");
    	}
    	List<String> percorso = new ArrayList<>(this.model.trovaPercorso(localizzazione));
    	txtResult.appendText("TROVATO PERCORSO A PARTIRE DA " + localizzazione + ": \n");
    	for(String s: percorso) {
    		txtResult.appendText(s + "\n");
    	}
    	
    }

    @FXML
    void doStatistiche(ActionEvent event) {
    	txtResult.clear();
    	String localizzazione = boxLocalizzazione.getValue();
    	if(localizzazione == null) {
    		txtResult.appendText("ERRORE: Selezionare prima una localizzazione dal menu a tendina!\n");
    	}
    	List<Vicina> vicine = new ArrayList<>(this.model.getVicine(localizzazione));
    	txtResult.appendText("ADIACENTI A: " + localizzazione + "\n");
    	for(Vicina v: vicine) {
    		txtResult.appendText(v.getLocalization() + " " + v.getPeso() + "\n");
    	}
    
    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;

		this.model.creaGrafo();
		boxLocalizzazione.getItems().addAll(this.model.getVertici());
		
		txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("#VERTICI: "+ this.model.nVertici()+"\n");
    	txtResult.appendText("#ARCHI: "+ this.model.nArchi()+"\n");
    	
	}
}
