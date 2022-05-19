import java.util.Scanner;

public class TestadorGrafo {
    public static void main(String[] args) {

        GrafoLista gLista = new GrafoLista();
        Kosaraju k = new Kosaraju(gLista);

        Scanner sc = new Scanner(System.in);
        int nroVertices = sc.nextInt();
        sc.nextLine();

        PegarEntrada gI = new PegarEntrada();
        gI.getEntrada(nroVertices, sc, gLista);
        gLista.setNumeroDeVertices(nroVertices);
        gLista.atribuirPosicao();

        //formato matriz

        int representacao = sc.nextInt();
        if (representacao == 1)
            k.executarSaida();
        else {
            GrafoLista grafoFc = k.executarSaidaMatriz();
            grafoFc.atribuirPosicao();
            GrafoMatriz grafoMatriz = new GrafoMatriz(grafoFc.getNumeroDeVertices(), grafoFc);
            grafoMatriz.imprimirMatriz();
        }
    }
}
