package com.gabe.hosanna;

import com.gabe.hosanna.adapter.FilteredResultsAdapter;
import com.gabe.hosanna.views.SearchResults;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Author: Hosanna Gabe-Oji
 * Project: Dec Challenge
 * Date:   2020-06-14
 */
public class SearchResultsTest {

    public static final int TOTAL_RECORDS = 65500;

    @Test
    public void testSearchResults() throws IOException{
        FilteredResultsAdapter filteredResultsAdapter = new SearchResults().getFilteredResults("","","");
        assertEquals(TOTAL_RECORDS, filteredResultsAdapter.getCount());
    }
}
