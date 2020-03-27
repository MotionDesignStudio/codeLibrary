#!/usr/bin/env python3.5

# Find next occurance of X

# Test Case list
mySexyList = ['c', 'z', 'd', 'r', 'd', 'q', 't', 'p', 'd', 'a', 't', 'd', 'z'];

class FindNext:
	
	def __init__ ( self, myList ):
		self.myList = myList;
		print ( self.myList );
		
	def theDuplicates(self):
		testMe="";
		mainList = self.myList.copy();
		# List of duplicates
		duplicatesList = [];
		# List of indexes
		listOfIndexes = [];
		# List length
		listLength = len( mainList );
		#print  ("listLength: {}".format(listLength) );
		
		# Loop over list and create array of dumplicates
		while mainList:
			testMe = mainList.pop(0);
			#print  (" \n searching For: {}".format( testMe ) );
			
			for checkMe in mainList:
				if testMe == checkMe:
					duplicatesList.append( testMe );
					#print  (" Pos: {}".format( mainList.index( testMe ) ) );
					#print  (" mainList Length: {}".format( len(mainList) ) );
					#print  (" mainList : {}".format( mainList ) );

					# Remove the found object from the mainList
					mainList.pop ( mainList.index( testMe ) );
				
					# Exit for loop or it will continue to test positive for multiple matches
					break;
					
					
		print  ( duplicatesList );
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
			
			# cleanup and save memeory by removing empty dictionaries
			if len ( uniqueElemDictionary[x] ) == 0:
				del uniqueElemDictionary[x];

		print  ("uniqueElemDictionary :: {} copyOfDuplicatesList :: {}".format( uniqueElemDictionary, copyOfDuplicatesList ) );
		return uniqueElemDictionary, copyOfDuplicatesList;
				
	def presentData(self):
		
		uniqueElemDictionary, copyOfDuplicatesList = self.getIndexOfDuplicates();
		# This is used to store recurring character and its original location and next occurance
		presentingTheData = [];
		
		for x in copyOfDuplicatesList:
			# Assign the first two values of the list to the each element in duplicate list 
			#print ( uniqueElemDictionary[x][0:2] );
			presentingTheData.append( [x] + uniqueElemDictionary[x][0:2] );
			del uniqueElemDictionary[x][0:2];

		print ( presentingTheData );
		return presentingTheData;

def main():
	testing = FindNext( mySexyList );
	#testing.theDuplicates();
	#testing.getIndexOfDuplicates();
	testing.presentData();
	
if __name__ == "__main__":
	main();