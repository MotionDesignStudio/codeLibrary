
/* A map of movie critics and their ratings of a small set of movies */

import java.util.*;

// This is needed a POJO class instance two returned different data types
final class getIndexOfDuplicates
{ 
	ArrayList <Character> copyOfDuplicatesList = new ArrayList <Character>();
	// Calling method theDuplicates and duplicating its return value
	public LinkedHashMap<Character, ArrayList <String>> uniqueElemDictionary;
	
	public getIndexOfDuplicates ( LinkedHashMap<Character, ArrayList <String>> uniqueElemDictionary, ArrayList <Character> copyOfDuplicatesList )	
	{
		this.uniqueElemDictionary = uniqueElemDictionary;
		this.copyOfDuplicatesList = copyOfDuplicatesList;
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
		//Display original list 
		//System.out.printf("%s%n", myList );
		
		ArrayList <Character> mainList = new ArrayList<>();
		
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
		//ArrayList <Character> theDuplicates2 =   theDuplicates();
		// I make a copy here so I can send later to the mehtod presentData 
		// this decreases duplicate calls
		ArrayList <Character> copyOfDuplicatesList = new ArrayList <Character>();
		// Calling method theDuplicates and duplicating its return value
		for ( Character x : theDuplicates() )
		{
			copyOfDuplicatesList.add( x );
		}
		
		// Duplicate list cast to set to remove duplicates
		Set <Character> removeDuplicateSet = new HashSet <Character>( copyOfDuplicatesList );
			
		// HashMap for each unique element
		LinkedHashMap<Character, ArrayList <String>> uniqueElemDictionary = new LinkedHashMap<Character, ArrayList <String>>();

		int counter = -1;
		for ( Character x : mainList )
		{
			counter++;
			// putIfAbsent assigns an empty list to each dictiary as the key
			uniqueElemDictionary.putIfAbsent( x, new ArrayList <String>()  );
			
			for ( Character y : removeDuplicateSet)
			{
				// y is the unique value from the set
				// If that value is the same as the current key save the position in the array
				if ( Character.compare( y, x ) == 0 ) 
				{
					uniqueElemDictionary.get(x).add( Integer.toString( counter ) );
				}
				
			}
			
			// cleanup and save memory by removing empty dictionaries
			if ( uniqueElemDictionary.get(x).size() == 0){
				uniqueElemDictionary.remove( x );
			}
		
		}
		return new getIndexOfDuplicates( uniqueElemDictionary, copyOfDuplicatesList );
	}
	
	public ArrayList < List<String> >  presentData (){
		LinkedHashMap<Character, ArrayList <String>> uniqueElemDictionary = new LinkedHashMap<Character, ArrayList <String>>( getIndexOfDuplicates2().uniqueElemDictionary );
		
		ArrayList <Character> copyOfDuplicatesList = new ArrayList <Character>( getIndexOfDuplicates2().copyOfDuplicatesList );
		// This is used to store recurring character and its original location and next occurance
		ArrayList <List<String> > presentingTheData = new ArrayList <List<String> >();

		// This counter limits the iterator for uniqueElemDictionary to grap the first two strings of the ArrayList
		int counter = 0;
		
		Iterator copyOfDuplicatesListIter = copyOfDuplicatesList.iterator();
		while (copyOfDuplicatesListIter.hasNext()){
			// Must convert checkMe to a character or I will not be able to reference it in the LinkedHashMap  
			Character checkMe = copyOfDuplicatesListIter.next().toString().charAt(0);
			
			// This list is created each time so it can be reset to store a new list 
			List<String> tmpCreateList = new ArrayList<String>();
			tmpCreateList.add( checkMe.toString() );
			// Must use iterator here to avoid errors: java.util.ConcurrentModificationException
			// because I am modifing a list as I iterate over it
			Iterator iter = uniqueElemDictionary.get( checkMe ).iterator();
			while ( iter.hasNext())
			{
				tmpCreateList.add( iter.next().toString() );
				iter.remove();
				counter++;
				if ( counter > 1){
					break;
				}
			}
			
			presentingTheData.add ( tmpCreateList );
			counter=0;
		}
		//Clearing the uniqueElemDictionary from memory
		uniqueElemDictionary.clear();
		return presentingTheData;
	}
	
	//public ArrayList <Character> testingProcedure ( int howMany )
	public void testingProcedure ( int howMany )
	{
		ArrayList <Character> alphabetList = new ArrayList<> ();
		ArrayList <Character> shuffledListOfCharacters = new ArrayList<> ();		
		Random rand = new Random();
		
		Character c;
		for ( c ='a'; c<='z'; ++c ){
			alphabetList.add( c );
		}
		
		for ( int i = 0; i < howMany; i++){
			shuffledListOfCharacters.add ( alphabetList.get ( rand.nextInt(26) ) );
		}

		//System.out.printf("shuffledListOfCharacters :: %s%n", shuffledListOfCharacters  );
		this.myList = shuffledListOfCharacters;
		System.out.printf("%s%n", this.myList );
		//return shuffledListOfCharacters;
	}
	
	
	public static void main ( String args[])
	{
		// Test Case list data type ArrayList
		ArrayList <Character> mySexyList = new ArrayList<> ();
		mySexyList.addAll (Arrays.asList ('c', 'z', 'd', 'r', 'd', 'q', 't', 'p', 'd', 'a', 't', 'd', 'z' ));
		
		//System.out.printf("mySexyList :: %s%n", mySexyList );
		//System.out.printf("%s%n", mySexyList );
		
		//JavaFindNextDuplicate testing = new JavaFindNextDuplicate ( mySexyList );
		// Below I sent an empty Arraylist so I can use testing
		JavaFindNextDuplicate testing = new JavaFindNextDuplicate ( new ArrayList<> () );
		//JavaFindNextDuplicate testing = new JavaFindNextDuplicate ( testing.testingProcedure( 13 ) );
		
		testing.testingProcedure( 10 );
		System.out.printf("%s%n", testing.theDuplicates() );
		System.out.printf("%s %s%n",  testing.getIndexOfDuplicates2().uniqueElemDictionary, testing.getIndexOfDuplicates2().copyOfDuplicatesList );
		System.out.printf("%s%n", testing.presentData() );
		
		
		//System.out.printf("%s%n", testing.testingProcedure( 13 ) );
		//testing.testingProcedure( 13 );
	}
	
	
}




