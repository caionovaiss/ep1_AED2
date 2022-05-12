import java.util.ArrayList;
import java.util.Scanner;

public class TestadorGrafo {
    public static void main(String[] args) {

        GrafoLista grafo = new GrafoLista();
        Kosaraju k = new Kosaraju(grafo);

        Scanner sc = new Scanner(System.in);
        int nroVertices = sc.nextInt();
        sc.nextLine();

        PegarEntrada gI = new PegarEntrada();

        gI.getEntrada(nroVertices, sc, grafo);

        grafo.setNumeroDeVertices(nroVertices);
        //grafo.imprimirGrafoLista();

        ArrayList<GrafoLista> cFC = k.getComponentesFortementeConectados();
//        for (GrafoLista g : cFC) {
//            System.out.println("---");
//            g.imprimirGrafoLista();
//        }

        if (cFC.size() > 1) {
            System.out.println("Nao");
            System.out.println(cFC.size());
        } else {
            System.out.println("Sim");
            System.out.println(cFC.size());
        }
    }
}
        /*
        if (grafo.getVertices().get(0).getAdjacencias().get(1).equals(grafo.getVertices().get(2))) {
            System.out.println("sao iguais");
            System.out.println(grafo.getVertices().get(0).getAdjacencias().get(1).getDado());
            System.out.println(grafo.getVertices().get(2).getDado());
            System.out.println("iguais ----");
        } else {
            System.out.println("nao sao iguais");
            System.out.println(grafo.getVertices().get(0).getAdjacencias().get(1).getDado());
            System.out.println(grafo.getVertices().get(2).getDado());
            System.out.println("diferentes ----");
        }

         */
