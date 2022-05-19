import java.util.ArrayList;

public class GrafoMatriz {
    private final int maximoVertices;
    private int matriz[][];
    private GrafoLista gLista;

    public GrafoMatriz(int nroVertices, GrafoLista gLista) {
        this.gLista = gLista;
        this.maximoVertices = nroVertices;

        matriz = new int[maximoVertices][maximoVertices];

        for (int i = 0; i < getMaximoVertices(); i++) {
            for (int j = 0; j < getMaximoVertices(); j++) {
                matriz[i][j] = 0;
            }
        }
    }

    public int getMaximoVertices() {
        return this.maximoVertices;
    }

    public GrafoLista getgLista() {
        return this.gLista;
    }

    public void adicionarAdjacencias() {
        for (int v = 0; v < getMaximoVertices(); v++) {
            Vertice temp = getgLista().getVertices().get(v);
            for (int u = 0; u < getMaximoVertices(); u++) {
                for (Vertice adj : temp.getAdjacencias()) {
                    this.matriz[v][adj.getPosicao()] = 1;
                }
            }
        }
    }

    public void imprimirMatriz() {
        adicionarAdjacencias();
        System.out.print("    ");
        for (int i = 0; i < getMaximoVertices(); i++) {
            System.out.printf("  " + getgLista().getVertices().get(i).getDado());
        }
        System.out.println();
        for (int i = 0; i < getMaximoVertices(); i++) {
            System.out.printf(getgLista().getVertices().get(i).getDado());
            for (int j = 0; j < getMaximoVertices(); j++) {
                System.out.printf(" %3d", matriz[i][j]);
            }
            System.out.println();
        }
    }

}
