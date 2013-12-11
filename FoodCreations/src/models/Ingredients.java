package models;


public class Ingredients {
 

	private int id;
    private String name;
	private String amt;
    private String unit;
 
    public Ingredients(){}
 
    public Ingredients(String name, String amt, String unit) {
        super();
        this.name = name;
		this.amt = amt;
        this.unit = unit;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Ingredients [id=" + id + ", name=" + name + ", amt=" + amt + ", unit=" + unit
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

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}