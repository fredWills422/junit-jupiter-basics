package com.web.junitjupiterbasics;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import com.web.junitjupiterbasics.mathutils.MathUtils;
import static org.junit.jupiter.api.Assumptions.*;



@DisplayName("When running MathUtils")
class MathUtilsTest {
	
	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running " +testInfo.getDisplayName()+ " with tags " +testInfo.getTags());
	}
	
	
	@Nested
	@DisplayName("the add method")
	@Tag("Math")
	class AddTest{
		
		@Test
		@DisplayName("should return the right sum when adding two positive numbers.")
		void testAddPositive() {	
			assertEquals(2, mathUtils.add(1, 1),"should return the right sum");
		}
		
		@Test
		@DisplayName("should return the right sum when adding two negative numbers")
		void testAddNegative() {

			int expected = -2;
			int actual = mathUtils.add(-1, -1);
			assertEquals(expected, actual, ()-> "should return sum " +expected+ " but returned " +actual);
		
		}
		
	}
	
	@Test
	@DisplayName("multiply method")
	@Tag("Math")
	void multiplyTest(){
		testReporter.publishEntry("Running " +testInfo.getDisplayName()+ " with tags " +testInfo.getTags());
		//assertEquals(4, mathUtils.multiply(2,2), "should return the right product");
		assertAll(
				() -> assertEquals(4,mathUtils.multiply(2, 2)),
				() -> assertEquals(0,mathUtils.multiply(2, 0)),
				() -> assertEquals(-2,mathUtils.multiply(2, -1))
		);
	}
	
	@Test
	@Tag("Math")
	void divideTest() {
		
		//method similar to assertThat() that comes from Assumptions (?interface?) in jupiter.api
		assumeTrue(true);
		//asserting that expected Exception and actual Exception are equal if they are not throw error
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0),"Divide by zero should throw");
	}
	
	
	@Test
	@Tag("Circle")
	void computeCircleAreaTest() {
		//asserting that expected double and actual double are equal if they are not throw error
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return right circle area");
	
	}
	
	@Test
	@Disabled
	@DisplayName("TDD method should not run")
	void testDisabled() {
		fail("This test should fail");
	}
	
}
