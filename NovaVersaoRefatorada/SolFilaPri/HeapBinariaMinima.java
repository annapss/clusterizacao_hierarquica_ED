package SolFilaPri;

import naive.Arvbin;

public class HeapBinariaMinima
{
	private int n;               /* Numero de elementos no heap. */   
	private int tam;             /* Tamanho m�ximo do heap. */
	private Distancia[] vetor;          /* Vetor com elementos. */

	/* Constr�i heap a partir de vetor v. */
	public HeapBinariaMinima(int qtdPontos, Arvbin[] v)
	{
		vetor = new Distancia[(qtdPontos * (qtdPontos - 1)) / 2 + 1];
		tam = vetor.length;
		n = 1;

		for (int i = 0; i < qtdPontos; i++) {
			if (v[i] == null) continue;
			for (int j = i + 1; j < qtdPontos; j++) {
				if (v[j] == null) continue;
				vetor[n++] = new Distancia(v[i], v[j]);
			}
		}

		n--;

		constroiHeap();
	}

	/* Testa se a fila de prioridade est� logicamente vazia.
	   Retorna true se vazia, false, caso contr�rio. */
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
		System.out.print("Conte�do da heap: ");
		
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

	/* M�todo auxiliar que estabelece a propriedade de ordem do heap a 
	 * partir de um vetor arbitr�rio dos itens. */
	private void constroiHeap()
	{
		for( int i = n / 2; i > 0; i-- )
			refaz(i);
	}

	/* M�todo auxiliar para restaurar a propriedade de heap que
	 * n�o est� sendo respeitada pelo n� i. */
	private void refaz(int i)
	{
		Distancia x = vetor[ i ];

		while(2*i <= n)
		{
			int filhoEsq, filhoDir, menorFilho;
			
			filhoEsq = 2*i;
			filhoDir = 2*i + 1;
			
			/* Por enquanto, o menor filho � o da esquerda. */
			menorFilho = filhoEsq;
			
			/* Verifica se o filho direito existe. */
			if(filhoDir <= n)
			{
				 /* Em caso positivo, verifica se � menor que o filho esquerdo. */
				if(vetor[ filhoDir ].getDistancia() < vetor[ filhoEsq ].getDistancia())
					menorFilho = filhoDir;
			}

			/* Compara o valor do menor dos filhos com x. */
			if(vetor[ menorFilho ].getDistancia() < x.getDistancia())
				vetor [ i ] = vetor[ menorFilho ];
			else
				break;
			
			/* Como o elemento x que estava na posi��o "i" desceu para o n�vel de baixo, a pr�xima
			 * itera��o vai verificar a possibilidade de trocar esse elemento x (agora na 
			 * posi��o "menorFilho") com um de seus novos filhos. */
			i = menorFilho;
		}
		
		vetor[ i ] = x;
	}

	/* Insere item x na fila de prioridade, mantendo a propriedade do heap.
	 * S�o permitidas duplicatas. */
	public void insere(Distancia x)
	{
		if (tam == n)
		{
			System.out.println("Fila de prioridades cheia!");
			return;
		}

		/* O elemento � inicialmente inserido na primeira posi��o dispon�vel
		 * na �rvore, considerando de cima para baixo, da esquerda para a direita. */
		n++;
		int pos = n;
		
		/* Sentinela utilizada para tratar o caso do pai do n� raiz (de �ndice 1). */
		vetor[0] = x;

		/* Refaz heap (sobe elemento). */
		while(x.getDistancia() < vetor[pos/2].getDistancia())
		{
			vetor[pos] = vetor[ pos/2 ];
			pos /= 2;
		}
		
		vetor[pos] = x;
	}

	public void remontaDistancias(Arvbin nova) {
		Arvbin arvoreEsquerda = nova.retornaEsq();
		Arvbin arvoreDireita = nova.retornaDir();

		for (int i = 1; i < n + 1; i++) {

			if (verificaPossuir(vetor[i], arvoreEsquerda)) {
				vetor[i--] = vetor[n--];

			}
			else if (verificaPossuir(vetor[i], arvoreDireita)) {
				Distancia a = new Distancia(retornaInversa(vetor[i], arvoreDireita), nova);
				vetor[i] = a;
			}
		}

		refaz(1);
	}

	public boolean verificaPossuir(Distancia a, Arvbin b) {
		return (a.getClusterA().equals(b) || a.getClusterB().equals(b));
	}

	public Arvbin retornaInversa(Distancia a, Arvbin b) {
		if (a.getClusterA().equals(b)) return a.getClusterB();
		else return a.getClusterA();
	}

	public int qtdElementos() {
		return n;
	}

}