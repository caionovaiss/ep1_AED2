import java.util.Scanner;

/*
Classe que pega a entrada e transforma em um grafo lista
 */
public class PegarEntrada {

    public PegarEntrada() {
    }

    public void getEntrada(int nroVertices, Scanner sc, GrafoLista grafo) {
        // pegar entrada
        for (int i = 0; i < nroVertices; i++) {
            String linha = sc.nextLine();
            System.out.println();
            int index = linha.indexOf(":");
            String[] verticesCoratados = linha.split(" ");
            String dadoV = verticesCoratados[0].substring(0, index);

            //verificacao para nao criar vertices repetidos
            Vertice v = new Vertice(dadoV);
            boolean jaTem = false;
            for (Vertice g : grafo.getVertices()) {
                if (g.getDado().equals(dadoV)) {
                    v = g;
                    jaTem = true;
                    break;
                }
            }
            if (!jaTem)
                v = grafo.criarEAdicionarVertice(dadoV);

            Vertice adj;
            for (int x = 1; x < verticesCoratados.length; x++) {
                String dado = verticesCoratados[x].substring(0, verticesCoratados[x].length() - 1);

                Vertice temp = new Vertice(dado);
                boolean jaTemAdj = false;
                for (Vertice l : grafo.getVertices()) {
                    if (l.getDado().equals(dado)) {
                        temp = l;
                        jaTemAdj = true;
                        break;
                    }
                }
                if (!jaTemAdj) {
                    adj = grafo.criarEAdicionarVertice(dado);
                    grafo.adicionarAresta(v, adj);
                } else
                    grafo.adicionarAresta(v, temp);
            }
        }
    }
}
