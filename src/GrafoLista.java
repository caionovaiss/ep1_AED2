import java.util.ArrayList;

public class GrafoLista {
    private int numeroDeVertices;
    private ArrayList<Vertice> vertices;

    public GrafoLista() {
        this.numeroDeVertices = 0;
        this.vertices = new ArrayList<>();
    }

    public Vertice criarEAdicionarVertice(String dado) {
        Vertice novoVertice = new Vertice(dado);
        this.vertices.add(novoVertice);

        return novoVertice;
    }

    public void addVertice(Vertice v) {
        this.vertices.add(v);
    }

    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setNumeroDeVertices(int numeroDeVertices) {
        this.numeroDeVertices = numeroDeVertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    /*
    Classe que adiciona aresta, ou seja, adiciona o vertice adjancente em um vertice
     */
    public void adicionarAresta(Vertice vInicial, Vertice vFinal) {
        vInicial.adicionarAdjacencia(vFinal);
    }

    public void imprimirGrafoLista() {
        for (Vertice v : this.vertices) {
            System.out.print(v.getDado() + ": ");
            for (Vertice adj : v.getAdjacencias()) {
                System.out.print(adj.getDado() + "; ");
            }
            System.out.println();
        }
    }

}
