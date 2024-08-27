package FilaPrioridade;
import naive.Arvbin;
import naive.Cluster;
public class HeapBinariaMinima
{
	private int n;               /* Numero de elementos no heap. */   
	private int tam;             /* Tamanho máximo do heap. */
	private Distancia[] vetor;          /* Vetor com elementos. */

	/* Constrói heap vazio. */
	public HeapBinariaMinima(int tamanho)
	{
		n = 0;
		tam = tamanho;
		vetor = new Distancia[tamanho+1];
	}

	/* Constrói heap a partir de vetor v. */
	public HeapBinariaMinima(int tamanho, Distancia[] v)
	{
		tam = tamanho;
		vetor = new Distancia[tamanho+1];
		n = tamanho;

		for( int i = 0; i < tamanho; i++ )
			vetor[ i + 1 ] = v[ i ];

		constroiHeap();
		/*for(int i = 1; i <= n; i++)
		{
			Cluster a = vetor[i].getClusterA().retornaVal();
			Cluster b = vetor[i].getClusterB().retornaVal();
			System.out.println("Cluster A coordX: " + a.getX() + "Cluster A coordY: " + a.getY() + " Cluster B coordX: " + b.getX() + "Cluster B coordY: " + b.getY());
		}
		System.out.println();*/
	}

	/* Testa se a fila de prioridade está logicamente vazia.
	   Retorna true se vazia, false, caso contrário. */
	public boolean vazia()
	{
		return n == 0;
	}

	/* Faz a lista de prioridades logicamente vazia. */
	public void fazVazia()
	{
		n = 0;
	}

	/* Imprime os elementos da heap. */
	public void imprime()
	{
		System.out.print("Conteúdo da heap: ");
		
		for(int i = 1; i <= n; i++)
			System.out.print(vetor[i] + " ");

		System.out.println();
	}

	/* Busca o menor item na fila de prioridades.
	   Retorna o menor item em itemMin e true, ou falso se a heap estiver vazia. */
	public Distancia min()
	{
		if (this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return null;
		}

		return vetor[1];
	}

	/* Remove o menor item da lista de prioridades e coloca ele em itemMin. */
	public Distancia removeMin()
	{
		Distancia itemMin;
		
		if(this.vazia())
		{
			System.out.println("Fila de prioridades vazia!");
			return null;
		}

		itemMin = vetor[1];
		vetor[1] = vetor[n];
		n--;
		// refaz(1);
		
		return itemMin;
	}

	/* Método auxiliar que estabelece a propriedade de ordem do heap a 
	 * partir de um vetor arbitrário dos itens. */
	private void constroiHeap()
	{
		for( int i = n / 2; i > 0; i-- )
			refaz(i);
	}

	/* Método auxiliar para restaurar a propriedade de heap que
	 * não está sendo respeitada pelo nó i. */
	private void refaz(int i)
	{
		Distancia x = vetor[ i ];

		while(2*i <= n)
		{
			int filhoEsq, filhoDir, menorFilho;
			
			filhoEsq = 2*i;
			filhoDir = 2*i + 1;
			
			/* Por enquanto, o menor filho é o da esquerda. */
			menorFilho = filhoEsq;
			
			/* Verifica se o filho direito existe. */
			if(filhoDir <= n)
			{
				 /* Em caso positivo, verifica se é menor que o filho esquerdo. */
				if(vetor[ filhoDir ].getDistancia() < vetor[ filhoEsq ].getDistancia())
					menorFilho = filhoDir;
			}

			/* Compara o valor do menor dos filhos com x. */
			if(((Distancia)vetor[ menorFilho ]).getDistancia() < ((Distancia)x).getDistancia())
				vetor [ i ] = vetor[ menorFilho ];
			else
				break;
			
			/* Como o elemento x que estava na posição "i" desceu para o nível de baixo, a próxima
			 * iteração vai verificar a possibilidade de trocar esse elemento x (agora na 
			 * posição "menorFilho") com um de seus novos filhos. */
			i = menorFilho;
		}
		
		vetor[ i ] = x;
	}

	/* Insere item x na fila de prioridade, mantendo a propriedade do heap.
	 * São permitidas duplicatas. */
	public void insere(Distancia x)
	{
		if (tam == n)
		{
			System.out.println("Fila de prioridades cheia!");
			return;
		}

		/* O elemento é inicialmente inserido na primeira posição disponível
		 * na árvore, considerando de cima para baixo, da esquerda para a direita. */
		n++;
		int pos = n;
		
		/* Sentinela utilizada para tratar o caso do pai do nó raiz (de índice 1). */
		vetor[0] = x;

		/* Refaz heap (sobe elemento). */
		while(((Distancia)x).getDistancia() < ((Distancia)vetor[pos/2]).getDistancia())
		{
			vetor[pos] = vetor[ pos/2 ];
			pos /= 2;
		}
		
		vetor[pos] = x;
	}
	public void removeDist(Arvbin arvore)
	{
		/*O p é a quantidade de clusters que geram n distancias*/
		// n = (p * (p - 1)) /2 -> n é a quantidade de distancias
		int p = (int)(1 + Math.sqrt((8 * n) + 1)) / 2;
		p = p - 2;
		int remocoes = 0;
		Cluster clusterEsq = (Cluster) arvore.retornaEsq().retornaVal();
		Cluster clusterDir = (Cluster) arvore.retornaDir().retornaVal();
		for(int i = 1; i <= n; i++)
		{
			Distancia distanciaAtual = vetor[i];
			/*
			 * A ideia aqui é verificar se a distanciaAtual apresenta um dos clusters que combinamos
			 * como parte dela. Se encontrarmos o clusterEsq, nós calculamos a nova distancia e 
			 * colocamos na heap. Se encontrarmos o clusterDir, nós removemos da heap para não recalcular duas
			 * distancias duas vezes.
			 */
			Arvbin arvoreA = distanciaAtual.temPonto(clusterEsq); //arvore da distancia que não é igual ao clusterEsq
			Arvbin arvoreB = distanciaAtual.temPonto(clusterDir); //arvore da distancia que não é igual ao clusterDir
			if(arvoreB != null && remocoes < p)
			{
				vetor[i] = vetor[n--];
				i--;
				remocoes++;
			}
			else if(arvoreA != null)
			{
				Distancia novaDistancia = new Distancia(arvoreA, arvore);
				vetor[i] = novaDistancia;
			}
		}
		refaz(1);
		/*for(int i = 1; i < n; i++)
		{
			Cluster a = vetor[i].getClusterA().retornaVal();
			Cluster b = vetor[i].getClusterB().retornaVal();
			System.out.println("Cluster A coordX: " + a.getX() + "Cluster A coordY: " + a.getY() + " Cluster B coordX: " + b.getX() + "Cluster B coordY: " + b.getY());
		}*/
		//System.out.println();
	}
	
	public int getQuant() {
		return n;
	}
	
	public void imprimeHeap()
	{
		for(int i = 1; i <= n; i++)
		{
			System.out.println(vetor[i].getDistancia());
		}
		throw new IllegalArgumentException();
	}
}