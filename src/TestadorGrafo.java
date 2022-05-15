import java.util.ArrayList;
import java.util.Scanner;

public class TestadorGrafo {
    public static void main(String[] args) {

        // PEGANDO EM FORMATO DE LISTA
        GrafoLista grafo = new GrafoLista();
        Kosaraju k = new Kosaraju(grafo);

        Scanner sc = new Scanner(System.in);
        int nroVertices = sc.nextInt();
        sc.nextLine();

        PegarEntrada gI = new PegarEntrada();
        gI.getEntrada(nroVertices, sc, grafo);
        grafo.setNumeroDeVertices(nroVertices);
        //grafo.imprimirGrafoLista();

        int representacao = sc.nextInt();
        if (representacao == 1) {
            GrafoLista cFC = k.getComponentesFortementeConectados();
            cFC.imprimirGrafoLista();
        } else {

        }

    }
}
