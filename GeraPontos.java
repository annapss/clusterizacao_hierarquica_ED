import java.util.Random;
public class GeraPontos {
	public static Ponto[] geraPontos(int n)
	{
		Random rand = new Random();
		Ponto pontos[] = new Ponto[n];
		for(int i = 0; i < n; i++)
		{
			int x = rand.nextInt(n) + 1;
			int y = rand.nextInt(n) + 1;
			Ponto ponto = new Ponto(x, y);
			pontos[i] = ponto;
		}
		return pontos;
	}
}
