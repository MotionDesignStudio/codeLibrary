
/* A map of movie critics and their ratings of a small set of movies */

import java.util.*;

// This is needed a POJO class instance two return different data types
final class getIndexOfDuplicates
{ 
	// Instance Variables
	public ArrayList <Character> mainList;
	// I make a copy here so I can send later to the mehtod presentData 
	// this decreases duplicate calls
	public ArrayList <Character> copyOfDuplicatesList;
	// Duplicate list cast to set to remove duplicates
	public ArrayList <Character> removeDuplicateSet;
	// Dictionary for each unique element
	//public Set<Character> uniqueElemDictionary = new HashSet<Character>();
	
	//public Dictionary uniqueElemDictionary = new Hashtable();
	
	public HashMap<Character, ArrayList <Integer>> uniqueElemDictionary;
	
	public getIndexOfDuplicates ( HashMap<Character, ArrayList <Integer>> uniqueElemDictionary, ArrayList <Character> mainList )	
	{
		this.mainList = mainList;
		this.uniqueElemDictionary = uniqueElemDictionary;
	}
	
}



// This must match the same name as the file without extension in Java
class JavaFindNextDuplicate
{
	
	// Instance Variables
	ArrayList <Character> myList;
	
	// Constructor Declaration of Class 
	public JavaFindNextDuplicate ( ArrayList <Character> myList )
	{
		this.myList = myList;
	}
	
	// Init a set to store all the movies 
	public ArrayList <Character> theDuplicates ()
	{
		ArrayList <Character> mainList = new ArrayList<>();
		//mainList = this.myList;
		
		// Must duplicate or I will destroy the myList for other methods because I use remove
		for ( Character x : this.myList )
		{
			mainList.add( x );	
		}
		
		//System.out.printf("ZZZ mainList :: %s%n", mainList.get(0) );
		
		// List of duplicates
		ArrayList <Character> duplicatesList = new ArrayList<>();
		
		// Test me character
		Character testMe;
		
		while ( mainList.size() != 0 )
		{
			testMe = mainList.remove(0);

			for ( int i = 0; i <  mainList.size(); i++ )
			{
				
				if ( Character.compare( testMe, mainList.get( i )  ) == 0 ) 
				{
					duplicatesList.add( testMe );
					// Remove the found object from the mainList
					mainList.remove ( i );
					// Exit for loop or it will continue to test positive for multiple matches
					break;
				} 
			}
		}		
		
		return duplicatesList;
	}
	
	
	public getIndexOfDuplicates getIndexOfDuplicates2 ()
	{
		ArrayList <Character> mainList =  this.myList;
		//mainList = this.myList;
		//uniqueElemDictionary, copyOfDuplicatesList;
		// I make a copy here so I can send later to the mehtod presentData 
		// this decreases duplicate calls
		ArrayList <Character> copyOfDuplicatesList = new ArrayList <Character>();
		// Calling method theDuplicates and duplicating its return value
		for ( Character x : theDuplicates() )
		{
			copyOfDuplicatesList.add( x );
		}
		
		//System.out.printf("AAA copyOfDuplicatesList :: %s%n", copyOfDuplicatesList );
		
		// Duplicate list cast to set to remove duplicates
		Set <Character> removeDuplicateSet = new HashSet <Character>( copyOfDuplicatesList );
		
		//System.out.printf("BBB removeDuplicateSet :: %s%n", removeDuplicateSet );

		
		// HashMap for each unique element
		HashMap<Character, ArrayList <Integer>> uniqueElemDictionary = new HashMap<Character, ArrayList <Integer>>();
		
		
		//System.out.printf("XXX mainList :: %s%n", mainList );
		int counter = -1;
		for ( Character x : mainList )
		{
			counter++;
			//System.out.printf("YYY mainList :: %s%n", x );
			//uniqueElemDictionary.put( x, new ArrayList <Integer>()  );
			uniqueElemDictionary.putIfAbsent( x, new ArrayList <Integer>()  );
			//ArrayList <Character> whyNot = new ArrayList <Character>();
				
			//uniqueElemDictionary.put( x, whyNot );	
			
			for ( Character y : removeDuplicateSet)
			{
				if ( Character.compare( y, x ) == 0 ) 
				{
					uniqueElemDictionary.get(x).add( counter );
					//System.out.printf("y , uniqueElemDictionary ::  %s, %s%n", y, uniqueElemDictionary.get(x) );
					//uniqueElemDictionary[x];
				}
				
			}
			
		}
			
		System.out.printf("XXX uniqueElemDictionary :: %s%n", uniqueElemDictionary );
		return new getIndexOfDuplicates( uniqueElemDictionary, copyOfDuplicatesList );
	}
	

	
	
	
	public static void main ( String args[])
	{
		// Test Case list data type ArrayList
		ArrayList <Character> mySexyList = new ArrayList<> ();
		mySexyList.addAll (Arrays.asList ('c', 'z', 'd', 'r', 'd', 'q', 't', 'p', 'd', 'a', 't', 'd', 'z' ));
		
		//System.out.printf("mySexyList :: %s%n", mySexyList );
		System.out.printf("%s%n", mySexyList );
		
		JavaFindNextDuplicate testing = new JavaFindNextDuplicate ( mySexyList );
		System.out.printf("%s%n", testing.theDuplicates() );
		
		System.out.printf("%s%n", testing.getIndexOfDuplicates2() );

		//System.out.printf("%s%n", testing.discardDuplicateSet() );
	}
	
	
}




