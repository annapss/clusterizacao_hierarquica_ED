
public class Main {
	public static void main(String[] args) {
		long  tempoMedio = 0;
		for(int i = 0; i < 10; i++)
		{
			Ponto[] listaPontos = GeraPontos.geraPontos(100);
			/*for(int i = 0; i < listaPontos.length; i++)
			{
				System.out.println(listaPontos[i]);
			}*/
			long t0 = System.currentTimeMillis();	

			Naive naive = new Naive(listaPontos);
			Arvbin<Ponto> pontosMin = naive.clusterizacaoHierarquica();
			pontosMin.mostra();
			
			long t1 = System.currentTimeMillis();
			
			long tempoProcessamento = t1 - t0;
			tempoMedio += tempoProcessamento;
			System.out.println("\nTempo linear: " + tempoProcessamento);
		}
		tempoMedio = tempoMedio / 10;
		System.out.println("\nTempo medio: " + tempoMedio);
	}
}
