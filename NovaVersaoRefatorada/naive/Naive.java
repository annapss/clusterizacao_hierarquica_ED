package naive;

public class Naive {
	private Arvbin[] arvore; //vetor de arvores onde guardamos todas as �rvores que precisam ser combinadas
	private int qtdClustersRestantes; //quantidade de clusters que ainda precisam ser combinados
	
	@SuppressWarnings("unchecked")
	public Naive(Ponto[] pontos) //se a gente n�o tiver mais usando esse construtor, acho que podemos tirar
	{
		arvore = new Arvbin[pontos.length];
		qtdClustersRestantes = pontos.length;
		for(int i = 0; i < pontos.length; i++)
		{
			arvore[i] = new Arvbin(pontos[i]);
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
		/*Quando tiver somente um cluster, teremos somente uma �rvore no nosso vetor.
		 * Assim, retornamos essa �rvore
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
		//Essa parte que vai mudar :(
		int qtdTotalNos = arvore[clustersCombinados[0]].getQtdNos() + arvore[clustersCombinados[1]].getQtdNos();
		float somaX = arvore[clustersCombinados[0]].getSomaX() + arvore[clustersCombinados[1]].getSomaX();
		float somaY = arvore[clustersCombinados[0]].getSomaY() + arvore[clustersCombinados[1]].getSomaY();
		float clusterCoordX = somaX / qtdTotalNos;
		float clusterCoordY = somaY / qtdTotalNos;
		//System.out.print(arvore[clustersCombinados[0]].retornaVal() + " " + arvore[clustersCombinados[1]].retornaVal() + "\n");
		
		
		Ponto centroide = new Ponto(clusterCoordX,clusterCoordY);
		Arvbin novoCluster = new Arvbin(centroide, arvore[clustersCombinados[0]], arvore[clustersCombinados[1]]);
		arvore[clustersCombinados[0]] = null;
		arvore[clustersCombinados[1]] = novoCluster;
		qtdClustersRestantes--;
	}
}