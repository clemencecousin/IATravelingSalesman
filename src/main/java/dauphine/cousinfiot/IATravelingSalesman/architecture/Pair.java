package dauphine.cousinfiot.IATravelingSalesman.architecture;

public class Pair<T, K> {
	private T city1;
	private K city2;

	public Pair(T c1, K c2) {
		this.city1 = c1;
		this.city2 = c2;
	}

	public T getLeft() {
		return this.city1;
	}

	public K getRight() {
		return this.city2;
	}

	public boolean equals(Pair<T, K> p) {
		return ((this.city1.equals(p.getLeft()) && this.city2.equals(p.getRight()))
				|| (this.city2.equals(p.getLeft()) && this.city1.equals(p.getRight())));
	}

	@Override
	public String toString() {
		return getLeft() + " - " + getRight();
	}
}
