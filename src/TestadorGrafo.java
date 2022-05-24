import java.util.Scanner;

public class TestadorGrafo {
    public static void main(String[] args) {

        GrafoLista gLista = new GrafoLista();
        Kosaraju k = new Kosaraju(gLista);

        //pegar entrada
        Scanner sc = new Scanner(System.in);
        int nroVertices = sc.nextInt();
        sc.nextLine();
        GrafoMatriz gM = new GrafoMatriz(nroVertices);
        PegarEntrada gI = new PegarEntrada(nroVertices, sc, gLista, gM);
        gI.getEntrada();

        int representacao = sc.nextInt();

        if (representacao == 1) {
            k.executarSaida();
        } else {
            KosarajuMatriz kM = new KosarajuMatriz(gM);
            kM.executarSaida();
        }
    }
}
