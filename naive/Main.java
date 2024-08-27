package naive;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		long  tempoMedio = 0;
		System.out.println("Quantos pontos devem ser gerados? ");
		Scanner scan = new Scanner(System.in);
		int qtdPontos = scan.nextInt();
		for(int i = 0; i < 10; i++) //executa a clusterização 10 vezes para 10 entradas diferentes
		{
			Arvbin[] listaClusters = GeraPontos.geraPontos(qtdPontos);
			//Arvbin[] listaClusters = GeraPontos.criaPontos(5);
			long t0 = System.currentTimeMillis();	

			Naive naive = new Naive(listaClusters);
			Arvbin arvore = naive.clusterizacaoHierarquica();
			System.out.println(arvore.getQtdNos());
			arvore.mostra(); // imprime arvore mostrando todos os clusters que foram criados
			
			long t1 = System.currentTimeMillis();
			
			long tempoProcessamento = t1 - t0;
			tempoMedio += tempoProcessamento;
			System.out.println("\nTempo linear: " + tempoProcessamento);
		}
		tempoMedio = tempoMedio / 10;
		System.out.println("\nTempo medio: " + tempoMedio);
	}
}