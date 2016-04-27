package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;

import it.polito.tdp.dizionario.model.DizionarioModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	private DizionarioModel model;

	public void setModel(DizionarioModel model) {
		this.model = model;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtNumLettere;

	@FXML
	private TextField txtParola;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button trovaVicini;

	@FXML
	private Button trovaConnessi;

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		
		model.getParole().clear();
	

		int lun = 0;

		String l = txtNumLettere.getText();

		if (l.length() == 0) {
			txtResult.setText("Inserire lunghezza lettera!");
			return;
		}

		try {
			lun = Integer.parseInt(txtNumLettere.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Formato numero lettere errato");
			return;
		}

		model.generaGrafo(lun);
		txtResult.setText("Grafo generato correttamente.");

		trovaVicini.setDisable(false);
		trovaConnessi.setDisable(false);

	}

	@FXML
	void doReset(ActionEvent event) {
		txtNumLettere.setText("");
		txtParola.setText("");
		txtResult.setText("");
		model.getParole().clear();

		trovaVicini.setDisable(true);
		trovaConnessi.setDisable(true);

	}

	@FXML
	void doTrovaConnessi(ActionEvent event) {
		int lun = 0;

		String l = txtNumLettere.getText();

		if (l.length() == 0) {
			txtResult.setText("Inserire lunghezza lettera!");
			return;
		}

		try {
			lun = Integer.parseInt(txtNumLettere.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Formato numero lettere errato");
			return;
		}

		String parola = txtParola.getText();

		if (parola.length() == 0) {
			txtResult.setText("Inserire parola!");
			return;
		}

		if (parola.length() != lun) {
			txtResult.setText("Lunghezza parola inserita diversa da numero lettere.");
			return;

		}
		
		if(!model.getParole().contains(parola)) {
			txtResult.setText("La parola non esiste nel dizionario.");
			return;
			
		}
		
		model.ricorsione(parola);
		model.getConnessi().remove(parola);
		List<String> connessi = new ArrayList<String>(model.getConnessi());
		
		
		
		
		txtResult.clear();
		
		for(String s: connessi) {
			
			txtResult.appendText(s+"\n");
		}
		

	}

	@FXML
	void doTrovaVicini(ActionEvent event) {

		int lun = 0;

		String l = txtNumLettere.getText();

		if (l.length() == 0) {
			txtResult.setText("Inserire lunghezza lettera!");
			return;
		}

		try {
			lun = Integer.parseInt(txtNumLettere.getText());
		} catch (NumberFormatException e) {
			txtResult.setText("Formato numero lettere errato");
			return;
		}

		String parola = txtParola.getText();

		if (parola.length() == 0) {
			txtResult.setText("Inserire parola!");
			return;
		}

		if (parola.length() != lun) {
			txtResult.setText("Lunghezza parola inserita diversa da numero lettere.");
			return;

		}
		
		if(!model.getParole().contains(parola)) {
			txtResult.setText("La parola non esiste nel dizionario.");
			return;
			
		}
		
		
		
		List<String> vicini = new ArrayList<String>();
		
		//vicini = model.confrontaParole(parola);
		vicini = Graphs.neighborListOf(model.getGrafo(), parola);
		
		txtResult.clear();
		
		for(String s: vicini) {
			
			txtResult.appendText(s+"\n");
		}
		
		
		

	}

	@FXML
	void initialize() {
		assert txtNumLettere != null : "fx:id=\"txtNumLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert trovaVicini != null : "fx:id=\"trovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert trovaConnessi != null : "fx:id=\"trovaConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";

		model = new DizionarioModel();
	}
}
