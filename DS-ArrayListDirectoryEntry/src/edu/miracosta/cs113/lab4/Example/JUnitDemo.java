package edu.miracosta.cs113.lab4.Example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JUnitDemo {
    static List<Employee> emps = new ArrayList<Employee>();

    
	  
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("In Set up current Size" + emps.size());
		emps.add(new Employee(1, "Nats", 15000));
		emps.add(new Employee(2, "Kalid", 25000));
		emps.add(new Employee(3, "Krish", 5000));		

	}

	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		   System.out.println("In Tearing down");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
	    System.out.println("Running testInsert()");
	    assertEquals(emps.size(), 3);
	    emps.add(new Employee(4, "John", 15000));
	    assertEquals(emps.size(), 4);
	    assertEquals((emps.get(1)).getEmpId(), 1); // Second Employee put in has id 1
	 }

	
     @Test
	 public void testReplace() {
		    System.out.println("Running testReplace()");
		    assertEquals(emps.size(), 3);
		    emps.set(1, new Employee(1, "Jack", 2000));
		    
		    assertEquals(emps.size(), 3);
		    assertEquals((emps.get(1)).getName(), "Jack"); // checking values of employee
		    assertEquals((emps.get(1)).getSalary(), 2000); // checking values of employee
		    assertEquals((emps.get(1)).getEmpId(), 1); // checking values of employee
	  }
 
     @Test
     private void compare(List<Employee> emps2, Object[] strs) {
    	    Object[] array = emps2.toArray();
    	    assertTrue("Arrays not the same length", array.length == strs.length);
    	    for (int i = 0; i < array.length; i++) {
 //   	    	System.out.println(i + ((Employee)(array[i])).getName() + " " + ((Employee)strs[i]).getName());
    	    	assertEquals(strs[i], array[i]);
    	    }
     }


	@Test
     public void testOrder() {
    	    System.out.println("Running testOrder()");
    	    compare(emps, new Object[] {new Employee(1, "Nats", 15000), new Employee(2, "Kalid", 25000), new Employee(3, "Krish", 5000) });
    	  }

     @Test
     public void testRemove() {
    	    System.out.println("Running testRemove()");
    	    assertEquals(emps.size(), 4);
    	    emps.remove(1);
    	    assertEquals(emps.size(), 3);
    	    compare(emps, new Object[] { new Employee(1, "Nats", 15000), new Employee(3, "Krish", 5000), new Employee(4, "John", 15000)});
     }


}
