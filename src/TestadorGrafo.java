import java.util.ArrayList;

public class TestadorGrafo {
    public static void main(String[] args) {

        GrafoLista grafo = new GrafoLista();
        grafo.setNumeroDeVertices(4);

        Vertice vA = grafo.adicionarVertice("a");
        Vertice vB = grafo.adicionarVertice("b");
        Vertice vC = grafo.adicionarVertice("c");
        Vertice vD = grafo.adicionarVertice("d");
        Vertice vE = grafo.adicionarVertice("e");
        Vertice vF = grafo.adicionarVertice("f");
        Vertice vG = grafo.adicionarVertice("g");
        Vertice vH = grafo.adicionarVertice("h");

        grafo.adicionarAresta(vA, vC);
        grafo.adicionarAresta(vC, vE);
        grafo.adicionarAresta(vC, vB);
        grafo.adicionarAresta(vC, vD);
        grafo.adicionarAresta(vE, vG);
        grafo.adicionarAresta(vE, vF);
        grafo.adicionarAresta(vG, vE);
        grafo.adicionarAresta(vG, vH);
        grafo.adicionarAresta(vH, vG);
        grafo.adicionarAresta(vH, vF);
        grafo.adicionarAresta(vF, vE);
        grafo.adicionarAresta(vF, vD);
        grafo.adicionarAresta(vB, vA);
        grafo.adicionarAresta(vB, vD);


        Kosaraju k = new Kosaraju(grafo);

        for (Vertice i : k.getStack()) {
            System.out.println(i.getDado());
        }

        // ArrayList<GrafoLista> cFC = k.getComponentesFortementeConectados();
        // for (GrafoLista g : cFC) {
        //     g.imprimirGrafoLista();
        //     System.out.println("aqui");
        // }
    }

}
