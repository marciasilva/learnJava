package prototype;

import prototype.model.Movie;

public class PrototypeDemo {

	public static void main(String[] args) {

		Registry registry = new Registry();
		Movie movie = (Movie) registry.createItem("Movie");
		movie.setTitle("Before you");

		System.out.println(movie);
		System.out.println(movie.getTitle());
		
		Movie anotherMovie = (Movie) registry.createItem("Movie");
		anotherMovie.setTitle("Oceans 8");
		
		System.out.println(anotherMovie);
		System.out.println(anotherMovie.getTitle());
	}

}
