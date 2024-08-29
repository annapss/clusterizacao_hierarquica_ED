package naive;
import java.util.Random;
import java.util.Scanner;
public class GeraPontos {

	// Método usado para gerar N pontos aleatórios com suas coordenadas variando de 1 a N.
	public static Arvbin[] geraPontos(int n)
	{
		Random rand = new Random();
		Arvbin[] arvore = new Arvbin[n];
		for(int i = 0; i < n; i++)
		{
			int x = rand.nextInt(n) + 1;
			int y = rand.nextInt(n) + 1;
			Ponto ponto = new Ponto(x, y);
			arvore[i] = new Arvbin(ponto);
		}
		return arvore;
	}
	
	// Método usado para gerar N pontos IGUAIS.
	public static Arvbin[] geraPontosIguais(int n)
	{
		Arvbin[] arvore = new Arvbin[n];
		for(int i = 0; i < n; i++)
		{
			int x = 1;
			int y = 2;
			Ponto ponto = new Ponto(x, y);
			arvore[i] = new Arvbin(ponto);
		}
		return arvore;
	}
	
	// Método usado para gerar N pontos pré-definidos pelo usuário.
	public static Arvbin[] criaPontos(int n){
		Scanner scan = new Scanner(System.in);
		Arvbin[] arvore = new Arvbin[n];
		for(int i = 0; i < n; i++)
		{
			int x = scan.nextInt();
			int y = scan.nextInt();
			Ponto ponto = new Ponto(x, y);
			arvore[i] = new Arvbin(ponto);
		}
		return arvore;
	}
}