package naive;
import java.util.Random;
public class GeraPontos {
	public static Arvbin<Ponto>[] geraPontos(int n)
	{
		Random rand = new Random();
		Arvbin<Ponto>[] arvore = new Arvbin[n];
		for(int i = 0; i < n; i++)
		{
			int x = rand.nextInt(n) + 1;
			int y = rand.nextInt(n) + 1;
			Ponto ponto = new Ponto(x, y);
			arvore[i] = new Arvbin<>(ponto);
		}
		return arvore;
	}
}