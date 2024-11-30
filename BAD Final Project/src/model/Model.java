package model;

abstract public class Model {

	public boolean exists() {
		return this.getId() != null;
	}

	abstract public String getIdPrefix();
	
	abstract public String getId();
	
	abstract public void setId(String id);

}
