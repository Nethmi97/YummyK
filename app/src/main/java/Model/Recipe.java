package Model;

public class Recipe {
    private String recipeid;
    private String recipeName, recipeIngredients, recipeMethod, recipePortion, recipeImg;

    public Recipe(){}

    public Recipe(String recipeid, String recipeName, String recipeIngredients, String recipeMethod, String recipePortion, String recipeImg) {
        this.recipeid = recipeid;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeMethod = recipeMethod;
        this.recipePortion = recipePortion;
        this.recipeImg = recipeImg;
    }

    public String getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(String recipeid) {
        this.recipeid = recipeid;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeMethod() {
        return recipeMethod;
    }

    public void setRecipeMethod(String recipeMethod) {
        this.recipeMethod = recipeMethod;
    }

    public String getRecipePortion() {
        return recipePortion;
    }

    public void setRecipePortion(String recipePortion) {
        this.recipePortion = recipePortion;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }
}
