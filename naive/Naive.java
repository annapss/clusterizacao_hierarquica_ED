package naive;

public class Naive {
	private Arvbin[] arvore; //vetor de arvores onde guardamos todas as árvores que precisam ser combinadas
	private int qtdClustersRestantes; //quantidade de clusters que ainda precisam ser combinados
	
	@SuppressWarnings("unchecked")
	public Naive(Cluster[] clusters) //se a gente não tiver mais usando esse construtor, acho que podemos tirar
	{
		arvore = new Arvbin[clusters.length];
		qtdClustersRestantes = clusters.length;
		for(int i = 0; i < clusters.length; i++)
		{
			arvore[i] = new Arvbin(clusters[i]);
		}
	}

	public Naive(Arvbin[] arv) {
		this.arvore = arv;
		this.qtdClustersRestantes = arv.length;
	}
	
	public Arvbin clusterizacaoHierarquica()
	{
		while(qtdClustersRestantes != 1)
		{
			combinaCluster();
			
		}
		/*Quando tiver somente um cluster, teremos somente uma árvore no nosso vetor.
		 * Assim, retornamos essa árvore
		 */
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
		int[] clustersCombinados = new int[2]; //posicao dos dois clusters que possuem a menor distancia
		//encontra menor distancia entre os clusters
		for(int i = 0; i < arvore.length; i++) 
		{
			if(arvore[i] == null) continue;
			for(int j = i + 1; j < arvore.length; j++)
			{
				if(arvore[j] == null) continue;
				Cluster cluster1 = arvore[i].retornaVal();
				Cluster cluster2 = arvore[j].retornaVal();
				float distancia = cluster1.distanciaPonto(cluster2);
				if(distancia < menorDist)
				{
					menorDist = distancia;
					clustersCombinados[0] = i;
					clustersCombinados[1] = j;
				}
			}
		}
		//Essa parte que vai mudar :(
		int qtdTotalNos = arvore[clustersCombinados[0]].getQtdNos() + arvore[clustersCombinados[1]].getQtdNos();
		float somaX = arvore[clustersCombinados[0]].getSomaX() + arvore[clustersCombinados[1]].getSomaX();
		float somaY = arvore[clustersCombinados[0]].getSomaY() + arvore[clustersCombinados[1]].getSomaY();
		float clusterCoordX = somaX / qtdTotalNos;
		float clusterCoordY = somaY / qtdTotalNos;
		//System.out.print(arvore[clustersCombinados[0]].retornaVal() + " " + arvore[clustersCombinados[1]].retornaVal() + "\n");
		
		
		Cluster cluster = new Cluster(clusterCoordX,clusterCoordY);
		Arvbin novoCluster = new Arvbin(cluster, arvore[clustersCombinados[0]], arvore[clustersCombinados[1]]);
		arvore[clustersCombinados[0]] = null;
		arvore[clustersCombinados[1]] = novoCluster;
		qtdClustersRestantes--;
	}
}