package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Graph<String, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	
	private List<String> best;
	private int pesoMax;
	
	public Model() {
		super();
		this.dao = new GenesDao();
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//Aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici());
		
		//Aggiungo gli archi
		for(Adiacenza a: this.dao.getAdiacenze()) {
			if(this.grafo.containsVertex(a.getLocalization1()) && this.grafo.containsVertex(a.getLocalization2())) {
				Graphs.addEdgeWithVertices(this.grafo, a.getLocalization1(), a.getLocalization2(), a.getPeso());
			}
		}
		
		 System.out.println("Grafo creato!");
		 System.out.println("#VERTICI: "+ this.grafo.vertexSet().size());
		 System.out.println("#ARCHI: "+ this.grafo.edgeSet().size());
	}
	

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public boolean grafoCreato() {
		if(this.grafo == null)
			return false;
		else
			return true;
	}
	
	public List<String> getVertici(){
		List<String> vertici = new ArrayList<>(this.grafo.vertexSet());
		return vertici;
	}

	public List<Vicina> getVicine(String localizzazione) {
		List<Vicina> vicine = new ArrayList<>();
        for(String s: Graphs.neighborListOf(this.grafo, localizzazione)) {
        	Vicina v = new Vicina(s, (int) this.grafo.getEdgeWeight(this.grafo.getEdge(s, localizzazione)));
            vicine.add(v);
        }
		
		return vicine;
	}
	
	public List<String> trovaPercorso(String localizzazione){
		this.best = new ArrayList<>();
		
		this.pesoMax = 0;
		
		List<String> parziale = new ArrayList<>();
		
		parziale.add(localizzazione);
		
		cerca(parziale, 0);
		
		return best;
	}

	private void cerca(List<String> parziale, int peso) {
		if(peso > pesoMax) {
			this.best = new ArrayList<>(parziale);
			pesoMax = peso;
		}
		
		String ultimo = parziale.get(parziale.size()-1);
		for(String s: Graphs.neighborListOf(this.grafo, ultimo)) {
			  DefaultWeightedEdge e = this.grafo.getEdge(ultimo, s);
			   if(e != null && !parziale.contains(s)) {
				parziale.add(s);
				peso += this.grafo.getEdgeWeight(this.grafo.getEdge(ultimo, s));
				cerca(parziale, peso);
				parziale.remove(parziale.size()-1);
				peso -= this.grafo.getEdgeWeight(this.grafo.getEdge(ultimo, s));
			}
		}
	}

}