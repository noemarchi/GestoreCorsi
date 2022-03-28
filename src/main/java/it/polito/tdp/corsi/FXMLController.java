/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader
    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader
    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader
    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader
    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader
    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader
    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) 
    {
    	// pulizia interfaccia grafica
		this.txtRisultato.clear();
    	
    	// acquisizione dati
    	String periodo = this.txtPeriodo.getText();
    	int periodoNumerico;
    	
    	// controllo dati
    	try
    	{
    		periodoNumerico = Integer.parseInt(periodo);
    	}
    	catch(NumberFormatException e)
    	{
    		this.txtRisultato.setText("Inserisci un periodo numerico!");
    		return;
    	}
    	
    	if(periodoNumerico < 1 || periodoNumerico > 2)
    	{
    		this.txtRisultato.setText("Inserisci come periodo 1 o 2!");
    		return;
    	}
    	
    	// esecuzione dell'operazione (chiedo al model)
    	List<Corso> corsi = this.model.getCorsiByPeriodo(periodoNumerico);
    	
    	// visualizzazione del risultato
    	this.txtRisultato.setText("Corsi del periodo didattico " + periodoNumerico + ":\n");
    	for(Corso c: corsi)
    	{
    		this.txtRisultato.appendText(c + "\n");
    	}
    	
    	this.txtPeriodo.clear();
    }

    @FXML
    void numeroStudenti(ActionEvent event) 
    {
    	// pulizia interfaccia grafica
		this.txtRisultato.clear();
    	
    	// acquisizione dati
    	String periodo = this.txtPeriodo.getText();
    	int periodoNumerico;
    	
    	// controllo dati
    	try
    	{
    		periodoNumerico = Integer.parseInt(periodo);
    	}
    	catch(NumberFormatException e)
    	{
    		this.txtRisultato.setText("Inserisci un periodo numerico!");
    		return;
    	}
    	
    	if(periodoNumerico < 1 || periodoNumerico > 2)
    	{
    		this.txtRisultato.setText("Inserisci come periodo 1 o 2!");
    		return;
    	}
    	
    	// esecuzione dell'operazione (chiedo al model)
    	Map<Corso, Integer> iscritti = this.model.getIscritti(periodoNumerico);
    	
    	// visualizzazione del risultato
    	this.txtRisultato.setText("Iscritti ai corsi del periodo didattico " + periodoNumerico + ":\n");
    	for(Corso c: iscritti.keySet())
    	{
    		this.txtRisultato.appendText(iscritti.get(c) + " @ " +  c + "\n");
    	}
    	
    	this.txtPeriodo.clear();
    }

    @FXML
    void stampaDivisione(ActionEvent event) 
    {
    	// pulizia interfaccia grafica
		this.txtRisultato.clear();
    	
    	// acquisizione dati
    	String codins = this.txtCorso.getText();
    	
    	// controllo dati
    	if(codins == null || codins.equals(""))
    	{
    		this.txtRisultato.setText("Inserisci il codice di un corso!");
    		return;
    	}
    	// TODO controllo che il corso esista
    	
    	// esecuzione dell'operazione (chiedo al model)
    	List<Divisione> div = this.model.getDivisioneStudenti(codins);
    	Collections.sort(div);
    	
    	// visualizzazione del risultato
    	this.txtRisultato.setText("Suddivisione studenti per corso di studi:\n");
    	for(Divisione d: div)
    	{
    		this.txtRisultato.appendText(d + "\n");
    	}
    	
    	this.txtCorso.clear();
    }

    @FXML
    void stampaStudenti(ActionEvent event) 
    {
    	// pulizia interfaccia grafica
		this.txtRisultato.clear();
    	
    	// acquisizione dati
    	String codins = this.txtCorso.getText();
    	
    	// controllo dati
    	if(codins == null || codins.equals(""))
    	{
    		this.txtRisultato.setText("Inserisci il codice di un corso!");
    		return;
    	}
    	// TODO controllo che il corso esista
    	
    	// esecuzione dell'operazione (chiedo al model)
    	List<Studente> studenti = this.model.getStudentiByCorso(codins);
    	
    	// visualizzazione del risultato
    	this.txtRisultato.setText("Studenti iscritti al corso " + codins + ":\n");
    	for(Studente s: studenti)
    	{
    		this.txtRisultato.appendText(s + "\n");
    	}
    	
    	this.txtCorso.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) 
    {
    	this.model = model;
    }
    
    
}