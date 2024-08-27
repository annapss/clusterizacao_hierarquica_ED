package naive;
import java.util.Deque;
import java.util.Iterator;
import naive.Cluster;
public class Arvbin
{
	private Cluster val;   /* Valor armazenado na raiz. */

	/* Referências para subárvores. */
	private Arvbin esq, dir;
	private float somaX;
	private float somaY;
	private int qtdNos;

	/* Construtor de árvore sem sub-ávores (dir = esq = null). É fornecido apenas o valor da raiz. */
	public Arvbin(Cluster valor)
	{
		val = valor;
		esq = null;
		dir = null;
		somaX = valor.getX();
		somaY = valor.getY();
		qtdNos = 1;
	}

	/* Construtor de árvore com sub-ávores. São fornecidos
	o valor da raiz e as subárvores, que devem ter sido 
	construídas previamente.*/
	public Arvbin(Cluster valor, Arvbin arvEsq, Arvbin arvDir)
	{
		val = valor;
		esq = arvEsq;
		dir = arvDir;
		somaX = arvEsq.getSomaX() + arvDir.getSomaX();
		somaY = arvEsq.getSomaY() + arvDir.getSomaY();
		qtdNos = arvEsq.getQtdNos() + arvDir.getQtdNos() + 1;
	}
	
	public float getSomaX() {
		return somaX;
	}

	public void setSomaX(float somaX) {
		this.somaX = somaX;
	}

	public float getSomaY() {
		return somaY;
	}

	public void setSomaY(float somaY) {
		this.somaY = somaY;
	}

	public int getQtdNos() {
		return qtdNos;
	}

	public void setQtdNos(int qtdNos) {
		this.qtdNos = qtdNos;
	}

	/* Retorna o valor armazenado na raiz. */
	public Cluster retornaVal()
	{
		return val;
	}

	/* Retorna a subárvore esquerda 
	   (ou null se não houver). */
	public Arvbin retornaEsq()
	{
		return esq;
	}

	/* Retorna a subárvore direita 
    (ou null se não houver). */
	public Arvbin retornaDir()
	{
		return dir;
	}

	/* Modifica o valor armazenado na raiz. */
	public void defineVal(Cluster valor)
	{
		val = valor;
	}

	/* Redefine a subárvore esquerda, apagando a antiga se houver. */
	public void defineEsq(Arvbin filho)
	{
		esq = filho;
	} 

	/* Redefine a subárvore direita, apagando a antiga se houver. */
	public void defineDir(Arvbin filho)
	{
		dir = filho;
	}

	/* Imprime o conteúdo da árvore em pré-ordem */
	public void mostra()
	{
		System.out.print("(" + val);
		if (esq != null)
			esq.mostra();
		if (dir != null)
			dir.mostra();
		System.out.print(")");
	}
	
	/* Verifica se um valor está na árvore. Se estiver, retorna um ponteiro para o
	   o nó que tem esse valor. Caso contrário, retorna null. */
	
	/* Calcula e retorna o número de nós na árvore. */
	public int contaNos()
	{
		if((esq == null) && (dir == null))
			return 1;
		
		int nosEsq = 0, nosDir = 0;
		
		if(esq != null)
			nosEsq = esq.contaNos();
		
		if(dir != null)
			nosDir = dir.contaNos();
		
		return 1 + nosEsq + nosDir;
	}
	
	/* Calcula e retorna a altura da árvore. */
	public int calculaAltura()
	{
		if((esq == null) && (dir == null))
			return 0;
		
		int altEsq = 0, altDir = 0;
		
		if(esq != null)
			altEsq = esq.calculaAltura();
		
		if(dir != null)
			altDir = dir.calculaAltura();
		
		return 1 + Math.max(altEsq, altDir);
	}
	
	
	
	public int calculaDiametro()
	{
		/* Caso base, quando é uma folha. */
		if((esq == null) && (dir == null))
		{
			return 1;
		}
		
		/* Calcula a altura das subárvores esquerda e direita do nó. */
		int alturaEsq = 0, alturaDir = 0;
		
		if(esq != null)
			alturaEsq = esq.calculaAltura();
		
		if(dir != null)
			alturaDir = dir.calculaAltura();
		
		int maxDistanciaNo = alturaEsq + alturaDir + 1;
		
		/* Nesse ponto, temos a maior distância entre dois nós da árvore
		 * que passa pelo nó corrente (this). Agora devemos calcular esse
		 * valor para as subárvores esquerda e direita, comparando depois. */		
		
		int maxDistanciaEsq = 0, maxDistanciaDir = 0;
		
		if(esq != null)
			maxDistanciaEsq = esq.calculaDiametro();
		
		if(dir != null)
			maxDistanciaDir = dir.calculaDiametro();
		
		int maxDistanciaFilhos = Math.max(maxDistanciaEsq,  maxDistanciaDir);
		
		if(maxDistanciaNo > maxDistanciaFilhos)
			return maxDistanciaNo;
		else
			return maxDistanciaFilhos;
	}
	
	/* Método que realiza a impressão de todos os caminhos da raiz para uma folha. */
	public void imprimeTodosCaminhos(Deque<Cluster> caminhos)
	{
		/* Adiciona o nó no caminho. */
		caminhos.addLast(val);

		/* Caso base: quando é uma folha. */
		if((esq == null) && (dir == null))
		{
			/* Chegou-se ao fim do caminho, portanto deve-se imprimi-lo. */
			imprimeCaminho(caminhos);
		}

		/* Caso geral: deve-se fazer a recursão para os nós esquerdo (se houver) e 
		 * para o direito (se houver). */
		
		if(esq != null)
			esq.imprimeTodosCaminhos(caminhos);

		if(dir != null)
			dir.imprimeTodosCaminhos(caminhos);
		
		/* Remove o nó corrente do caminho após as chamadas recursivas para os
		 * nós esquerdo e direito. */ 
		caminhos.removeLast();
	}

	/* Método privado auxiliar que imprime os nós contidos na estrutura caminhos. */
	private void imprimeCaminho(Deque<Cluster> caminhos)
	{
		Iterator<Cluster> iterator = caminhos.iterator();

		while(iterator.hasNext())
		{
			System.out.print(iterator.next() + " -> ");
		}

		System.out.println();
	}
}