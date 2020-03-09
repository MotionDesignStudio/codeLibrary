
/* A map of movie critics and their ratings of a small set of movies */

import java.util.*;

class JavaSearchForDuplicatesInList
{
	
	// Instance Variables
	Map<String, Map<String, Double>> myCritics;
	
	// Constructor Declaration of Class 
	public JavaSearchForDuplicatesInList ( Map myCritics )
	{
		this.myCritics = myCritics;
	}
	
	// Init a set to store all the movies 
	public Set buildSetMovies ()
	{
		Set<String> movieSet = new HashSet<String>();
		
		this.myCritics.forEach (( userName, theirMovies) ->{
			
			theirMovies.forEach (( movieName, movieScore ) -> {
				movieSet.add( movieName );
			});
		});				
		return movieSet;
	}
	
	
	
	public Set searchForDuplicates ()
	{
		// Temporary store movies a set
		Set<String> movieSet = new HashSet<String>();
		movieSet = buildSetMovies ();	
		
		// Possible Duplicates
		Set<String> duplicateMovieSet = new HashSet<String>();
		
		// Creater iterator
		Iterator<String> itr = movieSet.iterator();
		
		while (itr.hasNext())
		{
			String lowerCaseAndCheckMe =  itr.next();
			
			// This will remove one string or movie from the movieSet each itteration
			itr.remove ( );
			
			// itr2 will iterate over the shorter movieset each time
			for ( Iterator<String> itr2 = movieSet.iterator(); itr2.hasNext(); )
			{				
				String checkThisMovie = itr2.next();
				if ( lowerCaseAndCheckMe.equalsIgnoreCase( checkThisMovie ) )
				{
					duplicateMovieSet.add (checkThisMovie);
				}
				
			}
			
		}
		
		return duplicateMovieSet;
	}
	
	
	
	public Set discardDuplicateSet ()
	{
		/*discard duplicates in a set
		* call the method that creates the initial set of movies
		*/
		Set<String> uniqueMovieSet = new HashSet<String>();
		uniqueMovieSet = buildSetMovies ();
			
		// Creater iterator for set from class searchForDuplicates
		Iterator<String> itr = searchForDuplicates().iterator();
		
		while ( itr.hasNext() )
		{
			//System.out.printf("%s%n", itr.next() );
			// Remove the object
			uniqueMovieSet.remove ( itr.next() );
			// This will remove one string or movie from the movieSet each itteration
			itr.remove ( );
		}
		
		return uniqueMovieSet;
	}
	
	
	
	public static void main ( String args[])
	{
	// A nested Map of movie critics and their ratings of a small set of movies
		
		Map<String, Map<String, Double>> critics = new HashMap<>(
			Map.of("Lisa Rose", Map.of("Lady in the Water" , 2.5, "Snakes on a Plane" , 3.5, "Just My Luck" , 3.0, "Superman Returns" , 3.5, "You, me and Dupree" , 2.5, "The Night Listener" , 3.0))
		);
		critics.put ("Gene Seymour", Map.of("Lady in the Water" , 2.5, "Snakes on a Plane" , 3.5, "Just My Luck" , 3.0, "Superman Returns" , 3.5, "You, me and Dupree" , 2.5, "The Night Listener" , 3.0) );
		critics.put ("Michael Phillips", Map.of( "Lady in the Water" , 2.5, "Snakes on a Plane" , 3.0, "Superman Returns" , 3.5, "The Night Listener" , 4.0 ) );
		critics.put ("Claudia Puig", Map.of( "Snakes on A Plane" , 3.5, "Just My Luck" , 3.0, "The Night Listener" , 4.5, "Superman Returns" , 4.0, "You, me and Dupree" , 2.5 ) ); 
		critics.put ("Mick LaSalle", Map.of("Lady in the Water", 3.0, "Snakes on a Plane", 4.0, "Just My Luck", 2.0, "Superman Returns", 3.0, "The Night Listener", 3.0, "You, me and Dupree", 2.0 ) );
		critics.put ("Jack Matthews", Map.of("Lady in the Water", 3.0, "Snakes on a Plane", 4.0, "The Night Listener", 3.0, "Superman Returns", 5.0, "You, me and Dupree", 3.5 ) ); 
		critics.put ("Toby", Map.of("Snakes on a Plane",4.5,"You, Me and Dupree",1.0,"Superman Returns",4.0 ) );
		
		JavaSearchForDuplicatesInList testing = new JavaSearchForDuplicatesInList ( critics );
		//System.out.printf("%s%n", testing.buildSetMovies() );
		
		//System.out.printf("%s%n", testing.searchForDuplicates() );

		System.out.printf("%s%n", testing.discardDuplicateSet() );
	}
	
	
}




