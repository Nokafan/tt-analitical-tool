package com.example.analitycal.tool;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class SortedLinesListsTest {
    public static final String RESPONCE_ONE = "C 1.1 8.15.1 P 15.10.2012 83";
    public static final String REQUEST_ONE = "D 1.1 8 P 01.01.2012-01.12.2012";
    private final SortedLinesLists lists = new SortedLinesLists();

    @Test
    public void testSetRequestLines() {
        lists.getRequestLines().add("D 1.1 8 P 01.01.2012-01.12.2012");
        Assert.assertEquals(1, lists.getRequestLines().size());
    }

    @Test
    public void testSetResponceLines() {
        lists.getResponceLines().add("C 1.1 8.15.1 P 15.10.2012 83");
        Assert.assertEquals(1, lists.getResponceLines().size());
    }
    @Test
    public void testGetRequestLines_isNotNull() {
        lists.getRequestLines().add("D 1.1 8 P 01.01.2012-01.12.2012");
        Assert.assertNotNull(lists.getRequestLines().get(0));
    }

    @Test
    public void testGetResponceLines_isNotNull() {
        lists.getResponceLines().add("C 1.1 8.15.1 P 15.10.2012 83");
        Assert.assertNotNull(lists.getResponceLines().get(0));
    }


    @Test
    public void testTestEquals() {
        lists.getResponceLines().add(RESPONCE_ONE);
        lists.getRequestLines().add(REQUEST_ONE);
        Assert.assertEquals("Strings are not equals", RESPONCE_ONE, lists.getResponceLines().get(0));
        Assert.assertEquals("Strings are not equals", REQUEST_ONE, lists.getRequestLines().get(0));
    }

    @Test
    public void testTestHashCode() {
        lists.getResponceLines().add(RESPONCE_ONE);
        lists.getRequestLines().add(REQUEST_ONE);
        Assert.assertEquals(REQUEST_ONE.hashCode(), lists.getRequestLines().get(0).hashCode());
        Assert.assertEquals(RESPONCE_ONE.hashCode(), lists.getResponceLines().get(0).hashCode());
    }
}