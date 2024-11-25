package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the assign01.MathVector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author CS 2420 course staff and Brandon Flickinger
 * @version August 22, 2024
 */
public class MathVectorTest {
	
	private MathVector rowVec, unitVec, colVec;

	@BeforeEach
	public void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.4, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.4}, {7.1}});		
	}
	
	@Test
	public void createVectorFromMatrix() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	public void createVectorFromJaggedArray() {
	  double arr[][] = {{1}, {2}, {3}, {4, 5}, {6}, {7}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	}
	
	@Test
	public void smallColVectorToString() {
		String expected = "-11.0\n2.5\n36.0\n-3.4\n7.1";
		assertEquals(expected, colVec.toString());		
	}
	
	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}
	
	@Test
	public void scaleSmallColVector() {
		// error here
		MathVector expected = new MathVector(new double[][]{{-27.5}, {6.25}, {90}, {-8.5}, {17.75}});
		colVec.scale(2.5);
		assertTrue(expected.equals(colVec));
	}
	
	@Test
	public void transposeSmallRowVector() {
		MathVector expected = new MathVector(new double[][]{{3}, {1}, {2}});
		assertTrue(expected.equals(rowVec.transpose()));
	}
	
	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	public void addSmallRowVectors() {
		MathVector expected = new MathVector(new double[][]{{4, 2, 3}});
		assertTrue(expected.equals(rowVec.add(unitVec)));		
	}

	@Test
	public void dotProductSmallRowVectors() {
		assertEquals(3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0, rowVec.dotProduct(unitVec));		
	}
	
	@Test
	public void smallRowVectorMagnitude() {
		assertEquals(Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0), rowVec.magnitude());		
	}
	
	@Test
	public void smallRowVectorNormalize() {
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		MathVector expected = new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}});
		rowVec.normalize();
		assertTrue(expected.equals(rowVec));		
	}

	@Test
	public void testToString() {
		String expected = "3.0 1.0 2.0";
		assertEquals(expected, rowVec.toString());
	}
	
	@Test
	public void testEqualsColVector() {
		assertTrue(colVec.equals(new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.4}, {7.1}})));
	}
	
	@Test
	public void testEqualsFalseColVector() {
		assertFalse(colVec.equals(new MathVector(new double[][]{{-11}, {2}, {6}, {-4}, {1}})));
	}
	
	@Test
	public void testRowVecScale() {
		MathVector expected = new MathVector(new double[][]{{6, 2, 4}});
		rowVec.scale(2);
		assertTrue(expected.equals(rowVec));
	}
	
	@Test
	public void testColTranspose() {
		MathVector expected = new MathVector(new double[][]{{-11, 2.5, 36, -3.4, 7.1}});
		assertTrue(expected.equals(colVec.transpose()));
	}
	
	@Test
	public void addColVectors() {
		MathVector expected = new MathVector(new double[][]{{-22}, {5}, {72}, {-6.8}, {14.2}});
		assertTrue(expected.equals(colVec.add(colVec)));		
	}
	
	@Test
	public void dotProductColVectors() {
		assertEquals(1485.22, colVec.dotProduct(colVec));		
	}
	
	@Test
	public void colVectorMagnitude() {
		assertEquals(Math.sqrt(-11 * -11 + 2.5 * 2.5 + 36 * 36 + -3.4 * -3.4 + 7.1 * 7.1), colVec.magnitude());		
	}
	
	@Test
	public void colVectorNormalize() {
		double length = Math.sqrt(-11 * -11 + 2.5 * 2.5 + 36 * 36 + -3.4 * -3.4 + 7.1 * 7.1);
		MathVector expected = new MathVector(new double[][]{{-11 / length}, {2.5 / length}, {36 / length}, {-3.4 / length}, {7.1 / length}});
		colVec.normalize();
		assertTrue(expected.equals(colVec));		
	}
	
}
