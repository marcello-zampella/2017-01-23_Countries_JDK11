package it.polito.tdp.borders;

import java.awt.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Confine;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAnno;

    @FXML
    private ComboBox<Country> boxNazione;

    @FXML
    private TextArea txtResult;
    

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	int anno=0;
    	if(!isNumeric(this.txtAnno.getText())) {
    		this.txtResult.setText("DEVI INSERIRE UN NUMERO INTERO");
    		return;
    	}
    	this.txtResult.appendText("caclolo il grafo... \n");
    	anno=Integer.parseInt(this.txtAnno.getText());
    	ArrayList<Confine> confini=model.creaGrafo(anno);
    	Collections.sort(confini, new ComparatorePerConfine());
    	this.boxNazione.getItems().clear();
    	for(Confine c:confini) {
    		this.boxNazione.getItems().add(c.getC());
    		this.txtResult.appendText(c+"\n");
    	}

    }
    
    public static boolean isNumeric(String str) { 
  	  try {  
  	    Integer.parseInt(str);  
  	    return true;
  	  } catch(NumberFormatException e){  
  	    return false;  
  	  }  
  	}
    
    

    @FXML
    void doSimula(ActionEvent event) {
    	Country nazione=this.boxNazione.getValue();
    	if(nazione==null) {
    		this.txtResult.appendText("SCEGLI UNA NAZIONE");
    		return;
    	}
    	model.simula(nazione);
    	HashMap<Country,Integer>nazioni=model.getNazioni();
    	ArrayList<Integer> numeri=new ArrayList<Integer>(nazioni.values());
    	Collections.sort(numeri, new ComparatoreNumeri());
    	for(Integer num:numeri) {
    	for(Country c:nazioni.keySet()) {
    		
    			if(nazioni.get(c)==num) {
    				this.txtResult.appendText(c.getStateName()+" "+num+"\n");
    			}
    		}
    	}
    	

    }
    
    
    

	@FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		//this.btnSimula.setEnabled(false);
	}
}
