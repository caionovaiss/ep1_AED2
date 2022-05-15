import java.util.ArrayList;

public class Vertice {
    private String dado;
    private ArrayList<Vertice> adjacencias;


    public enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private Color cor;

    public Vertice(String dado) {
        this.dado = dado;
        this.adjacencias = new ArrayList<Vertice>();
        this.cor = Color.WHITE;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return this.cor;
    }

    public void adicionarAdjacencia(Vertice v) {
        this.adjacencias.add(v);
    }

    public void removerAdjacencia(Vertice adj) {
        if (this.adjacencias.contains(adj)) {
            this.adjacencias.remove(adj);
            System.out.println("adjacencia removida com sucesso");
        } else
            System.out.println("essa adjacencia nao existe");
    }

    public int getNumeroAdjacencias() {
        return adjacencias.size();
    }


    public ArrayList<Vertice> getAdjacencias() {
        return this.adjacencias;
    }

}
