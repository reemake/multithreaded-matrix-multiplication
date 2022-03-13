import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Class for generating matrices
 * @see MatrixMultiplication
 */
public class MatrixUtils {

    /** Number of rows in the 1st matrix */
    private int n = 15;

    /** Number of columns in the 1st matrix | rows in the 2nd matrix */
    private int p = 30;

    /** Number of columns in the 2nd matrix */
    private int m = 40;

    public int getN() {
        return n;
    }

    public int getP() {
        return p;
    }

    public int getM() {
        return m;
    }

    /**
     * Method for filling matrix with random values
     * @param matrix random matrix
     */
    public void randomMatrix(int[][] matrix) {
        final Random random = new Random();
        for (int row = 0; row < matrix.length; ++row)
            for (int col = 0; col < matrix[row].length; ++col)
                matrix[row][col] = random.nextInt(50);
    }

    /**
     * Method for writing a matrix to a file
     * @param matrix the matrix
     */
    public void writeMatrixToFile(int[][] matrix, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (int[] ints : matrix) {
            for (int int_ : ints) {
                fileWriter.write(int_ + "  ");
            }
            fileWriter.write("\n");
            fileWriter.flush();
        }
        fileWriter.close();
    }
}