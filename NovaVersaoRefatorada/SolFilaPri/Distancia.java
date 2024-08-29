package SolFilaPri;

import naive.Arvbin;

public class Distancia {
    private float distancia;
    private Arvbin clusterA;
    private Arvbin clusterB;

    public Distancia(Arvbin clusterA, Arvbin clusterB) {
        this.clusterA = clusterA;
        this.clusterB = clusterB;
        this.distancia = clusterA.retornaVal().distanciaPonto(clusterB.retornaVal());
    }

    public float getDistancia() {
        return distancia;
    }

    public Arvbin getClusterA() {
        return clusterA;
    }

    public Arvbin getClusterB() {
        return clusterB;
    }

    @Override
    public String toString() {
        return  "\nDistância entre " + clusterA.retornaVal() + " e " + clusterB.retornaVal() + ": " + this.distancia;
    }
}
