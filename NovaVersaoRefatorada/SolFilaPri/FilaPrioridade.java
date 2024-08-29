package SolFilaPri;

import naive.Arvbin;
import naive.Ponto;

public class FilaPrioridade {

    private HeapBinariaMinima heap;

    public FilaPrioridade(Arvbin[] arvore) {
        this.heap = new HeapBinariaMinima(arvore.length, arvore);
    }

    @Override
    public String toString() {
        heap.imprime();
        return "";
    }

    public Arvbin fazCluster() {

        while (heap.qtdElementos() > 1) {
            Distancia menor = heap.removeMin();
            Ponto centroide = fazNovoCentroide(menor.getClusterA(), menor.getClusterB());

            Arvbin novoCluster = new Arvbin(centroide, menor.getClusterA(), menor.getClusterB());

            heap.remontaDistancias(novoCluster);
        }

        Distancia menor = heap.removeMin();
        Ponto centroide = fazNovoCentroide(menor.getClusterA(), menor.getClusterB());

        return new Arvbin(centroide, menor.getClusterA(), menor.getClusterB());
    }

    public Ponto fazNovoCentroide(Arvbin a, Arvbin b) {
        int qtdTotalNos = a.getQtdNos() + b.getQtdNos();
        float somaX = a.getSomaX() + b.getSomaX();
        float somaY = a.getSomaY() + b.getSomaY();
        float clusterCoordX = somaX / qtdTotalNos;
        float clusterCoordY = somaY / qtdTotalNos;

        return new Ponto(clusterCoordX, clusterCoordY);
    }
}
