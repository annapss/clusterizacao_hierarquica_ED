package SolFilaPri;
import naive.Arvbin;
import naive.GeraPontos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long  tempoMedio = 0;
        System.out.println("Quantos pontos devem ser gerados? ");
        Scanner scan = new Scanner(System.in);
        int qtdPontos = scan.nextInt();

        for(int i = 0; i < 10; i++)
        {
            Arvbin[] listaClusters = GeraPontos.geraPontos(qtdPontos);
            long t0 = System.currentTimeMillis();

            FilaPrioridade fila = new FilaPrioridade(listaClusters);
            Arvbin arvore = fila.fazCluster();
            arvore.mostra();
            System.out.println("\n" + arvore.getTotalNos());

            long t1 = System.currentTimeMillis();

            long tempoProcessamento = t1 - t0;

            tempoMedio += tempoProcessamento;

            System.out.println("\nTempo linear: " + tempoProcessamento);
        }
        tempoMedio = tempoMedio / 10;

        System.out.println("\nTempo medio: " + tempoMedio);
    }
}
