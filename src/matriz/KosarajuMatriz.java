package matriz;

import itens.Vertice;

import java.util.ArrayList;
import java.util.Stack;

public class KosarajuMatriz {

    private final GrafoMatriz grafo;
    private final Stack<Vertice> stack;
    private final Stack<Vertice> stackCFC;

    public KosarajuMatriz(GrafoMatriz grafo) {
        this.grafo = grafo;
        this.stack = new Stack<>();
        this.stackCFC = new Stack<>();
    }

    public GrafoMatriz getGrafo() {
        return grafo;
    }

    public void dfs(Vertice v) {
        if (v.getCor().equals(Vertice.Color.WHITE))
            dfsVisit(v);
    }

    private void dfsVisit(Vertice v) {
        v.setCor(Vertice.Color.GRAY);

        for (int i = 0; i < getGrafo().getMaximoVertices(); i++) {
            if (getGrafo().getMatriz()[v.getPosicao()][i] == 1) {
                Vertice adj = getGrafo().getVertices().get(i);
                if (adj.getCor() == Vertice.Color.WHITE) {
                    dfsVisit(adj);
                }
            }
        }

        v.setCor(Vertice.Color.BLACK);
        this.stack.add(v);
    }

    public void dfsGI(Vertice v, GrafoMatriz gI) {
        for (int i = 0; i < gI.getMaximoVertices(); i++) {
            if (v.getCor().equals(Vertice.Color.WHITE))
                dfsVisitGI(v, gI);
        }
    }

    private void dfsVisitGI(Vertice v, GrafoMatriz gI) {
        v.setCor(Vertice.Color.GRAY);

        for (int i = 0; i < gI.getMaximoVertices(); i++) {
            if (gI.getMatriz()[v.getPosicao()][i] == 1) {
                Vertice adj = gI.getVertices().get(i);
                if (adj.getCor() == Vertice.Color.WHITE) {
                    dfsVisitGI(adj, gI);
                }
            }
        }

        v.setCor(Vertice.Color.BLACK);
    }

    public void dfsOT(GrafoMatriz gM) {
        for (int i = 0; i < gM.getMaximoVertices(); i++) {
            if (gM.getVertices().get(i).getCor() == Vertice.Color.WHITE)
                dfsVisitOT(gM.getVertices().get(i), gM);
        }

    }

    private void dfsVisitOT(Vertice v, GrafoMatriz gM) {
        v.setCor(Vertice.Color.GRAY);

        for (int i = 0; i < gM.getMaximoVertices(); i++) {
            if (gM.getMatriz()[v.getPosicao()][i] == 1) {
                Vertice adj = gM.getVertices().get(i);
                if (adj.getCor() == Vertice.Color.WHITE)
                    dfsVisitOT(adj, gM);
            }
        }

        v.setCor(Vertice.Color.BLACK);
        this.stackCFC.add(v);
    }

    public GrafoMatriz getTransposta() {
        GrafoMatriz gInvertido = new GrafoMatriz(getGrafo().getMaximoVertices());

        for (int l = 0; l < getGrafo().getMaximoVertices(); l++) {
            for (int c = 0; c < getGrafo().getMaximoVertices(); c++) {
                if (getGrafo().getMatriz()[l][c] == 1) {
                    gInvertido.getMatriz()[c][l] = 1;
                }
            }
        }
        gInvertido.setVertices(getGrafo().getVertices());

        for (Vertice v : gInvertido.getVertices()) {
            v.setCor(Vertice.Color.WHITE);
        }

        return gInvertido;
    }

    public GrafoMatriz getCFC(GrafoMatriz gInvertido) {
        ArrayList<Vertice> verticesPretos = new ArrayList<>();
        int pos = 0;

        while (!stack.isEmpty()) {
            Vertice p = stack.pop();
            for (Vertice v : gInvertido.getVertices()) {
                if (p.getDado().equals(v.getDado())) {
                    dfsGI(v, gInvertido);
                    StringBuilder componente = new StringBuilder();

                    for (Vertice j : gInvertido.getVertices()) {
                        if (j.getCor() == Vertice.Color.BLACK) {
                            componente.append(j.getDado());
                            j.setCor(Vertice.Color.GRAY);
                        }
                    }
                    //evitar que adicione strings vazias
                    if (!componente.toString().equals("")) {
                        Vertice x = new Vertice(componente.toString());
                        x.setPosicao(pos);
                        verticesPretos.add(x);
                        pos++;
                    }
                    break;
                }
            }
        }

        GrafoMatriz gM = new GrafoMatriz(verticesPretos.size());
        gM.setVertices(verticesPretos);
        return gM;

    }

    public void addAdjacenciasGrafoFc(GrafoMatriz g) {
        for (int l = 0; l < g.getMaximoVertices(); l++) {
            Vertice comp = g.getVertices().get(l);
            for (int y = 0; y < getGrafo().getMaximoVertices(); y++) {
                Vertice v = getGrafo().getVertices().get(y);
                if (comp.getDado().contains(v.getDado())) {
                    for (Integer i : getGrafo().pegarAdjacencias(v)) {
                        Vertice adj = getGrafo().getVertices().get(i);
                        if (!comp.getDado().contains(adj.getDado())) {
                            for (Vertice c : g.getVertices()) {
                                if (c.getDado().contains(adj.getDado())) {
                                    g.getMatriz()[comp.getPosicao()][c.getPosicao()] = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void ehGrafoFortementeConexo(GrafoMatriz cFC) {
        if (cFC.getMaximoVertices() != 1) {
            System.out.println("Não");
            System.out.println(cFC.getMaximoVertices());
        } else {
            System.out.println("Sim");
            System.out.println(cFC.getMaximoVertices());
        }
    }

    public void printarOrdemTopologica() {
        while (!this.stackCFC.isEmpty())
            System.out.print(this.stackCFC.pop().getDado() + " ");
    }

    public void executarSaida() {
        for (Vertice v : getGrafo().getVertices()) {
            dfs(v);
        }
        GrafoMatriz gI = getTransposta();

        GrafoMatriz matrizFc = getCFC(gI);
        addAdjacenciasGrafoFc(matrizFc);
        ehGrafoFortementeConexo(matrizFc);

        dfsOT(matrizFc);
        printarOrdemTopologica();
        System.out.println();
        System.out.println();
        matrizFc.imprimirGMatriz();

    }


}

