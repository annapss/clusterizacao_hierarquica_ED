package SolFilaPri;

import naive.Arvbin;
import naive.Ponto;

public class FilaPrioridade {

    // Heap usada na solução desejada
    private HeapBinariaMinima heap;

    // Cria a heap que será usada.
    public FilaPrioridade(Arvbin[] arvore) {
        this.heap = new HeapBinariaMinima(arvore.length, arvore);
    }

    // TO STRING -> usado para depuração do código //
    @Override
    public String toString() {
        heap.imprime();
        return "";
    }
    // ------------------------------------------- //

    public Arvbin fazCluster() {

        // Irá executar exatamente n - 1 vezes, só irá parar caso reste uma única Distância dentro da heap (que possuirá dois centróides/pontos).
        while (heap.qtdElementos() > 1) {
            Distancia menor = heap.removeMin(); // Pega a menor distância dentre todas da heap.

            // Constrói o novo centróide que será utilizado nas futuras distâncias.
            Ponto centroide = fazNovoCentroide(menor.getClusterA(), menor.getClusterB()); 
            Arvbin novoCluster = new Arvbin(centroide, menor.getClusterA(), menor.getClusterB());

            // Refaz as distâncias tendo como base o novo centróide.
            heap.remontaDistancias(novoCluster);
        }

        // Como restou apenas uma distância, esse trecho cria e retorna o centróide final.
        Distancia menor = heap.removeMin();
        Ponto centroide = fazNovoCentroide(menor.getClusterA(), menor.getClusterB());
        return new Arvbin(centroide, menor.getClusterA(), menor.getClusterB());
    }

    // Método auxiliar para gerar um centróide a ser utilizado futuramente.
    public Ponto fazNovoCentroide(Arvbin a, Arvbin b) {

        // Basicamente gera a média entre todos os pontos até agora considerados.
        int qtdTotalNos = a.getQtdNos() + b.getQtdNos();
        float somaX = a.getSomaX() + b.getSomaX();
        float somaY = a.getSomaY() + b.getSomaY();
        float clusterCoordX = somaX / qtdTotalNos;
        float clusterCoordY = somaY / qtdTotalNos;

        return new Ponto(clusterCoordX, clusterCoordY);
    }
}
