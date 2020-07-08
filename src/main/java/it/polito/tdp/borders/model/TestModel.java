package it.polito.tdp.borders.model;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Model m = new Model();
		SimpleGraph<Country, DefaultWeightedEdge> grafo;

		Collegamento c1=new Collegamento(new Country(1,null,null),new Country(2,null,null));
		Collegamento c2=new Collegamento(new Country(2,null,null),new Country(1,null,null));
		System.out.println(c1.equals(c2));
		grafo=new SimpleGraph(DefaultEdge.class);
		grafo.addVertex(c1.getC1());
		grafo.addVertex(c2.getC2());
		System.out.println(grafo.vertexSet());

		

	}

}
