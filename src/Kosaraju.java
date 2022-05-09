import java.util.ArrayList;
import java.util.Stack;

import static java.awt.Color.WHITE;

public class Kosaraju {
    private ArrayList<Vertice> visitados;
    private Stack<Vertice> stack;
    GrafoLista grafo;

    public Kosaraju(GrafoLista grafo) {
        this.stack = new Stack<Vertice>();
        this.visitados = new ArrayList<Vertice>();
        this.grafo = grafo;
    }

    public void dfs(GrafoLista g) {
        for (Vertice v : g.getVertices()) {
            if (v.getCor().equals(Vertice.Color.WHITE)) {
                dfsVisit(v);
            }
        }
    }

    public void dfsVisit(Vertice v) {
        v.setCor(Vertice.Color.GRAY);

        for (Vertice adj : v.getAdjacencias()) {
            if (adj.getCor().equals(Vertice.Color.WHITE)) {
                dfsVisit(adj);
            }
        }

        v.setCor(Vertice.Color.BLACK);
        stack.add(v);
    }

    public GrafoLista getTransposta() {
        GrafoLista grafoInvertido = new GrafoLista();
        grafoInvertido.setNumeroDeVertices(grafo.getNumeroDeVertices());

        Vertice novaAdj = null;

        for (Vertice v : this.grafo.getVertices()) {
            boolean jaTem = false;
            Vertice novoV = new Vertice(v.getDado());
            for (Vertice adj : v.getAdjacencias()) {

                //verificar se ja possui um vertice no grafo invertido
                for (Vertice vertI : grafoInvertido.getVertices()) {
                    if (vertI.getDado().equals(adj.getDado())) {
                        grafoInvertido.adicionarAresta(vertI, novoV);
                        jaTem = true;
                    }
                }

                if (!jaTem) {
                    novaAdj = grafoInvertido.adicionarVertice(adj.getDado());
                    System.out.println(novaAdj.getDado());
                    grafoInvertido.adicionarAresta(novaAdj, novoV);
                }
            }
        }

        return grafoInvertido;
    }

    public ArrayList<GrafoLista> getComponentesFortementeConectados() {
        dfs(this.grafo);
        GrafoLista grafoInvertido = getTransposta();
        dfs(grafoInvertido);
        ArrayList<GrafoLista> cFC = new ArrayList<>();


        return cFC;
    }

    public ArrayList<Vertice> getVisitados() {
        return visitados;
    }

    public void setVisitados() {
        this.visitados = new ArrayList<Vertice>();
    }

    public Stack<Vertice> getStack() {
        return stack;
    }
}
