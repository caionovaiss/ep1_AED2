package itens;

import java.util.ArrayList;

/*
Classe vertice que tem o conteudo dentro dele - dado, e suas adjacencias - outros vertices
 */
public class Vertice {
    private final String dado;
    private final ArrayList<Vertice> adjacencias;
    private int posicao;

    public enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private Color cor;

    public Vertice(String dado) {
        this.dado = dado;
        this.adjacencias = new ArrayList<>();
        this.cor = Color.WHITE;
    }

    public String getDado() {
        return dado;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return this.cor;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao() {
        return this.posicao;
    }

    public ArrayList<Vertice> getAdjacencias() {
        return this.adjacencias;
    }

    public void adicionarAdjacencia(Vertice v) {
        this.adjacencias.add(v);
    }

}
