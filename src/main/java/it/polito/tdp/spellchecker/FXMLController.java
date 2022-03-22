/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary model;
	
    public void setModel(Dictionary model) {
		this.model = model;
	}
	
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader
    
    @FXML // fx:id="labelTempo"
    private Label labelTempo; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLanguage"
    private ComboBox<String> cmbLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="labelErrori"
    private Label labelErrori; // Value injected by FXMLLoader

    @FXML // fx:id="txtAreaInserimento"
    private TextArea txtAreaInserimento; // Value injected by FXMLLoader

    @FXML // fx:id="txtAreaWrongWords"
    private TextArea txtAreaWrongWords; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtAreaWrongWords.clear();
    	txtAreaInserimento.clear();
    }

    long start,end;
    @FXML
    void doSpellCheck(ActionEvent event) {
    	start = System.nanoTime();
    	String stringatxtArea = txtAreaInserimento.getText();
    	stringatxtArea = stringatxtArea.toLowerCase();
    	stringatxtArea = stringatxtArea.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	
    	List<String> listaInserite = new ArrayList<>();
    	List<RichWord> listaOutputSbagliate = new ArrayList<>();
    	
    	String[] vett = stringatxtArea.split(" ");
    	for (String ss : vett) {
    		listaInserite.add(ss);
    	}
    	
    	Dictionary dizionario = new Dictionary();
    	String stringaLingua = cmbLanguage.getValue();
    	dizionario.loadDictionary(stringaLingua);
    	
    	listaOutputSbagliate.addAll(dizionario.spellCheckText(listaInserite));
    	
    	end = System.nanoTime();
    	labelTempo.setText("Spell check completed in "+(end-start)+" nanoseconds.");
    	
    	for (RichWord ss: listaOutputSbagliate) {
    		txtAreaWrongWords.appendText(ss.getParola() + "\n");
    	}
    	labelErrori.setText("The text contains "+listaOutputSbagliate.size()+" errors.");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaInserimento != null : "fx:id=\"txtAreaInserimento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaWrongWords != null : "fx:id=\"txtAreaWrongWords\" was not injected: check your FXML file 'Scene.fxml'.";


        cmbLanguage.getItems().clear();
        cmbLanguage.getItems().add("English");
        cmbLanguage.getItems().add("Italian");
        
        
    }

}
