package naive;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		long  tempoMedio = 0;

		// O código fica menos hard-coded ao perguntarmos diretamente no console quantos pontos devem ser gerados.
		System.out.println("Quantos pontos devem ser gerados? ");
		Scanner scan = new Scanner(System.in);
		int qtdPontos = scan.nextInt();
		// -- //

		// Loop para iterar as 10 vezes requisitadas
		for(int i = 0; i < 10; i++)
		{
			Arvbin[] listaClusters = GeraPontos.geraPontos(qtdPontos); // Gera-se os pontos a serem utilizados no código NAIVE

			long t0 = System.currentTimeMillis();	 // Inicia-se a contagem de tempo

			Naive naive = new Naive(listaClusters);
			Arvbin arvore = naive.clusterizacaoHierarquica();
			arvore.mostra(); // Expõe a árvore formada após a Clusterização Hierárquica
			
			long t1 = System.currentTimeMillis(); // Termina a contagem do tempo
			
			long tempoProcessamento = t1 - t0;
			tempoMedio += tempoProcessamento;

			System.out.println("\nTempo linear: " + tempoProcessamento); // Expõe o tempo demorado para a execução atual
		}
		tempoMedio = tempoMedio / 10;
		
		System.out.println("\nTempo medio: " + tempoMedio); // Expõe o tempo médio das 10 execuções
	}
}