package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO dao;
	private SimpleGraph<Country, DefaultWeightedEdge> grafo;

	
	public Model() {
		dao= new BordersDAO(); 
	}

	public ArrayList<Confine> creaGrafo(int anno) {
		ArrayList<Collegamento> collegamenti=dao.getConfiniAnno(anno);
		grafo=new SimpleGraph(DefaultEdge.class);
		for(Collegamento c:collegamenti) {
		grafo.addVertex(c.getC1());
		grafo.addVertex(c.getC2());
		grafo.addEdge(c.getC1(), c.getC2());
		}
		ArrayList<Confine> confini=new ArrayList<Confine>();
		for(Country c: grafo.vertexSet()) {
			confini.add(new Confine(c,Graphs.neighborSetOf(grafo, c).size()));
		}

		return confini;
				
	}
	
	private int tempoMassimo;
	private HashMap<Country,Integer> nazioni;

	public void simula(Country nazione) {
		Simulatore sim= new Simulatore();
		sim.init(grafo,nazione);
		sim.run();
		nazioni=sim.getNazioni();
		tempoMassimo=sim.getTempoMassimo();
	}

	public int getTempoMassimo() {
		return tempoMassimo;
	}

	public HashMap<Country, Integer> getNazioni() {
		return nazioni;
	}
	
	

}
