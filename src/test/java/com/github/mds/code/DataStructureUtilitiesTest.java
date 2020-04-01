package com.github.mds.code;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;


public class DataStructureUtilitiesTest {
    @Test
    public void testDuplicateRemoval() {
        // it is SUPER-critical that the things be named the same way; "You and me" is not the same as "You and Me"
        Map<String, Map<String, Double>> critics = new HashMap<>(
                Map.of("Lisa Rose", Map.of("Lady in the Water" , 2.5, "Snakes on a Plane" , 3.5, "Just My Luck" , 3.0, "Superman Returns" , 3.5, "You, Me and Dupree" , 2.5, "The Night Listener" , 3.0))
        );
        critics.put ("Gene Seymour", Map.of("Lady in the Water" , 2.5, "Snakes on a Plane" , 3.5, "Just My Luck" , 3.0, "Superman Returns" , 3.5, "You, Me and Dupree" , 2.5, "The Night Listener" , 3.0) );
        critics.put ("Michael Phillips", Map.of( "Lady in the Water" , 2.5, "Snakes on a Plane" , 3.0, "Superman Returns" , 3.5, "The Night Listener" , 4.0 ) );
        critics.put ("Claudia Puig", Map.of( "Snakes on a Plane" , 3.5, "Just My Luck" , 3.0, "The Night Listener" , 4.5, "Superman Returns" , 4.0, "You, Me and Dupree" , 2.5 ) );
        critics.put ("Mick LaSalle", Map.of("Lady in the Water", 3.0, "Snakes on a Plane", 4.0, "Just My Luck", 2.0, "Superman Returns", 3.0, "The Night Listener", 3.0, "You, Me and Dupree", 2.0 ) );
        critics.put ("Jack Matthews", Map.of("Lady in the Water", 3.0, "Snakes on a Plane", 4.0, "The Night Listener", 3.0, "Superman Returns", 5.0, "You, Me and Dupree", 3.5 ) );
        critics.put ("Toby", Map.of("Snakes on a Plane",4.5,"You, Me and Dupree",1.0,"Superman Returns",4.0 ) );
        // convert data to a Guava Table

        Table<String, String, Double> movieRatings= HashBasedTable.create();
        // sigh. Why wasn't it in this form already? Or in a database?
        // in a database, this would be a simple select: select distinct moviename from ratings where criticname in (...)
        critics.entrySet().stream().forEach(entry -> {
            entry.getValue().entrySet().stream().forEach(rating -> {
                movieRatings.put(entry.getKey(), rating.getKey(), rating.getValue());
            });
        });
        // but now that we have the table...
        Set<String> movies=movieRatings.cellSet().stream().map(cell -> cell.getColumnKey()).collect(Collectors.toSet());
        System.out.println(movies);
        assertEquals(movies.size(), 6);

    }
}
