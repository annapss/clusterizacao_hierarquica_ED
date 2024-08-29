package SolFilaPri;

import naive.Arvbin;
import naive.Cluster;

public class Distancia {
    private float distancia;
    private Arvbin clusterA;
    private Arvbin clusterB;

    public Distancia(Arvbin clusterA, Arvbin clusterB) {
        this.clusterA = clusterA;
        this.clusterB = clusterB;
        this.distancia = (float) Math.sqrt(Math.pow(clusterA.retornaVal().getX() + clusterB.retornaVal().getX(), 2) + Math.pow(clusterA.retornaVal().getY() + clusterB.retornaVal().getY(), 2));
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
