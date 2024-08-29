package naive;

public class Naive {
	private Arvbin[] arvore; // Vetor dos Pontos/Centróides/Clusteres a serem combinados.
	private int qtdClustersRestantes; // Quantidade restante até o fim da execução.
	
	// Construtor do Naive
	public Naive(Arvbin[] arv) {
		this.arvore = arv;
		this.qtdClustersRestantes = arv.length;
	}
	
	// Método principal para a clusterização.
	public Arvbin clusterizacaoHierarquica()
	{
		// Loop será executado até restar somente um único cluster.
		while(qtdClustersRestantes != 1)
		{
			combinaCluster();
		}
		
		// Retorna-se o cluster que restou dentro do vetor.
		for(int i = 0; i < arvore.length; i++)
		{
			if(arvore[i] != null)
				return arvore[i];
		}
		return null;
	}
	
	public void combinaCluster()
	{
		float menorDist = Float.MAX_VALUE;
		int[] clustersCombinados = new int[2]; // Índice dos dois clusteres que possuem a menor distância entre si
		
		// Busca a menor distância entre dois clusteres.
		for(int i = 0; i < arvore.length; i++) 
		{
			if(arvore[i] == null) continue;
			for(int j = i + 1; j < arvore.length; j++)
			{
				if(arvore[j] == null) continue;
				Ponto centroide1 = arvore[i].retornaVal();
				Ponto centroide2 = arvore[j].retornaVal();
				float distancia = centroide1.distanciaPonto(centroide2);
				if(distancia < menorDist)
				{
					menorDist = distancia;
					clustersCombinados[0] = i;
					clustersCombinados[1] = j;
				}
			}
		}
		
		// Cálculo do novo centróide a partir dos clusteres/pontos envolvidos
		int qtdTotalNos = arvore[clustersCombinados[0]].getQtdNos() + arvore[clustersCombinados[1]].getQtdNos();
		float somaX = arvore[clustersCombinados[0]].getSomaX() + arvore[clustersCombinados[1]].getSomaX();
		float somaY = arvore[clustersCombinados[0]].getSomaY() + arvore[clustersCombinados[1]].getSomaY();
		float clusterCoordX = somaX / qtdTotalNos;
		float clusterCoordY = somaY / qtdTotalNos;
		
		// Armazena-se o novo centróide dentro da árvore e remove-se os anteriormente usados do vetor
		Ponto centroide = new Ponto(clusterCoordX,clusterCoordY);
		Arvbin novoCluster = new Arvbin(centroide, arvore[clustersCombinados[0]], arvore[clustersCombinados[1]]);
		arvore[clustersCombinados[0]] = null;
		arvore[clustersCombinados[1]] = novoCluster;
		
		qtdClustersRestantes--; // Diminui a quantidade de iterações restantes
	}
}