package models;


public class Recipe {
 
    private int id,picid;
  	private String name;
    private String description;
    private String ingredients;
    private String procedures;


	public Recipe(){}
 
    public Recipe(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return name;
    }
    
    public int toInteger() {
        return picid;
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

  	public String getDescription() {
  		return description;
  	}

  	public void setDescription(String description) {
  		this.description = description;
  	}

	public void setpicID(int picid) {
		this.picid = picid;
	}
	
	public int getpicID() {
		return  picid;
	}

    public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}
}