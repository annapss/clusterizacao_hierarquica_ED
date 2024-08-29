package SolFilaPri;

import naive.Arvbin;

public class HeapBinariaMinima
{
	private int n;               /* Numero de elementos atualmente no heap. */   
	private int tam;             /* Tamanho máximo do heap. */
	private Distancia[] vetor;   /* Vetor com elementos. */

	/* Constrói heap de distâncias a partir de vetor v. */
	public HeapBinariaMinima(int qtdPontos, Arvbin[] v)
	{

		// O total de distâncias no início do algoritmo será de (n * (n - 1)) / 2, levando em consideração a regra matemática de combinatória.
		vetor = new Distancia[(qtdPontos * (qtdPontos - 1)) / 2 + 1];
		tam = vetor.length;
		n = 1;

		// Esse trecho, de fato, cria as distâncias que serão utilizadas posteriormente.
		for (int i = 0; i < qtdPontos; i++) {
			if (v[i] == null) continue;
			for (int j = i + 1; j < qtdPontos; j++) {
				if (v[j] == null) continue;
				vetor[n++] = new Distancia(v[i], v[j]);
			}
		}

		// Como o n fora iniciado como 1, é necessário corrigir sua falha de 1.
		n--;

		constroiHeap();
	}

	/* Testa se a fila de prioridade está logicamente vazia.
	   Retorna true se vazia, false, caso contrário. */
	public boolean vazia()
	{
		return n == 0;
	}

	/* Imprime os elementos da heap. */
	public void imprime()
	{
		System.out.print("Conteúdo da heap: ");
		
		for(int i = 1; i <= n; i++)
			System.out.print(vetor[i] + " ");

		System.out.println();
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
			if(vetor[ menorFilho ].getDistancia() < x.getDistancia())
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


	// Código para adaptação da heap para a nova distância gerada
	public void remontaDistancias(Arvbin nova) {
		
		// Inicialmente, pega-se os centróides/pontos envolvidos na nova distância.
		Arvbin arvoreEsquerda = nova.retornaEsq();
		Arvbin arvoreDireita = nova.retornaDir();

		// Itera-se por todos os elementos da heap para verificar quais distâncias envolviam algum dos dois centróides/pontos.
		for (int i = 1; i < n + 1; i++) {

			// Caso o centróide/ponto posicionado na ESQUERDA do novo centróide esteja presente na distância a ser verificada, exclui-se a distância da heap.
			if (verificaPossuir(vetor[i], arvoreEsquerda)) {
				vetor[i--] = vetor[n--];

			}
			// Caso o centróide/ponto posicionado na DIREITA do novo centróide esteja presente na distância a ser verificada, substitui-o pela Distância entre o
			// outro centróide/ponto da distância verificada com o novo centróide gerado.
			else if (verificaPossuir(vetor[i], arvoreDireita)) {
				vetor[i] = new Distancia(retornaInversa(vetor[i], arvoreDireita), nova);
			}
		}

		// Reordena a HEAP para usos posteriores
		refaz(1);
	}

	// Método que verifica se a distância em questão possui o centróide/ponto em sua construção.
	public boolean verificaPossuir(Distancia a, Arvbin b) {
		return (a.getClusterA().equals(b) || a.getClusterB().equals(b));
	}

	// Método que retorna o centróide/ponto que é diferente do verificado da distância.
	public Arvbin retornaInversa(Distancia a, Arvbin b) {
		if (a.getClusterA().equals(b)) return a.getClusterB();
		else return a.getClusterA();
	}

	// Retorna a quantidade de Distâncias presentes na heap no momento.
	public int qtdElementos() {
		return n;
	}

}