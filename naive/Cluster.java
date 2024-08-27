package naive;

public class Cluster {
	private float x;
	private float y;
	public Cluster(float x, float y) {
		super();
		float val = x;
		val = val*100;
		val = Math.round(val);
		val = val /100;
		this.x = val;
		val = y;
		val = val*100;
		val = Math.round(val);
		val = val /100;
		this.y = val;
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
	public float distanciaPonto(Cluster b)
	{
		double parte1 = Math.pow(this.x - b.getX(), 2);
		double parte2 = Math.pow(this.y - b.getY(), 2);
		return (float)Math.pow(parte1 + parte2, 0.5);
	}
	@Override
	public boolean equals(Object o)
	{
		return this.x == ((Cluster)o).getX() && this.y == ((Cluster)o).getY();
	}
}
