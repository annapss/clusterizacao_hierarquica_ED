package SolFilaPri;

import naive.Arvbin;

public class Distancia {
    private float distancia; // Valor da distância entre os dois pontos/centróides envolvidos.
    private Arvbin clusterA; // Primeiro ponto/centróide envolvido na distância
    private Arvbin clusterB; // Segundo ponto/centróide envolvido na distância

    // Construtor e define a distância entre os dois pontos/centróides fornecidos.
    public Distancia(Arvbin clusterA, Arvbin clusterB) {
        this.clusterA = clusterA;
        this.clusterB = clusterB;
        this.distancia = clusterA.retornaVal().distanciaPonto(clusterB.retornaVal());
    }

    // GETS //
    public float getDistancia() {
        return distancia;
    }

    public Arvbin getClusterA() {
        return clusterA;
    }

    public Arvbin getClusterB() {
        return clusterB;
    }
    // ---- //

    // TO STRING -> usado para depuração do código //
    @Override
    public String toString() {
        return  "\nDistância entre " + clusterA.retornaVal() + " e " + clusterB.retornaVal() + ": " + this.distancia;
    }
}
