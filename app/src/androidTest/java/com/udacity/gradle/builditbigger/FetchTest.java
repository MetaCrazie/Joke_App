package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.util.Log;

import junit.framework.TestCase;

/**
 * Created by praty on 18/09/2016.
 */
public class FetchTest extends TestCase {
    private Context context;

    public void testDoInBackground() throws Exception {
        String joke = new FetchJoke(context).doInBackground(context);
        assertNotNull("Empty string", joke);
    }
}
