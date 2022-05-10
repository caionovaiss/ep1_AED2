import java.util.ArrayList;
import java.util.Scanner;

public class TestadorGrafo {
    public static void main(String[] args) {

        GrafoLista grafo = new GrafoLista();
        grafo.setNumeroDeVertices(4);
        Kosaraju k = new Kosaraju(grafo);

        Scanner sc = new Scanner(System.in);
        int nroVertices = sc.nextInt();

        for (int i = 0; i < nroVertices; i++) {
            String linha = sc.nextLine();
            String[] vertices = linha.split(":");
            for (String item : vertices) {
                System.out.println(item);
            }
        }

//        Vertice vA = grafo.criarVertice("a");
//        Vertice vB = grafo.criarVertice("b");
//        Vertice vC = grafo.criarVertice("c");
//        Vertice vD = grafo.criarVertice("d");
//        Vertice vE = grafo.criarVertice("e");
//        Vertice vF = grafo.criarVertice("f");
//        Vertice vG = grafo.criarVertice("g");
//        Vertice vH = grafo.criarVertice("h");
//
//        grafo.adicionarAresta(vA, vC);
//        grafo.adicionarAresta(vC, vE);
//        grafo.adicionarAresta(vC, vB);
//        grafo.adicionarAresta(vC, vD);
//        grafo.adicionarAresta(vD, vF);
//        grafo.adicionarAresta(vE, vG);
//        grafo.adicionarAresta(vE, vF);
//        grafo.adicionarAresta(vG, vE);
//        grafo.adicionarAresta(vG, vH);
//        grafo.adicionarAresta(vH, vG);
//        grafo.adicionarAresta(vH, vF);
//        grafo.adicionarAresta(vF, vD);
//        grafo.adicionarAresta(vB, vA);
//        grafo.adicionarAresta(vB, vD);

        ArrayList<GrafoLista> cFC = k.getComponentesFortementeConectados();
        for (GrafoLista g : cFC) {
            g.imprimirGrafoLista();
            System.out.println("aqui");
        }

    }
}
