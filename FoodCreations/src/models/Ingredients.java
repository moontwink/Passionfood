package models;


public class Ingredients {
 

	private int id;
    private String name;
	private String disease;
    private String unit;
 
    public Ingredients(){}
 
    public Ingredients(String name, String disease, String unit) {
        super();
        this.name = name;
		this.disease = disease;
        this.unit = unit;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Ingredients [id=" + id + ", name=" + name + ", disease=" + disease + ", unit=" + unit
                + "]";
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}