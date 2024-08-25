package naive;
import java.util.Random;
import java.util.Scanner;
public class GeraPontos {
	public static Arvbin[] geraPontos(int n)
	{
		Random rand = new Random();
		Arvbin[] arvore = new Arvbin[n];
		for(int i = 0; i < n; i++)
		{
			int x = rand.nextInt(n) + 1;
			int y = rand.nextInt(n) + 1;
			Cluster ponto = new Cluster(x, y);
			arvore[i] = new Arvbin(ponto);
		}
		return arvore;
	}
	
	public static Arvbin[] geraPontosIguais(int n)
	{
		Arvbin[] arvore = new Arvbin[n];
		for(int i = 0; i < n; i++)
		{
			int x = 1;
			int y = 2;
			Cluster ponto = new Cluster(x, y);
			arvore[i] = new Arvbin(ponto);
		}
		/*int x = 3;
		int y = 4;
		Cluster ponto = new Cluster(x, y);
		arvore[n - 1] = new Arvbin(ponto);*/
		return arvore;
	}
	
	public static Arvbin[] criaPontos(int n){
		Scanner scan = new Scanner(System.in);
		Arvbin[] arvore = new Arvbin[n];
		for(int i = 0; i < n; i++)
		{
			int x = scan.nextInt();
			int y = scan.nextInt();
			Cluster ponto = new Cluster(x, y);
			arvore[i] = new Arvbin(ponto);
		}
		return arvore;
	}
}