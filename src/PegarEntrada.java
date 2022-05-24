import java.util.Scanner;

/*
Classe que pega a entrada e transforma em um grafoL lista
 */
public class PegarEntrada {
    private int numeroVertices;
    private Scanner sc;
    private GrafoLista gL;
    private GrafoMatriz gM;

    public PegarEntrada(int numeroVertices, Scanner sc, GrafoLista gL, GrafoMatriz gM) {
        this.numeroVertices = numeroVertices;
        this.sc = sc;
        this.gL = gL;
        this.gM = gM;
    }

    public int getNumeroVertices() {
        return numeroVertices;
    }

    public void setNumeroVertices(int numeroVertices) {
        this.numeroVertices = numeroVertices;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public GrafoLista getgL() {
        return gL;
    }

    public void setgL(GrafoLista gL) {
        this.gL = gL;
    }

    public GrafoMatriz getgM() {
        return gM;
    }

    public void setgM(GrafoMatriz gM) {
        this.gM = gM;
    }

    public void getEntrada() {
        // pegar entrada
        for (int i = 0; i < getNumeroVertices(); i++) {
            String linha = getSc().nextLine();
            System.out.println();
            int index = linha.indexOf(":");
            String[] verticesCoratados = linha.split(" ");
            String dadoV = verticesCoratados[0].substring(0, index);

            //verificacao para nao criar vertices repetidos
            Vertice v = new Vertice(dadoV);
            v.setPosicao(i);
            boolean jaTem = false;
            for (Vertice j : getgL().getVertices()) {
                if (j.getDado().equals(dadoV)) {
                    v = j;
                    jaTem = true;
                    break;
                }
            }
            if (!jaTem) {
                v = getgL().criarEAdicionarVertice(dadoV);
            }
            Vertice adj;
            for (int x = 1; x < verticesCoratados.length; x++) {
                String dado = verticesCoratados[x].substring(0, verticesCoratados[x].length() - 1);

                Vertice temp = new Vertice(dado);
                boolean jaTemAdj = false;
                for (Vertice l : getgL().getVertices()) {
                    if (l.getDado().equals(dado)) {
                        temp = l;
                        jaTemAdj = true;
                        break;
                    }
                }
                if (!jaTemAdj) {
                    adj = getgL().criarEAdicionarVertice(dado);
                    getgL().adicionarAresta(v, adj);
                } else {
                    getgL().adicionarAresta(v, temp);
                }
            }
        }
        gListaTOgMatriz();
    }

    public void gListaTOgMatriz() {
        for (int i = 0; i < getgL().getVertices().size(); i++) {
            getgL().getVertices().get(i).setPosicao(i);
        }
        getgM().setVertices(getgL().getVertices());
        for (Vertice v : getgM().getVertices()) {
            for (Vertice adj : v.getAdjacencias()) {
                getgM().addAresta(v, adj);
            }
        }
    }
}
