import java.util.ArrayList;
import java.util.Stack;

public class Kosaraju {
    private ArrayList<Vertice> vertices;
    private Stack<Vertice> stack;
    private GrafoLista grafo;
    private GrafoLista fortementeConectados;

    public Kosaraju(GrafoLista grafo) {
        this.stack = new Stack<Vertice>();
        this.grafo = grafo;
        this.vertices = grafo.getVertices();
        this.fortementeConectados = new GrafoLista();
    }

    public void dfs(Vertice v) {
        if (v.getCor().equals(Vertice.Color.WHITE)) {
            dfsVisit(v);
        }
    }

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

    public void dfs2(Vertice v) {
        if (v.getCor().equals(Vertice.Color.WHITE)) {
            dfsVisit2(v);
        }
    }

    public void dfsVisit2(Vertice v) {
        v = getVertice(v.getDado());
        v.setCor(Vertice.Color.GRAY);

        for (Vertice adj : v.getAdjacencias()) {
            //usar o vertice do array vertices da classe
            for (Vertice j : this.vertices) {
                if (adj.getDado().equals(j.getDado())) {
                    if (j.getCor().equals(Vertice.Color.WHITE)) {
                        dfsVisit2(j);
                    }
                }
            }
        }

        v = getVertice(v.getDado());
        v.setCor(Vertice.Color.BLACK);
    }

    public Vertice getVertice(String dado) {
        for (Vertice v : this.vertices) {
            if (v.getDado().equals(dado))
                return v;
        }

        System.out.println("retornou null");
        return null;
    }

    public GrafoLista getTransposta(GrafoLista g) {
        GrafoLista gInvertido = new GrafoLista();
        gInvertido.setNumeroDeVertices(g.getNumeroDeVertices());
        for (Vertice v : g.getVertices()) {
            boolean jaTem = false;

            Vertice novoV = getVertice(v.getDado());

            if (gInvertido.getVertices().isEmpty()) {
                novoV = gInvertido.criarEAdicionarVertice(v.getDado());
            } else {
                boolean achou = false;
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

            for (Vertice adj : v.getAdjacencias()) {
                //verificar se ja possui um vertice no grafo invertido
                for (Vertice vertI : gInvertido.getVertices()) {
                    if (vertI.getDado().equals(adj.getDado())) {
                        gInvertido.adicionarAresta(vertI, novoV);
                        jaTem = true;
                        break;
                    }
                }

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

    public void printarVEAdj(GrafoLista g) {
        Vertice test = new Vertice("xota");
        Vertice test2 = new Vertice("xota");

        for (Vertice v : g.getVertices()) {
            if (v.getDado().equals("b"))
                test2 = v;
            System.out.println("Vertice: " + v.getDado() + " ");
            //System.out.println("vertice cor: " + v.getCor());
            for (Vertice adj : v.getAdjacencias()) {
                System.out.println("adjacencia do " + v.getDado() + "=" + adj.getDado() + " ");
                //System.out.print("cor da adj: " + adj.getCor());
                for (Vertice adjDaAdj : adj.getAdjacencias()) {
                    System.out.println("adjacencias da adj " + adj.getDado() + "= " + adjDaAdj.getDado() + " ");
                    //System.out.println("cor da adj da adj: " + adj.getCor());

                    if (adjDaAdj.getDado().equals("b")) {
                        test = adjDaAdj;
                    }
                }
            }
            System.out.println();
        }

        if (test.equals(test2))
            System.out.println("iguais e o dado eh: " + test.getDado());
        else
            System.out.println("diferente e o dado1 eh: " + test.getDado() + " e dado 2: " + test2.getDado());
    }

    public ArrayList<GrafoLista> getComponentesFortementeConectados() {

        //chamando dfs para o grafo original
        for (Vertice v : this.grafo.getVertices()) {
            dfs(v);
        }

        GrafoLista gInvertido = getTransposta(this.grafo);
        for (Vertice v : gInvertido.getVertices()) {
            v.setCor(Vertice.Color.WHITE);
        }
        gInvertido.setVertices(stackNew(gInvertido.getVertices()));

        ArrayList<GrafoLista> cFC = new ArrayList<>();
        //chamando o segundo dfs para o grafo invertido
        for (Vertice v : gInvertido.getVertices()) {
            dfs2(v);
            GrafoLista gTemp = new GrafoLista();
            for (Vertice j : gInvertido.getVertices()) {
                if (j.getCor().equals(Vertice.Color.BLACK)) {
                    gTemp.addVertice(j);
                    j.setCor(Vertice.Color.GRAY);
                }
            }
            if (!gTemp.getVertices().isEmpty())
                cFC.add(gTemp);
        }

        return cFC;
    }

    public Stack<Vertice> getStack() {
        return stack;
    }
}
