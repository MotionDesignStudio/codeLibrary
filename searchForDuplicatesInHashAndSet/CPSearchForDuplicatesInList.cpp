
#include <iostream>
#include <map>
#include <string>
#include <set>
#include <string> 
#include <algorithm> 
#include <cctype>
using namespace std;

using MovieCritics = map<string, map<string, int>>;

class Search4UniquesAndDuplicates
{
	public:
	MovieCritics myCritics;
	
	// Parameterized Constructor
	Search4UniquesAndDuplicates ( MovieCritics x )
	{
		// Set value for incoming nested map
		myCritics = x;
	}
	
	// Build Set
	std::set<string> buildSetMovies ( )
	{
		auto movieSet = std::set<string>{};
		
		for (auto&& critic : myCritics)
		{
			for (auto&& entry : critic.second)
			{
				// Add movies to the set buildSetMovies
				movieSet.insert( entry.first );
			}
		}
		
		return movieSet;
	}
	
	
	std::set<string> searchForDuplicates ()
	{
		// Temporary store movies a set
		auto movieSet = std::set<string>{ buildSetMovies() };
				
		// Possible Duplicates 
		auto duplicateMovieSet = std::set<string>{ };
		
		auto it = movieSet.begin();

		auto lowerCaseAndCheckMe =  string{};
		// tmp is necessary because checkMe I can not modify by o lowering the case
		auto tmp =  string{};
		while (it != movieSet.end())
		{
			// get first values in set
			lowerCaseAndCheckMe = *it;
			// Conver to lower case	
			transform( lowerCaseAndCheckMe.begin(), lowerCaseAndCheckMe.end(), lowerCaseAndCheckMe.begin(), ::tolower);
				
			// remove value from set
			// this must be removed here or the test will always return a positive answer because it compares itself
			movieSet.erase(it++);
			
			// Iterate over 
			for (string const & checkMe: movieSet)
			{
				// Conver to lower case
				tmp = checkMe;
				transform( tmp.begin(), tmp.end(), tmp.begin(), ::tolower);
				if( (lowerCaseAndCheckMe.compare( tmp ) ) == 0)
					duplicateMovieSet.insert( checkMe );
			}

		}
		return duplicateMovieSet;
	}
	
	std::set<string> discardDuplicateSet ()
	{
		// discard duplicates in a set
		// call the method that creates the initial set of movies
		auto uniqueMovieSet = std::set<string>{ buildSetMovies() };
		
		// Discard all the movies that are duplicate in the orgin set using the
		// movies from dumplicate movies set
		for (string const & checkMeDuplicate: searchForDuplicates () )
		{
			uniqueMovieSet.erase( checkMeDuplicate );
		}
		return uniqueMovieSet;
	}
	
	
};


int main(){

	// A Map of movie critics and their ratings of a small set of movies
	map<string, string> critics;
	
	auto critics2 = MovieCritics{};
	
	critics2["Lisa Rose"] = {
		{"Lady in the Water", 2.5},	{"Snakes on a Plane", 3.5},	{"Just My Luck" , 3.0},	{"Superman Returns" , 3.5},	{"You, me and Dupree" , 2.5}, {"The Night Listener" , 3.0 }
	};
		
	critics2["Gene Seymour"] = {
		{"Lady in the Water" , 3.0}, {"Snakes on a Plane" , 3.5}, {"Just My Luck" , 1.5}, {"Superman Returns" , 5.0}, {"You, me and Dupree" , 3.0}, {"The Night Listener" , 3.5 }
	};	
	
	critics2["Michael Phillips"] = {
		{"Lady in the Water" , 2.5}, {"Snakes on a Plane" , 3.0}, {"Superman Returns" , 3.5}, {"The Night Listener" , 4.0 }
	};
	
	critics2["Claudia Puig"] = { 
		{"Snakes on A Plane" , 3.5}, {"Just My Luck" , 3.0}, {"Just My Luck" , 3.0}, {"The Night Listener" , 4.5}, {"Superman Returns" , 4.0}, {"You, me and Dupree" , 2.5 }
	};
		
	critics2["Mick LaSalle"] = { 
		{"Lady in the Water", 3.0}, {"Snakes on a Plane", 4.0}, {"Just My Luck", 2.0}, {"Superman Returns", 3.0}, {"The Night Listener", 3.0}, {"You, me and Dupree", 2.0}
	};
	
	critics2["Jack Matthews"] = { 
		{"Lady in the Water", 3.0}, {"Snakes on a Plane", 4.0}, {"The Night Listener", 3.0}, {"Superman Returns", 5.0}, {"You, me and Dupree", 3.5}
	};
	
	critics2["Toby"] = { 
		{"Snakes on a Plane",4.5}, {"You, Me and Dupree",1.0}, {"Superman Returns",4.0}
	};
	
	// New Object
	Search4UniquesAndDuplicates testing( critics2 );

	//for ( auto&& movies : testing.buildSetMovies() ) { cout << movies << ", ";  }
	//for ( auto&& movies : testing.searchForDuplicates() ) { cout << movies << ", ";  }
	for ( auto&& movies : testing.discardDuplicateSet() ) { cout << movies << ", ";  }
	
	return 0;
}