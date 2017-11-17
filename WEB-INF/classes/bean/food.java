package bean;

public class food{
	int id;
	String category, item, description;
	int price;
	
	public void setid(int food_id){
		id = food_id;
	}
	
	public int getid(){
		return id;
	}
	
	public void setCategory(String cat){
		category = cat;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setItem(String it){
		item = it;
	}
	
	public String getItem(){
		return item;
	}
	
	public void setDescription(String desc){
		description = desc;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setPrice(int p){
		price = p;
	}
	
	public int getPrice(){
		return price;
	}
}


