package naive;

public class Arvbin
{
	private Ponto val;   /* Valor armazenado na raiz. */

	/* Referências para sub-árvores. */
	private Arvbin esq, dir;
	private float somaX; // Valor usado para cálculo do novo centróide
	private float somaY; // Valor usado para cálculo do novo centróide
	private int qtdNos;  // Valor usado para cálculo do novo centróide

	private int totalNos; // Total de nós na árvore, usado para depuração do código. Foi certificado que a quantidade de nós final deverá ser 2n - 1.

	/* Construtor de árvore sem sub-ávores (dir = esq = null). É fornecido apenas o valor da raiz. */
	// Usado para geração dos pontos iniciais.
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

	/* Construtor de árvore com sub-ávores. São fornecidos
	o valor da raiz e as sub-árvores, que devem ter sido 
	construídas previamente.*/
	// Usado para geração dos clusteres e seus centróides
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

	public float getSomaY() {
		return somaY;
	}

	public int getQtdNos() {
		return qtdNos;
	}

	/* Retorna o valor armazenado na raiz. */
	public Ponto retornaVal()
	{
		return val;
	}

	/* Retorna a sub-árvore esquerda 
	   (ou null se não houver). */
	public Arvbin retornaEsq()
	{
		return esq;
	}

	/* Retorna a sub-árvore direita 
    (ou null se não houver). */
	public Arvbin retornaDir()
	{
		return dir;
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

	// Retorna o total de nós. Usado para depuração.
	public int getTotalNos() {
		return totalNos;
	}
}