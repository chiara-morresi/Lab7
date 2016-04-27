package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.DizionarioDAO;

public class DizionarioModel {

	private List<String> parole;
	private SimpleGraph<String, DefaultEdge> grafo;
	private List<String> connessi;

	public DizionarioModel() {
		grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		parole = new ArrayList<String>();
		connessi = new ArrayList<String>();

	}

	public void generaGrafo(int lun) {
		
		this.getParoleLun(lun);
		
		for (String s : parole) {
			grafo.addVertex(s);
		}
		
		for(String s: parole) {
			for(String s2: this.confrontaParole(s)) {
				grafo.addEdge(s, s2);
				//System.out.println(s + " - " +s2);
			}
		}

	}

	public void getParoleLun(int lun) {
		DizionarioDAO dao = new DizionarioDAO();
		parole.addAll(dao.getParole(lun));

	}

	public List<String> getParole() {
		return parole;
	}

	public List<String> confrontaParole(String s) {
		List<String> paroleSimili = new ArrayList<String>();
		List<String> lista = new ArrayList<String>(parole);

		int flag = 0;
		char[] array = s.toCharArray();
		for (String s2 : lista) {
			flag = 0;
			char[] array2 = s2.toCharArray();
			for (int i = 0; i < array.length; i++) {

				if (array[i] != array2[i]) {
					flag++;
				}

			}

			if (flag == 1) {
				paroleSimili.add(s2);

			}

		}

		return paroleSimili;
	}

	public SimpleGraph<String, DefaultEdge> getGrafo() {
		return grafo;
	}
	
	
	public void ricorsione(String parola) {
		if(connessi.contains(parola))
			return;
		
		connessi.add(parola);
		for(String s: Graphs.neighborListOf(grafo, parola))
			ricorsione(s);
		
		
		
		
	}

	public List<String> getConnessi() {
		return connessi;
	}
	
	
	

}
