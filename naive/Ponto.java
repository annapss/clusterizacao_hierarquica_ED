package naive;

public class Ponto {

	private float x; // Coordenada X do Ponto
	private float y; // Coordenada Y do Ponto

	public Ponto(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	// GETS //
	public float getX() {return x;}
	public float getY() {return y;}
	// ---- //

	// TO STRING -> usado para depuração do código //
	@Override
	public String toString() {
		return "Ponto [x=" + x + ", y=" + y + "]";
	}
	// ------------------------------------------- //

	// Calcula a distância entre dois pontos
	public float distanciaPonto(Ponto b)
	{
		double parte1 = Math.pow(this.x - b.getX(), 2);
		double parte2 = Math.pow(this.y - b.getY(), 2);
		return (float)Math.pow(parte1 + parte2, 0.5);
	}

	// Verificação se um ponto é idêntico a outro
	@Override
	public boolean equals(Object o)
	{
		return this.x == ((Ponto)o).getX() && this.y == ((Ponto)o).getY();
	}
}
