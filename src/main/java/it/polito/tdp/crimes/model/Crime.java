package it.polito.tdp.crimes.model;

public class Crime {

	private String offense_type_1;
	private String offense_type_2;
	private int peso;
	
	public Crime(String offense_type_1, String offense_type_2, int peso) {
		this.offense_type_1 = offense_type_1;
		this.offense_type_2 = offense_type_2;
		this.peso = peso;
	}

	public String getOffense_type_1() {
		return offense_type_1;
	}

	public String getOffense_type_2() {
		return offense_type_2;
	}

	public int getPeso() {
		return peso;
	}

	public void setOffense_type_1(String offense_type_1) {
		this.offense_type_1 = offense_type_1;
	}

	public void setOffense_type_2(String offense_type_2) {
		this.offense_type_2 = offense_type_2;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((offense_type_1 == null) ? 0 : offense_type_1.hashCode());
		result = prime * result + ((offense_type_2 == null) ? 0 : offense_type_2.hashCode());
		result = prime * result + peso;
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
		Crime other = (Crime) obj;
		if (offense_type_1 == null) {
			if (other.offense_type_1 != null)
				return false;
		} else if (!offense_type_1.equals(other.offense_type_1))
			return false;
		if (offense_type_2 == null) {
			if (other.offense_type_2 != null)
				return false;
		} else if (!offense_type_2.equals(other.offense_type_2))
			return false;
		if (peso != other.peso)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return offense_type_1 + " " + offense_type_2 + " " + peso;
	}
	
	
	
}
