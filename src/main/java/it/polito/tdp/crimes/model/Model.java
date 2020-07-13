package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private Event e;
	private Graph<String, DefaultWeightedEdge> grafo;
	private EventsDao dao;
	private Map<Long, Event> crimini;
	private List<Event> listaCrimini;
	private List<String> listaVertici;
	private List<Crime> listaArchi;
	private List<Crime> archiGrafo;
	private double pesoMedio;
	private List<String> soluzione;
	
	public Model() {
		this.dao = new EventsDao();
		this.crimini = new HashMap<Long, Event>();
		dao.listAllEvents(crimini);
		this.listaCrimini = new ArrayList<>(this.crimini.values());
		this.listaVertici = new ArrayList<>();
		this.listaArchi = new ArrayList<>();
		this.archiGrafo= new ArrayList<Crime>();
		this.pesoMedio=0;
		//this.soluzione = new ArrayList<>();
	}

	public void creaGrafo(int mese, String categoria) {
		this.grafo= new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.listaVertici = dao.getVertex(categoria, mese);
		this.listaArchi = dao.getArchi(categoria, mese);
		//System.out.print(listaVertici);
		Graphs.addAllVertices(grafo, listaVertici);
		for (Crime c : listaArchi) {
			if(c.getPeso()>0)
				Graphs.addEdgeWithVertices(grafo, c.getOffense_type_1(), c.getOffense_type_2(), c.getPeso());
		}
		this.calcolaArchi();
		
	}
	
	public void calcolaPesoMedio() {
		double pesoTot=0;
		double totArchi=0;
		for(Crime c: listaArchi) {
			if(c.getPeso()>0) {
				pesoTot+=c.getPeso();
				totArchi++;
			}
		}
	   pesoMedio = pesoTot/totArchi;
	}
	
	public EventsDao getDao() {
		return dao;
	}

	public Map<Long, Event> getCrimini() {
		return crimini;
	}

	public List<Event> getListaCrimini() {
		return listaCrimini;
	}

	public Graph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public List<Crime> getArchiGrafo() {
		return archiGrafo;
	}


	public void calcolaArchi() {
		this.calcolaPesoMedio();
		for (Crime crime : listaArchi) {
			if(crime.getPeso()>=this.pesoMedio)
				this.archiGrafo.add(crime);
		}
	}
	
	public List<String> trovaPercorso(String sorgente, String destinazione) {
		int livello= 0;
		List<String> parziale = new ArrayList<>();
		this.soluzione = new ArrayList<>();
		parziale.add(sorgente);
		
		trovaRicorsivo(parziale, livello, destinazione);
		System.out.println(parziale);
		return soluzione;
	}

	private void trovaRicorsivo(List<String> parziale, int livello, String destinazione) {
		//caso terminale
		if(parziale.get(parziale.size()-1).equals(destinazione)) {
			if (parziale.size()>this.soluzione.size()) {
				this.soluzione = new ArrayList<>(parziale);
			}
			return;
		}
		
		//caso intermedio
		for (String vicino: Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(vicino)) {
				parziale.add(vicino);
				trovaRicorsivo(parziale, livello+1, destinazione);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}
}
