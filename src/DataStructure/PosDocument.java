package DataStructure;

public class PosDocument {
	private int pos;
	private String document;
	
	public PosDocument(int pos, String d) {
		this.pos = pos;
		this.document = d;
	}

	@Override
	public String toString() {
		return "PosDocument [pos=" + pos + ", document=" + document + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + pos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosDocument other = (PosDocument) obj;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		if (pos != other.pos)
			return false;
		return true;
	}
	
}