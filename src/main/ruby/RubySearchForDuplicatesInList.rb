#!/usr/bin/env ruby

# using version :  ruby 2.3.3p222 (2016-11-21) [x86_64-linux-gnu]

require 'set'

# A hash of movie critics and their ratings of a small set of movies

critics = { 
	'Lisa Rose' => { 'Lady in the Water' => 2.5, 'Snakes on a Plane' => 3.5, 'Just My Luck' => 3.0, 'Superman Returns' => 3.5, 'You, me and Dupree' => 2.5, 'The Night Listener' => 3.0 } ,   
	'Gene Seymour' => { 'Lady in the Water' => 3.0, 'Snakes on a Plane' => 3.5, 'Just My Luck' => 1.5, 'Superman Returns' => 5.0, 'You, me and Dupree' => 3.0, 'The Night Listener' => 3.5 } ,
	'Michael Phillips' => { 'Lady in the Water' => 2.5, 'Snakes on a Plane' => 3.0, 'Superman Returns' => 3.5, 'The Night Listener' => 4.0 } ,
	'Claudia Puig' => { 'Snakes on A Plane' => 3.5, 'Just My Luck' => 3.0, 'The Night Listener' => 4.5, 'Superman Returns' => 4.0, 'You, me and Dupree' => 2.5 } ,   
	'Mick LaSalle'=> {'Lady in the Water'=> 3.0, 'Snakes on a Plane'=> 4.0, 'Just My Luck'=> 2.0, 'Superman Returns'=> 3.0, 'The Night Listener'=> 3.0, 'You, me and Dupree'=> 2.0} ,
	'Jack Matthews'=> {'Lady in the Water'=> 3.0, 'Snakes on a Plane'=> 4.0, 'The Night Listener'=> 3.0, 'Superman Returns'=> 5.0, 'You, me and Dupree'=> 3.5},	  
	'Toby'=> {'Snakes on a Plane'=>4.5,'You, Me and Dupree'=>1.0,'Superman Returns'=>4.0}
		  		  
		  }


# Ruby requires class name start with a capital letter.

class Search4UniquesAndDuplicates
  
  def initialize  ( critics )
		@myCritics = critics
  end
  
  
  def buildSetMovies
    # Init a set to store all the movies
    movieHash = @myCritics
    
    thisIsASet = Set.new
    
    movieHash.each { | theUser, theirList |
      
      theirList.each { | theMovie |
        thisIsASet.add theMovie[0]
        }
      }
    
    return (thisIsASet)
   
  end
  
  def searchForDuplicates 
    # Temporary store movies a set
		movieSet = buildSetMovies
    
    #Possible Duplicates 
		duplicateMovieSet = Set.new
    
    convertMovieSetToArray = movieSet.to_a
    
   while convertMovieSetToArray.any?
      
     lowerCaseAndCheckMe = convertMovieSetToArray.pop
     
     for checkMe in convertMovieSetToArray
       if lowerCaseAndCheckMe.downcase == checkMe.downcase
        duplicateMovieSet.add lowerCaseAndCheckMe
       end
     end

   end
    
    return duplicateMovieSet
    
  end  
    
  
  def discardDuplicateSet
    # discard duplicates in a set
		# call the method that creates the initial set of movies
    uniqueMovieSet = buildSetMovies
    
    # Discard all the movies that are duplicate in the orgin set using the
		# movies from dumplicate movies set
    
    for checkMeDuplicate in searchForDuplicates
      uniqueMovieSet.delete ( checkMeDuplicate )
    end
    
    return uniqueMovieSet
  end
  
end

testing = Search4UniquesAndDuplicates.new ( critics )

#testing.buildSetMovies
#testing.searchForDuplicates

for x in testing.discardDuplicateSet
  puts x
end
