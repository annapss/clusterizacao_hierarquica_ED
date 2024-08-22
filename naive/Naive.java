package naive;

public class Naive {
	private Arvbin<Ponto>[] arvore;
	private int restante;
	
	@SuppressWarnings("unchecked")
	public Naive(Ponto[] pontos)
	{
		arvore = new Arvbin[pontos.length];
		restante = pontos.length;
		for(int i = 0; i < pontos.length; i++)
		{
			arvore[i] = new Arvbin<Ponto>(pontos[i]);
		}
	}

	public Naive(Arvbin<Ponto>[] arv) {
		this.arvore = arv;
		this.restante = arv.length;
	}
	
	public Arvbin<Ponto> clusterizacaoHierarquica()
	{
		while(restante != 1)
		{
			menorDistancia();
			
		}
		for(int i = 0; i < arvore.length; i++)
		{
			if(arvore[i] != null)
				return arvore[i];
		}
		return null;
	}
	
	public void menorDistancia()
	{
		float menorDist = Float.MAX_VALUE;
		int[] pontosMin = new int[2];
		for(int i = 0; i < arvore.length; i++)
		{
			if(arvore[i] == null) continue;
			for(int j = i + 1; j < arvore.length; j++)
			{
				if(arvore[j] == null) continue;
				Ponto p1 = arvore[i].retornaVal();
				Ponto p2 = arvore[j].retornaVal();
				float distancia = p1.distanciaPonto(p2);
				if(distancia < menorDist)
				{
					menorDist = distancia;
					pontosMin[0] = i;
					pontosMin[1] = j;
				}
			}
		}
		
		float x = (arvore[pontosMin[0]].retornaVal().getX() + arvore[pontosMin[1]].retornaVal().getX()) / 2;
		float y = (arvore[pontosMin[0]].retornaVal().getY() + arvore[pontosMin[1]].retornaVal().getY()) / 2;
		//System.out.print(arvore[pontosMin[0]].retornaVal() + " " + arvore[pontosMin[1]].retornaVal() + "\n");
		
		
		Ponto cluster = new Ponto(x,y);
		Arvbin<Ponto> novoCluster = new Arvbin<>(cluster, arvore[pontosMin[0]], arvore[pontosMin[1]]);
		arvore[pontosMin[0]] = null;
		arvore[pontosMin[1]] = novoCluster;
		restante--;
	}
}