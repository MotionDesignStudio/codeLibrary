package com.github.mds.code.duplicates;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SearchForDuplicatesTest {
    @Test
    public void testDuplicateSearch() {
        Map<String, Map<String, Double>> critics = new HashMap<>(
                Map.of("Lisa Rose", Map.of("Lady in the Water" , 2.5, "Snakes on a Plane" , 3.5, "Just My Luck" , 3.0, "Superman Returns" , 3.5, "You, me and Dupree" , 2.5, "The Night Listener" , 3.0))
        );
        critics.put ("Gene Seymour", Map.of("Lady in the Water" , 2.5, "Snakes on a Plane" , 3.5, "Just My Luck" , 3.0, "Superman Returns" , 3.5, "You, me and Dupree" , 2.5, "The Night Listener" , 3.0) );
        critics.put ("Michael Phillips", Map.of( "Lady in the Water" , 2.5, "Snakes on a Plane" , 3.0, "Superman Returns" , 3.5, "The Night Listener" , 4.0 ) );
        critics.put ("Claudia Puig", Map.of( "Snakes on A Plane" , 3.5, "Just My Luck" , 3.0, "The Night Listener" , 4.5, "Superman Returns" , 4.0, "You, me and Dupree" , 2.5 ) );
        critics.put ("Mick LaSalle", Map.of("Lady in the Water", 3.0, "Snakes on a Plane", 4.0, "Just My Luck", 2.0, "Superman Returns", 3.0, "The Night Listener", 3.0, "You, me and Dupree", 2.0 ) );
        critics.put ("Jack Matthews", Map.of("Lady in the Water", 3.0, "Snakes on a Plane", 4.0, "The Night Listener", 3.0, "Superman Returns", 5.0, "You, me and Dupree", 3.5 ) );
        critics.put ("Toby", Map.of("Snakes on a Plane",4.5,"You, Me and Dupree",1.0,"Superman Returns",4.0 ) );

        SearchForDuplicatesInList testing = new SearchForDuplicatesInList ( critics );
        //System.out.printf("%s%n", testing.buildSetMovies() );

        //System.out.printf("%s%n", testing.searchForDuplicates() );

        Set set= testing.discardDuplicateSet();
        assertEquals(set.size(), 6);
    }
}
