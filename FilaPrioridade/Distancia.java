package filaPrioridade;
import naive.Ponto;
public class Distancia {
	private Ponto a;
	private Ponto b;
	private float distancia;
	
	public Distancia(Ponto a, Ponto b)
	{
		this.a = a;
		this.b = b;
		distancia = a.distanciaPonto(b);
	}

	public Ponto getA() {
		return a;
	}

	public void setA(Ponto a) {
		this.a = a;
	}

	public Ponto getB() {
		return b;
	}

	public void setB(Ponto b) {
		this.b = b;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
}
