#!/usr/bin/env python3.7

# Find next occurance of X

import random, string

class FindNext:
	
	def __init__ ( self, myList ):
		self.myList = myList;
		#print ( self.myList );
		
	def theDuplicates(self):
		testMe="";
		mainList = self.myList.copy();
		# List of duplicates
		duplicatesList = [];
		
		# Loop over list and create array of dumplicates
		while mainList:
			testMe = mainList.pop(0);
			#print  (" \n searching For: {}".format( testMe ) );
			
			for checkMe in mainList:
				if testMe == checkMe:
					duplicatesList.append( testMe );

					# Remove the found object from the mainList
					mainList.pop ( mainList.index( testMe ) );
				
					# Exit for loop or it will continue to test positive for multiple matches
					break;
					
		return duplicatesList;
	
	def getIndexOfDuplicates(self):
		mainList = self.myList;
		# I make a copy here so I can send later to the mehtod presentData 
		# this decreases duplicate calls
		copyOfDuplicatesList = self.theDuplicates().copy();
		# Duplicate list cast to set to remove duplicates	
		removeDuplicateSet = set ( copyOfDuplicatesList );
		# Dictionary for each unique element
		uniqueElemDictionary={};
		
		counter = -1;
		for x in mainList:
			counter +=1;
			# setdefault assigns an empty list to each dictiary as the key
			uniqueElemDictionary.setdefault(x, [] );
			
			for y in removeDuplicateSet:
				# y is the unique value from the set
				# If that value is the same as the current key save the position in the array
				if y == x:
					uniqueElemDictionary[x].append(counter);	
			# VIP start ing with Python 3.7 Dictionary remains in order entered
			# cleanup and save memeory by removing empty dictionaries
			if len ( uniqueElemDictionary[x] ) == 0:
				del uniqueElemDictionary[x];

		#print  ("uniqueElemDictionary :: {} copyOfDuplicatesList :: {}".format( uniqueElemDictionary, copyOfDuplicatesList ) );
		return uniqueElemDictionary, copyOfDuplicatesList;
				
	def presentData(self):
		
		uniqueElemDictionary, copyOfDuplicatesList = self.getIndexOfDuplicates();
		# This is used to store recurring character and its original location and next occurance
		presentingTheData = [];
		
		for x in copyOfDuplicatesList:
			# Assign the first two values of the list to the each element in duplicate list 
			presentingTheData.append( [x] + uniqueElemDictionary[x][0:2] );
			del uniqueElemDictionary[x][0:2];

		return presentingTheData;

def testingProcedure( howMany ):
	alphabetList = list ( string.ascii_lowercase );
	randomNumbersList = [ random.randrange(0, len ( alphabetList ) , 1) for i in range( howMany )  ];
	shuffledListOfCharacters = [ alphabetList [ ranNum ] for ranNum in randomNumbersList  ]; 
	
	return ( shuffledListOfCharacters );
	
def main():
	
	# Test Case list
	mySexyList = ['c', 'z', 'd', 'r', 'd', 'q', 't', 'p', 'd', 'a', 't', 'd', 'z'];
	
	#tmp = testingProcedure( 13 );
	#print ( tmp );
	print ( mySexyList );
	
	#testing = FindNext( tmp );
	testing = FindNext( mySexyList );
	#print ( testing.theDuplicates() );
	print ( testing.getIndexOfDuplicates() );
	#print ( testing.presentData() );
	
if __name__ == "__main__":
	main();