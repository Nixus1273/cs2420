package assign01;

/**
 * This class represents a simple row or columnm vector of numbers. In a row
 * vector, the numbers are written horizontally (i.e., along the columns). In a
 * column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author CS 2420 course staff and Brandon Flickinger 
 * @version August 22, 2024
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private final double[][] data;
	// set to true for a row vector and false for a column vector
	private final boolean isRowVector;
	// count of elements in the vector
	private final int vectorSize;

	/**
	 * Creates a new row or column vector. For a row vector, the input array is
	 * expected to have 1 row and a positive number of columns, and this number of
	 * columns represents the vector's length. For a column vector, the input array
	 * is expected to have 1 column and a positive number of rows, and this number
	 * of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the
	 *                                  input 2D array is not compatible with a row
	 *                                  or column vector
	 */
	public MathVector(double[][] data) {
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			for(int i = 1; i < data.length; i++)
				if(data[i].length != 1)
					throw new IllegalArgumentException("For each row, the number of columns must be 1.");
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i = 0; i < this.data.length; i++) {
			for(int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Generates a textual representation of this vector.
	 * 
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and
	 * "1.0\n2.0\n3.0\n4.0\n5.0" for a sample column vector of length 5. In both
	 * cases, notice the lack of a newline or space after the last number.
	 * 
	 * @return textual representation of this vector
	 */
	public String toString() {
		StringBuilder vecString = new StringBuilder();
		if (this.isRowVector) {
            for (double[] datum : this.data) {
                for (double v : datum) {
                    vecString.append(v).append(" ");
                }
            }
		} else {
			for (int i = 0; i < this.data.length; i++) {
				for (int j = 0; j < this.data[i].length; j++) {
					if (i < this.data.length - 1) { 
						vecString.append(data[i][j]).append("\n");
					} else {
						vecString.append(data[i][j]);
					}
				}
			}
		}
		return vecString.toString().strip();
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality
	 * is defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 * @return true if this vector is equivalent to other, false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MathVector otherVec))
			return false;

        if (this.isRowVector && otherVec.isRowVector) {
			if (this.vectorSize == otherVec.vectorSize) {
				for (int i = 0; i < this.vectorSize; i++) {
					if (this.data[0][i] != otherVec.data[0][i]) {
						return false;
					}
				}
				return true;
			}
		} else if  (!(this.isRowVector) && !(otherVec.isRowVector)){
			if (this.vectorSize == otherVec.vectorSize) {
				for (int i = 0; i < this.vectorSize; i++) {
					for (int j = 0; j < this.data[0].length; j++) {
						if (this.data[i][j] != otherVec.data[i][j]) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Updates this vector by using a given scaling factor to multiply each entry.
	 * 
	 * @param factor - the scaling factor
	 */
	public void scale(double factor) {
		if (this.isRowVector) {
			for (int i = 0; i < this.vectorSize; i++) {
				this.data[0][i] *= factor;
			}
		} else {
			for (int i = 0; i < this.data.length; i++) {
				for (int j = 0; j < this.data[i].length; j++) {
					this.data[i][j] *= factor;
				}
			}
		}
	}

	/**
	 * Generates a new vector that is the transposed version of this vector.
	 * 
	 * @return transposed version of this vector
	 */
	public MathVector transpose() {
		if (this.isRowVector) {
			double[][] transposedRow = new double[this.vectorSize][1];
			for (int i = 0; i < this.vectorSize; i++) {
				transposedRow[i][0] = this.data[0][i];
			}
			return new MathVector(transposedRow);
		} else {
			double[][] transposedCol = new double[1][this.vectorSize];
			for (int i = 0; i < this.data.length; i++) {
				transposedCol[0][i] = this.data[i][0];
			}
			return new MathVector(transposedCol);
		}
	}

	/**
	 * Generates a new vector representing the sum of this vector and another
	 * vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 * @return sum of this vector and other
	 */
	public MathVector add(MathVector other) {
		if (this.isRowVector == other.isRowVector && this.vectorSize == other.vectorSize) {
			double[][] sumRowVec = new double[1][other.vectorSize];
			if (this.isRowVector) {
				for (int i = 0; i < this.vectorSize; i++) {
					sumRowVec[0][i] = this.data[0][i] + other.data[0][i];
				}
				return new MathVector(sumRowVec);
			} else {
				double[][] sumColVec = new double[other.vectorSize][1];
				for (int i = 0; i < this.data.length; i++) { 
					sumColVec[i][0] = data[i][0] + other.data[i][0];
				}
				return new MathVector(sumColVec);
			}
		} else {
			throw new IllegalArgumentException("Vectors must be same type and length!");
		}
	}

	/**
	 * Computes the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the
	 *              dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 * @return dot product of this vector and other
	 */
	public double dotProduct(MathVector other) {
		double dotProduct = 0;
		if (this.isRowVector == other.isRowVector && this.vectorSize == other.vectorSize) {
			if (this.isRowVector) {
				for (int i = 0; i < this.vectorSize; i++) {
					dotProduct += this.data[0][i] * other.data[0][i];
				}
			} else {
				for (int i = 0; i < this.data.length; i++) {
					dotProduct += this.data[i][0] * other.data[i][0];
				}
			}
		} else {
			throw new IllegalArgumentException("Vectors must be same type and length!");
		}
		return dotProduct;
	}

	/**
	 * Computes this vector's magnitude (also known as a vector's length).
	 * 
	 * @return magnitude of this vector
	 */
	public double magnitude() {
		double magnitude = 0;
		if (this.isRowVector) {
			for (int i = 0; i < this.vectorSize; i++) {
				magnitude += this.data[0][i] * this.data[0][i];
			}
		} else {
			for (int i = 0; i < this.data.length; i++) {
				for (int j = 0; j < this.data[i].length; j++) {
					magnitude += this.data[i][j] * this.data[i][j];
				}
			}
		}
		return Math.sqrt(magnitude);
	}
	
	/**
	 * Updates this vector by using standardizing it (AKA normalization). 
	 */
	public void normalize() {
		MathVector length = new MathVector(this.data);
		double magnitude = length.magnitude();
		if (this.isRowVector) {
			for (int i = 0; i < this.vectorSize; i++) {
				this.data[0][i] /= magnitude;
			}
		} else {
			for (int i = 0; i < this.data.length; i++) {
				for (int j = 0; j < this.data[i].length; j++) {
					this.data[i][j] /= magnitude;
				}
			}
		}
	}
	
}