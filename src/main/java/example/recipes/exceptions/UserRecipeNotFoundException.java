package example.recipes.exceptions;

public class UserRecipeNotFoundException extends RuntimeException {

    public UserRecipeNotFoundException(String message) {
        super(message);
    }
}
