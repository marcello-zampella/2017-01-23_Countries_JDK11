package it.polito.tdp.borders.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.model.Evento.TipoEvento;

public class Simulatore {
	int migranti;
	HashMap<Country,Integer> nazioni;
	PriorityQueue<Evento> queue;
	int tempo;
	int tempoMassimo;
	SimpleGraph<Country, DefaultWeightedEdge> grafo;


	public void init(SimpleGraph<Country, DefaultWeightedEdge> grafo, Country nazione) {
		this.grafo=grafo;
		tempoMassimo=0;
		migranti=1000;
		nazioni=new HashMap<Country,Integer>();
		//nazioni.put(nazione, migranti);
		queue=new PriorityQueue<Evento>();
		queue.clear();
		tempo=1;
		Evento e=new Evento(TipoEvento.ARRIVO_MIGRANTI,tempo,nazione,migranti);
		queue.add(e);
		
	}

	public void run() {
		
		while(!queue.isEmpty()) {
			Evento ev=queue.poll();
			//System.out.println(ev);
			switch(ev.getTipo()) {
			
			case ARRIVO_MIGRANTI:
				Country nazione=ev.getNazione();
				ArrayList<Country> vicini=new ArrayList<Country>(Graphs.neighborListOf(grafo, nazione));
				int migrantiTotali=ev.getNumeroMigranti();
				int numeroVicini=vicini.size();
				int parti=migrantiTotali/2;
				int resto=parti%numeroVicini;
				int nonStanziali=parti-resto;
				int partenze=nonStanziali/numeroVicini;
				int stanziali=parti+resto;
				if(nonStanziali>=numeroVicini) {
					if(this.nazioni.containsKey(nazione)) {
					this.nazioni.put(nazione, this.nazioni.get(nazione)+stanziali);
					} else {
						this.nazioni.put(nazione, stanziali);
					}
					for(Country nazioneTemp:vicini) {
						Evento e=new Evento(TipoEvento.ARRIVO_MIGRANTI,ev.getTempo()+1, nazioneTemp, partenze);
					queue.add(e);
				}
				} else {
					if(nazioni.containsKey(nazione)) {
					this.nazioni.put(nazione, this.nazioni.get(nazione)+migrantiTotali);
					} else {
						this.nazioni.put(nazione, migrantiTotali);
					}
				}
				if(ev.getTempo()>tempoMassimo)
					tempoMassimo=ev.getTempo();
				break;
				
			case PARTENZA_MIGRANTI:
				
				break;
			}
		}
		
		
	}

	public HashMap<Country, Integer> getNazioni() {
		return nazioni;
	}

	public int getTempoMassimo() {
		return tempoMassimo;
	}
	
	
	
	

}
