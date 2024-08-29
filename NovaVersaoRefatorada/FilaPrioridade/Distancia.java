package FilaPrioridade;
import naive.Arvbin;
import naive.Ponto;
public class Distancia {
	private Arvbin clusterA;
	private Arvbin clusterB;
	private float distancia;
	
	public Distancia(Arvbin clusterA, Arvbin clusterB)
	{
		this.clusterA = clusterA;
		this.clusterB = clusterB;
		distancia = clusterA.retornaVal().distanciaPonto(clusterB.retornaVal());
	}

	public Arvbin getClusterA() {
		return clusterA;
	}

	public void setClusterA(Arvbin clusterA) {
		this.clusterA = clusterA;
	}

	public Arvbin getClusterB() {
		return clusterB;
	}

	public void setClusterB(Arvbin clusterB) {
		this.clusterB = clusterB;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public Arvbin temPonto(Ponto c)
	{
		if(c.equals(clusterA.retornaVal()))
			return clusterB;
		else if(c.equals(clusterB.retornaVal()))
			return clusterA;
		return null;
	}
	
}
