package com;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Cecilia on 21/6/2017.
 */
public class AppTest extends TestCase {
        /**
         * Create the test case
         *
         * @param testName name of the test case
         */
        public AppTest( String testName )
        {
            super( testName );
        }

        /**
         * @return the suite of tests being tested
         */
        public static Test suite()
        {
            return new TestSuite( AppTest.class );
        }

        /**
         * Rigourous Test :-)
         */
        public void testApp()
        {
            assertTrue( true );
        }
}
