#!/usr/bin/env python3.5

# A dictionary of movie critics and their ratings of a small set of movies

from pythonData import critics

class search4UniquesAndDuplicates :
	def __init__ ( self, critics ):
		self.myCritics = critics
	
	def buildSetMovies ( self ) :
		# Init a set to store all the movies
		movieSet = set()
		for agents in self.myCritics :
			for movies in self.myCritics[ agents ] :
				movieSet.add( movies )
		
		return ( movieSet )
	
	def searchForDuplicates ( self ) :		
		# Temporary store movies a set
		movieSet = self.buildSetMovies ()
		
		#Possible Duplicates 
		duplicateMovieSet = set()
		
		while movieSet :
		# remove last value of set
			lowerCaseAndCheckMe = movieSet.pop()
			for checkMe in movieSet :
				if lowerCaseAndCheckMe.lower() == checkMe.lower() :
					duplicateMovieSet.add( lowerCaseAndCheckMe )
		
		return ( duplicateMovieSet )
		
	def discardDuplicateSet ( self ) :
		# discard duplicates in a set
		# call the method that creates the initial set of movies
		uniqueMovieSet =  self.buildSetMovies ()
		
		# Discard all the movies that are duplicate in the orgin set using the
		# movies from dumplicate movies set
		for checkMeDuplicate in self.searchForDuplicates() :
			uniqueMovieSet.discard ( checkMeDuplicate )
			
		return ( uniqueMovieSet )
		
	
testing = search4UniquesAndDuplicates ( critics )
		
#print ( testing.buildSetMovies() )	
#print ( testing.searchForDuplicates() )
print ( testing.discardDuplicateSet() )
	
	
	