#!/opt/rakudo-pkg/bin/perl6

# A hash of movie critics and their ratings of a small set of movies

my %critics = 'Lisa Rose' => { 'Lady in the Water' => 2.5, 'Snakes on a Plane' => 3.5, 'Just My Luck' => 3.0, 'Superman Returns' => 3.5, 'You, me and Dupree' => 2.5, 'The Night Listener' => 3.0 },
'Gene Seymour' => { 'Lady in the Water' => 3.0, 'Snakes on a Plane' => 3.5, 'Just My Luck' => 1.5, 'Superman Returns' => 5.0, 'You, me and Dupree' => 3.0, 'The Night Listener' => 3.5 } ,
'Michael Phillips' => { 'Lady in the Water' => 2.5, 'Snakes on a Plane' => 3.0, 'Superman Returns' => 3.5, 'The Night Listener' => 4.0 } ,
	'Claudia Puig' => { 'Snakes on A Plane' => 3.5, 'Just My Luck' => 3.0, 'Just My Luck' => 3.0, 'The Night Listener' => 4.5, 'Superman Returns' => 4.0, 'You, me and Dupree' => 2.5 } ,   
	'Mick LaSalle'=> {'Lady in the Water'=> 3.0, 'Snakes on a Plane'=> 4.0, 'Just My Luck'=> 2.0, 'Superman Returns'=> 3.0, 'The Night Listener'=> 3.0, 'You, me and Dupree'=> 2.0} ,
	'Jack Matthews'=> {'Lady in the Water'=> 3.0, 'Snakes on a Plane'=> 4.0, 'The Night Listener'=> 3.0, 'Superman Returns'=> 5.0, 'You, me and Dupree'=> 3.5},	  
	'Toby'=> {'Snakes on a Plane'=>4.5,'You, Me and Dupree'=>1.0,'Superman Returns'=>4.0} ;

class search4UniquesAndDuplicates {

	# Default constructor to set attibutes for created object(s)
	has Hash $.critics;
	
	method buildSetMovies () {
		# Init a set to store all the movies
		
		my $movieSet = SetHash.new;

		# loop over each key or user
		for $.critics.keys -> $agents { 
			for $.critics{$agents}.keys -> $movies { 
				# Extract each movie
				$movieSet{$movies}++;
			}		
		}

		#say $movieSet.perl;
		return $movieSet
	}
	
	method searchForDuplicates () {
		# Temporary store movies a set
		my SetHash $movieSet = self.buildSetMovies;
		#Possible Duplicates 
		my SetHash $duplicateMovieSet;

		while $movieSet {
			my $lowerCaseAndCheckMe = $movieSet.grab;

			for $movieSet.keys -> $checkMe {
				if $lowerCaseAndCheckMe.lc() eq $checkMe.lc() {
					$duplicateMovieSet{$lowerCaseAndCheckMe}++;
				}
			}
			
		}
		return $duplicateMovieSet;
	}
	
	method discardDuplicateSet () {
		# discard duplicates in a set
		# call the method that creates the initial set of movies
		my SetHash $uniqueMovieSet = self.buildSetMovies;
		
		# Discard all the movies that are duplicate in the orgin set using the
		# movies from dumplicate movies set
		for self.searchForDuplicates().keys -> $checkMeDuplicate {
			$uniqueMovieSet{$checkMeDuplicate}--;
		}
		return $uniqueMovieSet;
	}

}

my $testing = search4UniquesAndDuplicates.new( critics => %critics );

#say $testing.buildSetMovies();
#say $testing.searchForDuplicates();
say $testing.discardDuplicateSet().perl;


