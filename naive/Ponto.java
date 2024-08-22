package naive;

public class Ponto {
	private float x;
	private float y;
	public Ponto(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Ponto [x=" + x + ", y=" + y + "]";
	}
	public float distanciaPonto(Ponto b)
	{
		double parte1 = Math.pow(this.x - b.getX(), 2);
		double parte2 = Math.pow(this.y - b.getY(), 2);
		return (float)Math.pow(parte1 + parte2, 0.5);
	}
}
