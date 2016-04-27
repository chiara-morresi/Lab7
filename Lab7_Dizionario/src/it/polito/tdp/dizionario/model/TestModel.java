package it.polito.tdp.dizionario.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		DizionarioModel model = new DizionarioModel();
		
		model.getParoleLun(2);
		

		List<String> lista = model.confrontaParole("ab");
		
		for(String s: lista) {
			System.out.println(s);
		}
		
		
		model.generaGrafo(2);
		
		
		
	}

}
