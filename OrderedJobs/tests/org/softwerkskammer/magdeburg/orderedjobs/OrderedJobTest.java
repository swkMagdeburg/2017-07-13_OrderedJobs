package org.softwerkskammer.magdeburg.orderedjobs;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.softwerkskammer.magdeburg.orderedjobs.IOrderedJobs;
import org.softwerkskammer.magdeburg.orderedjobs.OrderedJobs;


public class OrderedJobTest {

	private IOrderedJobs systemUnderTest;

	@Before
	public void setUp() {
		systemUnderTest = new OrderedJobs();
	}

	@Test
	public void testSort() {
		String result = systemUnderTest.sort();
		assertEquals("", result);
	}

	@Test
	public void testRegisterSingleJob() {
		systemUnderTest.register('m');
		assertEquals("m", systemUnderTest.sort());
	}
	
	@Test
	public void testRegisterDuplicateJob() {
		systemUnderTest.register('a');
		systemUnderTest.register('a');
		assertEquals("a", systemUnderTest.sort());
	}
	
	@Test
	public void testRegisterTwoJobs() {
		systemUnderTest.register('a');
		systemUnderTest.register('b');
		assertEquals("ab", systemUnderTest.sort());
	}
	
	@Test
	public void testRegisterDependendJobs() {
		systemUnderTest.register('b', 'a');
		assertEquals("ab", systemUnderTest.sort());
	}
	
	@Test
	public void testExample() {
		systemUnderTest.register('c');
		systemUnderTest.register('b', 'a');
		systemUnderTest.register('c', 'b');
		assertEquals("abc", systemUnderTest.sort());
	}
	
	@Test(expected = IllegalArgumentException.class)	
	public void testRegisterDirectCircularDependencies() {
		systemUnderTest.register('b', 'c');
		systemUnderTest.register('c', 'b');
		systemUnderTest.sort();
	}
	
	@Test
	public void registerDependencies() {
		systemUnderTest.register('b', 'c');
		assertEquals("cb", systemUnderTest.sort());
	}
	

}
