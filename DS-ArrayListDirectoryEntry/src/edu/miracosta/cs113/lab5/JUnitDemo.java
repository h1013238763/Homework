package edu.miracosta.cs113.lab5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnitDemo {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void test1() {
        SingleLinkedList testInstance = new SingleLinkedList();
        assert 0 == testInstance.size();
    }

    /**
     * Test to see that the get method throws an
     * exception on an empty list
     */
	@Test
	public void test2() {
        try {
            SingleLinkedList testInstance = new SingleLinkedList();
            assert null == testInstance.get(0);
            assert false;
        } catch (IndexOutOfBoundsException ex) {
            assert true;
        }
    }

    /** Test to see that add method inserts an item */
	@Test
	public void test3() {
        SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
        testInstance.add("tom");
        assert (1 == testInstance.size());
        assert ("[tom]".equals(testInstance.toString()));
    }


	/** Test to see that add method inserts three items */
	@Test
	public void test4() {
        SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
        testInstance.add("tom");
        testInstance.add("dick");
        testInstance.add("harry");
        assert (3 == testInstance.size());
        assert ("[tom ==> dick ==> harry]".equals(testInstance.toString()));
    }

    /** Test the get method on a list of three items */
	@Test
	public void test5() {
        SingleLinkedList<String> testInstance = new SingleLinkedList<String>();
        testInstance.add("tom");
        testInstance.add("dick");
        testInstance.add("harry");
        assert ("tom".equals(testInstance.get(0)));
        assert ("dick".equals(testInstance.get(1)));
        assert ("harry".equals(testInstance.get(2)));
    }

 

}
