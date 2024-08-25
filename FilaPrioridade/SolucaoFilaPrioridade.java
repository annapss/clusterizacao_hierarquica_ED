package filaPrioridade;
import naive.Arvbin;
import naive.Cluster;

public class SolucaoFilaPrioridade {
	private Arvbin[] arvore;
	private int qtdClustersRestantes;
	private HeapBinariaMinima fila;
	
	public SolucaoFilaPrioridade(Arvbin[] arvore)
	{
		this.arvore = arvore;
		qtdClustersRestantes = arvore.length;
	}
	
	public Arvbin clusterizacaoHierarquica()
	{
		int qtd = 0;
		Distancia[] distancias = new Distancia[(qtdClustersRestantes * (qtdClustersRestantes - 1))/2];
		//calcula as distancias e insere na fila (heap)
		for(int i = 0; i < arvore.length; i++)
		{
			if(arvore[i] == null) continue;
			for(int j = i + 1; j < arvore.length; j++)
			{
				if(arvore[j] == null) continue;
				Arvbin clusterA = arvore[i];
				Arvbin clusterB = arvore[j];
				Distancia d = new Distancia(clusterA, clusterB);
				distancias[qtd++] = d;
			}
		}
		//insere na fila (heap)
		fila = new HeapBinariaMinima(distancias.length, distancias);
		while(fila.getQuant() > 1) 
		{
			combinaCluster();
			
		}
		
		Distancia min = fila.removeMin(); //pega a menor distancia da fila
		Arvbin arvoreA = min.getClusterA();
		Arvbin arvoreB = min.getClusterB();
		int qtdTotalNos = arvoreA.getQtdNos() + arvoreB.getQtdNos();
		float somaX = arvoreA.getSomaX() + arvoreB.getSomaX();
		float somaY = arvoreA.getSomaY() + arvoreB.getSomaY();
		float clusterCoordX = somaX / qtdTotalNos;
		float clusterCoordY = somaY / qtdTotalNos;
		
		Cluster cluster = new Cluster(clusterCoordX,clusterCoordY); //centroide
		Arvbin novoCluster = new Arvbin(cluster, arvoreA, arvoreB);
		return novoCluster;
	}
	
	public void combinaCluster()
	{
		Distancia min = fila.removeMin(); //pega a menor distancia da fila
		Arvbin arvoreA = min.getClusterA();
		Arvbin arvoreB = min.getClusterB();
		int qtdTotalNos = arvoreA.getQtdNos() + arvoreB.getQtdNos();
		float somaX = arvoreA.getSomaX() + arvoreB.getSomaX();
		float somaY = arvoreA.getSomaY() + arvoreB.getSomaY();
		float clusterCoordX = somaX / qtdTotalNos;
		float clusterCoordY = somaY / qtdTotalNos;
		
		Cluster cluster = new Cluster(clusterCoordX,clusterCoordY); //centroide
		Arvbin novoCluster = new Arvbin(cluster, arvoreA, arvoreB);
		
		fila.removeDist(novoCluster); // recalcula as distancias que apresentam os pontos que foram combinados
		qtdClustersRestantes--;
		
	}
}
