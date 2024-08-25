package filaPrioridade;

import java.util.Scanner;

import naive.Arvbin;
import naive.GeraPontos;
import naive.Naive;
import naive.Cluster;

public class Main {
	public static void main(String[] args) {
		long  tempoMedio = 0;
		System.out.println("Quantos pontos devem ser gerados? ");
		Scanner scan = new Scanner(System.in);
		int qtdPontos = scan.nextInt();
		for(int i = 0; i < 1; i++)
		{
			Arvbin[] listaClusters = GeraPontos.geraPontosIguais(qtdPontos);
			//Arvbin[] listaClusters = GeraPontos.criaPontos(qtdPontos);
			/*for(int i = 0; i < listaClusters.length; i++)
			{
				System.out.println(listaClusters[i]);
			}*/
			long t0 = System.currentTimeMillis();	

			SolucaoFilaPrioridade fila = new SolucaoFilaPrioridade(listaClusters);
			Arvbin arvore = fila.clusterizacaoHierarquica();
			arvore.mostra();
			
			long t1 = System.currentTimeMillis();
			
			long tempoProcessamento = t1 - t0;
			tempoMedio += tempoProcessamento;
			System.out.println("\nTempo linear: " + tempoProcessamento);
		}
		tempoMedio = tempoMedio / 1;
		System.out.println("\nTempo medio: " + tempoMedio);
	}
}
