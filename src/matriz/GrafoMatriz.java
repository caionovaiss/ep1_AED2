package matriz;

import itens.Vertice;

import java.util.ArrayList;

public class GrafoMatriz {
    private final int maximoVertices;
    private int matriz[][];
    private ArrayList<Vertice> vertices;

    public GrafoMatriz(int nroVertices) {
        this.maximoVertices = nroVertices;
        this.vertices = new ArrayList<>();

        matriz = new int[maximoVertices][maximoVertices];

        for (int i = 0; i < getMaximoVertices(); i++) {
            for (int j = 0; j < getMaximoVertices(); j++) {
                matriz[i][j] = 0;
            }
        }
    }

    //getters and setters
    public int getMaximoVertices() {
        return maximoVertices;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Integer> pegarAdjacencias(Vertice v) {
        ArrayList<Integer> adjs = new ArrayList<>();

        for (Vertice x : getVertices()) {
            if (x == v) {
                for (int i = 0; i < getMaximoVertices(); i++) {
                    if (getMatriz()[x.getPosicao()][i] == 1) {
                        adjs.add(i);
                    }
                }
                break;
            }
        }
        return adjs;
    }

    public void addAresta(Vertice ini, Vertice fim) {
        for (int l = 0; l < getMaximoVertices(); l++) {
            if (ini.getPosicao() == l) {
                for (int c = 0; c < getMaximoVertices(); c++) {
                    if (fim.getPosicao() == c) {
                        this.matriz[l][c] = 1;
                        break;
                    }
                }
                break;
            }
        }
    }

    public void imprimirGMatriz() {
        System.out.print("  ");
        for (Vertice v : getVertices()) {
            System.out.print(" ");
            System.out.print(v.getDado());
            System.out.print(" ");
        }
        System.out.println();
        for (int l = 0; l < getMaximoVertices(); l++) {
            System.out.print(this.vertices.get(l).getDado() + " ");

            for (int c = 0; c < getMaximoVertices(); c++) {
                System.out.print(" " + this.matriz[l][c] + " ");
            }
            System.out.println();
        }
    }


}
