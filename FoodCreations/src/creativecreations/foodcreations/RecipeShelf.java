package creativecreations.foodcreations;

import java.util.ArrayList;
import java.util.List;

import models.Recipe;

import database.JonDAO;

import android.app.ListActivity;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeShelf extends ListActivity {
	JonDAO jdao;
	private List<String> names;
	private List<String> desc;
    private List<String> ingr;
    private List<String> procs;
	public List<Integer> pics;

	@Override
	public void onCreate(Bundle icicle) {
		jdao = new JonDAO(this);
		jdao.open();
		
		names = new ArrayList<String>();
		pics = new ArrayList<Integer>();
		desc = new ArrayList<String>();
		ingr = new ArrayList<String>();
		procs = new ArrayList<String>();
		
		List<Recipe> nvm = jdao.getAllRecipes();
		
		if(nvm.isEmpty()){
			jdao.createRecipe("Balsamic Roasted Pork", " ", R.drawable.roastedporkloin,
					"-2 tablespoons steak seasoning rub<1/2 cup balsamic vinegar<1/2 cup olive oil<2 pounds bonless pork loin roast",
					"Dissolve steak seasoning in balsamic vinegar, then stir in olive oil. Place pork into a resealable plastic bag and pour marinade overtop. Squeeze out air and seal bag; marinate 2 hours to overnight.<" +
					"Preheat oven to 350 degrees F (175 degrees C).<" +
					"Place pork into a glass baking dish along with marinade. Bake in preheated oven, basting occasionally until the pork reaches an internal temperature of 145 degrees F (65 degrees C), about 1 hour. Let the roast rest for 10 minutes before slicing and serving.");				
			jdao.createRecipe("Best Chocolate Chip Cookies", " ", R.drawable.chocolatechipcookies,
					"-1 cup softened butter<1 cup white sugar<1 cup packed brown sugar<2 eggs,2 teaspoons vanilla extract<3 cups all-purpose flour<1 teaspoon baking soda<2 teaspoons hot water<1/2 teaspoon salt<2 cups semisweet chocolate chips<1 cup chopped walnuts",
					"Preheat oven to 350 degrees F (175 degrees C).<" +
					"Cream together the butter, white sugar, and brown sugar until smooth. Beat in the eggs one at a time, then stir in the vanilla. Dissolve baking soda in hot water. Add to batter along with salt. Stir in flour, chocolate chips, and nuts. Drop by large spoonfuls onto ungreased pans.<" +
					"Bake for about 10 minutes in the preheated oven, or until edges are nicely browned.");
			jdao.createRecipe("Delicious Ham and Potato Soup", " ", R.drawable.potatohamsoup,
					"-3 1/2 cups peeled and diced potatoes<1/3 cup diced celery<1/3 cup finely chopped onion<3/4 cup diced cooked ham<3 1/4 cups water<2 tablespoons chicken bouillon granules<1/2 teaspoon salt, or to taste<1 teaspoon ground white or black pepper, or to taste<5 tablespoons butter<5 tablespoons all-purpose flour<2 cups milk",
					"Combine the potatoes, celery, onion, ham and water in a stockpot. Bring to a boil, then cook over medium heat until potatoes are tender, about 10 to 15 minutes. Stir in the chicken bouillon, salt and pepper.<" +
					"In a separate saucepan, melt butter over medium-low heat. Whisk in flour with a fork, and cook, stirring constantly until thick, about 1 minute. Slowly stir in milk as not to allow lumps to form until all of the milk has been added. Continue stirring over medium-low heat until thick, 4 to 5 minutes.<" +
					"Stir the milk mixture into the stockpot, and cook soup until heated through. Serve immediately.");
			jdao.createRecipe("Grandma's Green Bean Casserole", " ", R.drawable.greenbeancasserole,
					"-2 tablespoons butter<2 tablespoons all-purpose flour<1 teaspoon salt<1 teaspoon white sugar<1/4 cup onion, diced<1 cup sour cream<3 (14.5 ounce) cans French style green beans, drained<2 cups shredded Cheddar cheese<1/2 cup crumbled buttery round crackers<1 tablespoon butter, melted",
					"Preheat oven to 350 degrees F (175 degrees C).<" +
					"Melt 2 tablespoons butter in a large skillet over medium heat. Stir in flour until smooth, and cook for one minute. Stir in the salt, sugar, onion, and sour cream. Add green beans, and stir to coat.<" +
					"Transfer the mixture to a 2 1/2 quart casserole dish. Spread shredded cheese over the top. In a small bowl, toss together cracker crumbs and remaining butter, and sprinkle over the cheese.<" +
					"Bake for 30 minutes in the preheated oven, or until the top is golden and cheese is bubbly.");
			jdao.createRecipe("Homemade Mac and Cheese", " ", R.drawable.mac_cheese,
					"-8 ounces uncooked elbow macaroni<2 cups shredded sharp Cheddar cheese<1/2 cup grated Parmesan cheese<3 cups milk<1/4 cup butter",
					"Cook macaroni according to the package directions. Drain.<" +
					"In a saucepan, melt butter or margarine over medium heat. Stir in enough flour to make a roux. Add milk to roux slowly, stirring constantly. Stir in cheeses, and cook over low heat until cheese is melted and the sauce is a little thick. Put macaroni in large casserole dish, and pour sauce over macaroni. Stir well.<" +
					"Melt butter or margarine in a skillet over medium heat. Add breadcrumbs and brown. Spread over the macaroni and cheese to cover. Sprinkle with a little paprika.<" +
					"Bake at 350 degrees F (175 degrees C) for 30 minutes. Serve.");
			jdao.createRecipe("Easy Chicken and Corn Chowder", " ", R.drawable.chickenandcornchowder,
					"-1/2 cup butter<1 small carrot, finely diced<1 stalk celery, diced<1 small onion, finely diced<1 clove garlic, minced<1/2 cup all-purpose flour<1 1/2 cups white corn kernels<1 1/2 cups yellow corn kernels<4 russet potatoes, diced<2 cooked rotisserie chicken breast halves, shredded<4 cups chicken stock, divided<2 1/2 cups half-and-half<1 pinch nutmeg<salt and ground black pepper to taste",
					"Melt butter in a large saucepan over medium heat; cook and stir carrot, celery, onion, and garlic into hot butter until slightly softened, about 2 minutes. Stir flour into mixture to make a paste; cook until lightly browned and flour gives off a slightly toasted smell, about 5 minutes. Watch carefully, flour burns easily. Remove saucepan from heat and set aside to cool, about 15 minutes.<" +
					"Stir white and yellow corn, potatoes, chicken, and 3 cups of chicken stock in a large soup pot over medium heat. Whisk remaining 1 cup chicken stock into vegetables and flour mixture until thoroughly combined. Stir mixture into ingredients in soup pot. Bring to a simmer, stirring constantly, until thick, about 5 minutes.<" +
					"Stir half-and-half, nutmeg, salt, and black pepper into chowder. Bring back to a boil, reduce heat to low, and simmer until potatoes are tender, about 20 minutes.");
			jdao.createRecipe("Stuffed Pork Chops I", " ", R.drawable.stuffedporkchops,
					"-2 tablespoons vegetable oil<4 thick cut pork chops<3 cups day-old bread cubes<1/4 cup butter, melted<1/4 cup chicken broth<2 tablespoons chopped celery<2 tablespoons chopped onion<1/4 teaspoon poultry seasoning<1 (10.75 ounce) cancondensed cream of mushroom soup<1/3 cup water",
					"Preheat oven to 350 degrees F (175 degrees C).<" +
					"In a skillet, heat the oil and brown the pork chops. Place the pork chops in a baking dish.<" +
					"In a bowl, toss the bread cubes, melted butter, chicken broth, celery, onion, and poultry seasoning together. Put heaping mounds of the bread crumb mixture onto the pork chops.<" +
					"Combine the cream of mushroom soup with the water, and pour this mixture over the stuffing and pork chops.<" +
					"Cover and bake for 30 minutes.<" +
					"Uncover and continue baking for 10 minutes longer or until juices run clear. The meat thermometer should read 145 degrees F (63 degrees C).");
			jdao.createRecipe("Mushroom Slow Cooker Roast Beef", " ", R.drawable.slowcookerroastbeef,
					"-1 pound sliced fresh mushrooms<1 (4 pound) standing beef rib roast<1 (1.25 ounce) envelope onion soup mix<1 (12 fluid ounce) bottle beer",
					"Place the mushrooms in the bottom of a slow cooker; set the roast atop the mushrooms; sprinkle the onion soup mix over the beef and pour the beer over everything; season with black pepper. Set slow cooker to LOW; cook 9 to 10 hours until the meat is easily pulled apart with a fork.");
			jdao.createRecipe("Oven Roasted Parmesan Potatoes", " ", R.drawable.roastedparmesanpotatoes,
					"-cooking spray<1 teaspoon vegetable oil, or as needed<2 tablespoons freshly grated Parmesan cheese1/2 teaspoon salt<1/2 teaspoon garlic powder<1/2 teaspoon paprika<1/4 teaspoon ground black pepper<2 pounds red potatoes, halved<1 tablespoon vegetable oil, or as needed<1/4 cup sour cream (optional)",
					"Preheat oven to 400 degrees F (200 degrees C). Spray a 9x13-inch baking pan or cast iron skillet with cooking spray; pour in about 1 teaspoon vegetable oil to coat the bottom." +
					"Mix Parmesan cheese, salt, garlic powder, paprika, and black pepper together in a bowl.<" +
					"Blot the cut-side of potatoes with a paper towel to remove any moisture. Place potatoes in a bowl and drizzle with 1 tablespoon vegetable oil; toss until potatoes are lightly coated. Sprinkle potatoes with Parmesan cheese mixture; toss to coat. Arrange potatoes, cut-side down, onto the prepared baking pan.<" +
					"Bake in the preheated oven for 15 to 20 minutes. Turn potatoes to cut-side up; continue baking until golden and crispy, about 15 to 20 more minutes. Serve with sour cream.");
			jdao.createRecipe("Rusty Chicken Thighs", " ", R.drawable.chickenthighs,
					"-1 clove garlic, sliced, or more to taste<2 teaspoons Asian chile pepper sauce (such as sambal oelek), or more to taste<1 1/2 tablespoons maple syrup<2 tablespoons soy sauce<2 tablespoons mayonnaise<3 tablespoons rice vinegar<salt and freshly ground black pepper to taste<2 pounds skinless, boneless chicken thighs<1 lime, cut into 8 wedges",
					"Mash garlic to a paste with a mortar and pestle. Mix chile pepper sauce, maple syrup, soy sauce, mayonnaise, and rice vinegar into garlic until marinade is thoroughly combined.<" +
					"Transfer chicken thighs to a large flat container (such as a baking dish) and pour marinade over chicken; stir until chicken is coated. Cover dish with plastic wrap and refrigerate about 3 hours; if preferred, let stand about 30 minutes at room temperature. Unwrap dish and sprinkle with salt.<" +
					"Preheat charcoal grill to high heat.<" +
					"Place chicken thighs onto the hot grill with smooth sides down. Cook until chicken shows grill marks, about 3 minutes. Turn chicken over and cook until other side shows grill marks, about 5 minutes. Continue to cook, moving them occasionally and turning over every 2 minutes, until meat is no longer pink inside and the thighs are golden brown, 10 to 12 minutes.<" +
					"Transfer chicken to a platter, let rest for 5 minutes, and serve garnished with lime wedges.");
			jdao.createRecipe("Baked French Toast", " ", R.drawable.frenchtoast,
					"-1 (1 pound) loaf French bread, cut diagonally in 1 inch slices<8 eggs<2 cups milk<1 1/2 cups half-and-half cream<2 teaspoons vanilla extract",
					"Butter a 9x13 inch baking dish. Arrange the slices of bread in the bottom. In a large bowl, beat together eggs, milk, cream, vanilla and cinnamon. Pour over bread slices, cover, and refrigerate overnight.<" +
					"The next morning, preheat oven to 350 degrees F (175 degrees C). In a small saucepan, combine butter, brown sugar and corn syrup; heat until bubbling. Pour over bread and egg mixture.<" +
					"Bake in preheated oven, uncovered, for 40 minutes.");
			jdao.createRecipe("Country-Style Steak", " ", R.drawable.countrystylesteak,
					"-1 cup all-purpose flour<1 teaspoon cracked black pepper<1 teaspoon seasoned salt<1/4 teaspoon garlic powder(optional)<1 pound beef cube steaks<1/2 cup olive oil<2 cups beef broth",
					"Preheat oven to 350 degrees F (175 degrees C).<" +
					"Mix the flour, black pepper, seasoned salt, and garlic powder together in a shallow bowl, and coat the cube steaks thoroughly with the flour mixture, patting the flour onto the steaks to get a good coating. Retain 3 tablespoons of seasoned flour.<" +
					"Heat the olive oil in a skillet over medium heat, and pan-fry the cube steaks until golden brown on both sides, about 5 minutes per side. Place the steaks into a 9x12-inch baking dish. Whisk the retained seasoned flour into the beef broth until smooth, and pour the broth over the steaks. Cover the dish with aluminum foil.<" +
					"Bake in the preheated oven until the meat is tender and the gravy has thickened, about 2 hours.");
			jdao.createRecipe("Brown Sugar Smokies", " ", R.drawable.sugarsmokies,
					"-1 pound bacon<1 (16 ounce) package little smokie sausages<1 cup brown sugar, or to taste",
					"Preheat oven to 350 degrees F (175 degrees C).<" +
					"Cut bacon into thirds and wrap each strip around a little sausage. Place the wrapped sausages on wooden skewers, several to a skewer. Arrange the skewers on a baking sheet and sprinkle them liberally with brown sugar.<" +
					"Bake until bacon is crisp and the brown sugar melted.");
			jdao.createRecipe("Buffalo Chicken Dip", " ", R.drawable.buffalochickendip,
					"-2 (10 ounce) cans chunk chicken, drained<2 (8 ounce) packages cream cheese, softened<1 cup Ranch dressing<3/4 cup pepper sauce (such as Frank's Red Hot®)<1 1/2 cups shredded Cheddar cheese<1 bunch celery, cleaned and cut into 4 inch pieces<1 (8 ounce) box chicken-flavored crackers",
					"Heat chicken and hot sauce in a skillet over medium heat, until heated through. Stir in cream cheese and ranch dressing. Cook, stirring until well blended and warm. Mix in half of the shredded cheese, and transfer the mixture to a slow cooker. Sprinkle the remaining cheese over the top, cover, and cook on Low setting until hot and bubbly. Serve with celery sticks and crackers.");
		}
		
		List<Recipe> allrecipes = jdao.getAllRecipes();
		
		for(Recipe r: allrecipes){
			names.add(r.getName());
			pics.add(r.getpicID());
			desc.add(r.getDescription());
			ingr.add(r.getIngredients());
			procs.add(r.getProcedures());
		}

		
		
		//System.out.println("picZZs = "+ pics.get(3));
		
		
		super.onCreate(icicle);
		setListAdapter(new IconicAdapter());
		
		
	}

	class IconicAdapter extends ArrayAdapter<String> {
		
	
		IconicAdapter() {
			super(RecipeShelf.this, R.layout.icon_text_layout, R.id.label,
					names);
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final int i = position;
			final String recipe_name = names.get(i);
			final String recipe_procs = procs.get(i);
			final int recipe_picid = pics.get(i);
			final String recipe_ingr = ingr.get(i);
			
			
			
			
			
			
			View row = super.getView(position, convertView, parent);
			ImageView icon = (ImageView) row.findViewById(R.id.icon);
			icon.setImageResource(pics.get(position));
			
			final TextView label = (TextView) row.findViewById(R.id.label);
			
			String refinedDesc = desc.get(position);
			if(refinedDesc.length() > 50){
			refinedDesc = refinedDesc.substring(0, 50);
			refinedDesc = refinedDesc.concat("...");
			}
			label.setText(names.get(position) + "\n" + refinedDesc);
			
			row.setOnClickListener(new View.OnClickListener() {
				
				 @Override
		            public void onClick(View view) {
					 Intent intent = new Intent(RecipeShelf.this, RecipeSpecific.class);
					 System.out.println("i = " + i);
					 System.out.println("doubleChck recipe = " + names.get(i) + ": picId" + pics.get(i));
					 intent.putExtra("rname",  recipe_name);
					 intent.putExtra("rprocs", recipe_procs);
					 intent.putExtra("rpicid", recipe_picid);
					 intent.putExtra("ringr", recipe_ingr);
					 startActivity(intent);
		            }
		        });
			
			return (row);
		}
	}
}