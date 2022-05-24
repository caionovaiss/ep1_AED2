import java.util.ArrayList;
import java.util.Stack;

public class Kosaraju {
    private ArrayList<Vertice> vertices;
    private final Stack<Vertice> stack;
    private final Stack<Vertice> stackCFC;
    private final GrafoLista grafo;
    private ArrayList<GrafoLista> fortementeConectados;

    public Kosaraju(GrafoLista grafo) {
        this.stack = new Stack<>();
        this.grafo = grafo;
        this.vertices = grafo.getVertices();
        this.fortementeConectados = new ArrayList<>();
        this.stackCFC = new Stack<>();
    }

    /* Primeiro Dfs utilizado */
    public void dfs(Vertice v) {
        if (v.getCor().equals(Vertice.Color.WHITE))
            dfsVisit(v);
    }

    /* funcao que o Dfs utiliza. Serve para construir uma pilha  */
    public void dfsVisit(Vertice v) {
        v.setCor(Vertice.Color.GRAY);

        for (Vertice adj : v.getAdjacencias()) {
            if (adj.getCor().equals(Vertice.Color.WHITE)) {
                dfsVisit(adj);
            }
        }

        v.setCor(Vertice.Color.BLACK);
        Vertice novoV = new Vertice(v.getDado());
        novoV.setCor(v.getCor());
        stack.add(novoV);
    }

    /* dfs utilizado para o grafo invertido, com as arestas trocadas */
    public void dfsGrafoInvertido(Vertice v) {
        if (v.getCor().equals(Vertice.Color.WHITE)) {
            dfsVisitGrafoInvertido(v);
        }
    }

    /* dfs de visita utilizado pelo dfs que eh aplicado no grafo invertido */
    public void dfsVisitGrafoInvertido(Vertice v) {
        v = getVertice(v.getDado());
        v.setCor(Vertice.Color.GRAY);

        for (Vertice adj : v.getAdjacencias()) {
            //usar o vertice do array vertices da classe
            for (Vertice j : this.vertices) {
                if (adj.getDado().equals(j.getDado())) {
                    if (j.getCor().equals(Vertice.Color.WHITE)) {
                        dfsVisitGrafoInvertido(j);
                    }
                }
            }
        }

        v = getVertice(v.getDado());
        v.setCor(Vertice.Color.BLACK);
    }

    /* Dfs utilizado no grafo de componentes fortemente conexos. Uzado para obter a ordem topologica */
    public void dfsOrdemTopologica(GrafoLista g) {
        for (Vertice v : g.getVertices()) {
            if (v.getCor().equals(Vertice.Color.WHITE)) {
                dfsVisitOrdemTopologica(v);
            }
        }
    }

    /* visita do dfs de ordem topologica. Usado para construir uma pilha */
    public void dfsVisitOrdemTopologica(Vertice v) {
        v.setCor(Vertice.Color.GRAY);

        for (Vertice adj : v.getAdjacencias()) {
            if (adj.getCor().equals(Vertice.Color.WHITE)) {
                dfsVisitOrdemTopologica(adj);
            }
        }

        v.setCor(Vertice.Color.BLACK);
        this.stackCFC.add(v);
    }

    /* funcao que pega o vertice a partir do dado, usada para evitar duplicação na hora de construir um vertice */
    public Vertice getVertice(String dado) {
        for (Vertice v : this.vertices) {
            if (v.getDado().equals(dado))
                return v;
        }

        System.out.println("retornou null");
        return null;
    }

    /* funcao utilizada para inverter as arestas do grafo */
    public GrafoLista getTransposta(GrafoLista g) {
        GrafoLista gInvertido = new GrafoLista();
        gInvertido.setNumeroDeVertices(g.getNumeroDeVertices());

        // loop que passa por todos os vertices do grafo original
        for (Vertice v : g.getVertices()) {
            boolean jaTem = false;

            Vertice novoV = getVertice(v.getDado());

            /* VERIFICACAO PARA OS VERTICES DO GRAFO ORIGINAL */
            //verificacao pra nao criar vertices sem necessidade
            if (gInvertido.getVertices().isEmpty()) {
                novoV = gInvertido.criarEAdicionarVertice(v.getDado());
            } else {
                boolean achou = false;
                //verificar se ja existe o vertice no grafo invertido
                for (Vertice i : gInvertido.getVertices()) {
                    if (v.getDado().equals(i.getDado())) {
                        novoV = i;
                        achou = true;
                        break;
                    }
                }
                if (!achou)
                    novoV = gInvertido.criarEAdicionarVertice(v.getDado());
            }

            /* VERIFICACAO PARA AS ADJACENCIAS DO VERTICE DO GRAFO ORIGINAL*/
            // loop que percorre todas as adjacencias do vertice em questao
            for (Vertice adj : v.getAdjacencias()) {
                //verificar se ja possui um vertice no grafo invertido
                for (Vertice vertI : gInvertido.getVertices()) {
                    if (vertI.getDado().equals(adj.getDado())) {
                        gInvertido.adicionarAresta(vertI, novoV);
                        jaTem = true;
                        break;
                    }
                }

                //se nao tiver vertice no grafo invertido
                if (!jaTem) {
                    Vertice novaAdj = new Vertice(adj.getDado());
                    boolean achou = false;
                    if (gInvertido.getVertices().isEmpty())
                        novaAdj = gInvertido.criarEAdicionarVertice(adj.getDado());
                    else {
                        for (Vertice k : gInvertido.getVertices()) {
                            if (k.getDado().equals(adj.getDado())) {
                                novaAdj = k;
                                achou = true;
                                break;
                            }
                        }
                        if (!achou)
                            novaAdj = gInvertido.criarEAdicionarVertice(adj.getDado());
                    }

                    gInvertido.adicionarAresta(novaAdj, novoV);
                }

                jaTem = false;
            }
        }

        return gInvertido;
    }

    //criar uma nova lista
    public ArrayList<Vertice> stackNew(ArrayList<Vertice> vArray) {
        ArrayList<Vertice> stackLista = new ArrayList<>();

        while (!stack.isEmpty()) {
            Vertice t = stack.pop();
            for (Vertice v : vArray) {
                if (t.getDado().equals(v.getDado())) {
                    stackLista.add(v);
                }
            }
        }
        return this.vertices = stackLista;
    }

    public GrafoLista getComponentesFortementeConectados() {
        //chamando dfs para o grafo original
        for (Vertice v : this.grafo.getVertices()) {
            dfs(v);
        }

        GrafoLista gInvertido = getTransposta(this.grafo);
        for (Vertice v : gInvertido.getVertices()) {
            v.setCor(Vertice.Color.WHITE);
        }
        gInvertido.setVertices(stackNew(gInvertido.getVertices()));

        GrafoLista grafoFc = new GrafoLista();
        ArrayList<GrafoLista> cFC = new ArrayList<>();
        //chamando o segundo dfs para o grafo invertido
        for (Vertice v : gInvertido.getVertices()) {
            dfsGrafoInvertido(v);
            StringBuilder componente = new StringBuilder();
            GrafoLista gTemp = new GrafoLista();
            for (Vertice j : gInvertido.getVertices()) {
                if (j.getCor().equals(Vertice.Color.BLACK)) {
                    componente.append(j.getDado());
                    j.setCor(Vertice.Color.GRAY);
                    gTemp.addVertice(j);
                }
            }
            if (!gTemp.getVertices().isEmpty()) {
                grafoFc.criarEAdicionarVertice(componente.toString());
                cFC.add(gTemp);
            }
        }
        this.fortementeConectados = cFC;
        grafoFc.setNumeroDeVertices(cFC.size());

        return grafoFc;
    }


    public void ehGrafoFortementeConexo(ArrayList<GrafoLista> cFC) {
        if (cFC.size() != 1) {
            System.out.println("Não");
            System.out.println(cFC.size());
        } else {
            System.out.println("Sim");
            System.out.println(cFC.size());
        }
    }

    public void adicionarAdjacenciasGrafoFC(GrafoLista grafoFc) {
        //todos devem  ser brancos
        for (Vertice comp : grafoFc.getVertices())
            comp.setCor(Vertice.Color.WHITE);

        for (Vertice comp : grafoFc.getVertices()) {
            for (Vertice v : this.grafo.getVertices()) {
                if (comp.getDado().contains(v.getDado())) {
                    for (Vertice adj : v.getAdjacencias()) {
                        //nao esta no mesmo componente
                        if (!comp.getDado().contains(adj.getDado())) {
                            for (Vertice c : grafoFc.getVertices()) {
                                if (c.getDado().contains(adj.getDado())) {
                                    if (!comp.getAdjacencias().contains(c))
                                        grafoFc.adicionarAresta(comp, c);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void printarOrdemTopologica() {
        while (!this.stackCFC.isEmpty())
            System.out.print(this.stackCFC.pop().getDado() + " ");
        System.out.println();
    }

    public void executarSaida() {
        GrafoLista grafoFc = getComponentesFortementeConectados();
        ehGrafoFortementeConexo(this.fortementeConectados);
        adicionarAdjacenciasGrafoFC(grafoFc);
        dfsOrdemTopologica(grafoFc);

        printarOrdemTopologica();
        grafoFc.imprimirGrafoLista();
    }

}
