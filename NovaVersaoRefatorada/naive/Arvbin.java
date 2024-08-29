package naive;
import java.util.Deque;
import java.util.Iterator;

public class Arvbin
{
	private Ponto val;   /* Valor armazenado na raiz. */

	/* Refer�ncias para sub�rvores. */
	private Arvbin esq, dir;
	private float somaX;
	private float somaY;
	private int qtdNos;

	private int totalNos;

	/* Construtor de �rvore sem sub-�vores (dir = esq = null). � fornecido apenas o valor da raiz. */
	public Arvbin(Ponto valor)
	{
		val = valor;
		esq = null;
		dir = null;
		somaX = valor.getX();
		somaY = valor.getY();
		qtdNos = 1;
		totalNos = 1;
	}

	/* Construtor de �rvore com sub-�vores. S�o fornecidos
	o valor da raiz e as sub�rvores, que devem ter sido 
	constru�das previamente.*/
	public Arvbin(Ponto valor, Arvbin arvEsq, Arvbin arvDir)
	{
		val = valor;
		esq = arvEsq;
		dir = arvDir;
		somaX = arvEsq.getSomaX() + arvDir.getSomaX();
		somaY = arvEsq.getSomaY() + arvDir.getSomaY();
		qtdNos = arvEsq.getQtdNos() + arvDir.getQtdNos();
		totalNos = esq.getTotalNos() + dir.getTotalNos() + 1;
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
	public Ponto retornaVal()
	{
		return val;
	}

	/* Retorna a sub�rvore esquerda 
	   (ou null se n�o houver). */
	public Arvbin retornaEsq()
	{
		return esq;
	}

	/* Retorna a sub�rvore direita 
    (ou null se n�o houver). */
	public Arvbin retornaDir()
	{
		return dir;
	}

	/* Modifica o valor armazenado na raiz. */
	public void defineVal(Ponto valor)
	{
		val = valor;
	}

	/* Redefine a sub�rvore esquerda, apagando a antiga se houver. */
	public void defineEsq(Arvbin filho)
	{
		esq = filho;
	} 

	/* Redefine a sub�rvore direita, apagando a antiga se houver. */
	public void defineDir(Arvbin filho)
	{
		dir = filho;
	}

	/* Imprime o conte�do da �rvore em pr�-ordem */
	public void mostra()
	{
		System.out.print("(" + val);
		if (esq != null)
			esq.mostra();
		if (dir != null)
			dir.mostra();
		System.out.print(")");
	}
	
	/* Verifica se um valor est� na �rvore. Se estiver, retorna um ponteiro para o
	   o n� que tem esse valor. Caso contr�rio, retorna null. */
	
	/* Calcula e retorna o n�mero de n�s na �rvore. */
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
	
	/* Calcula e retorna a altura da �rvore. */
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
		/* Caso base, quando � uma folha. */
		if((esq == null) && (dir == null))
		{
			return 1;
		}
		
		/* Calcula a altura das sub�rvores esquerda e direita do n�. */
		int alturaEsq = 0, alturaDir = 0;
		
		if(esq != null)
			alturaEsq = esq.calculaAltura();
		
		if(dir != null)
			alturaDir = dir.calculaAltura();
		
		int maxDistanciaNo = alturaEsq + alturaDir + 1;
		
		/* Nesse ponto, temos a maior dist�ncia entre dois n�s da �rvore
		 * que passa pelo n� corrente (this). Agora devemos calcular esse
		 * valor para as sub�rvores esquerda e direita, comparando depois. */		
		
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
	
	/* M�todo que realiza a impress�o de todos os caminhos da raiz para uma folha. */
	public void imprimeTodosCaminhos(Deque<Ponto> caminhos)
	{
		/* Adiciona o n� no caminho. */
		caminhos.addLast(val);

		/* Caso base: quando � uma folha. */
		if((esq == null) && (dir == null))
		{
			/* Chegou-se ao fim do caminho, portanto deve-se imprimi-lo. */
			imprimeCaminho(caminhos);
		}

		/* Caso geral: deve-se fazer a recurs�o para os n�s esquerdo (se houver) e 
		 * para o direito (se houver). */
		
		if(esq != null)
			esq.imprimeTodosCaminhos(caminhos);

		if(dir != null)
			dir.imprimeTodosCaminhos(caminhos);
		
		/* Remove o n� corrente do caminho ap�s as chamadas recursivas para os
		 * n�s esquerdo e direito. */ 
		caminhos.removeLast();
	}

	/* M�todo privado auxiliar que imprime os n�s contidos na estrutura caminhos. */
	private void imprimeCaminho(Deque<Ponto> caminhos)
	{
		Iterator<Ponto> iterator = caminhos.iterator();

		while(iterator.hasNext())
		{
			System.out.print(iterator.next() + " -> ");
		}

		System.out.println();
	}

	public int getTotalNos() {
		return totalNos;
	}
}