/**
 * Class which is describing operations for multithreaded matrix multiplication
 * @see MatrixUtils
 */
public class MatrixMultiplication extends Thread {

    /** 1st matrix */
    private final int[][] firstMatrix;

    /** 2nd matrix */
    private final int[][] secondMatrix;

    /** The result of multiplying two matrices */
    private final int[][] resultMatrix;

    /** Start index */
    private final int startIndex;

    /** End index */
    private final int endIndex;

    /** Constructor with parameters
     * @param firstMatrix  First matrix
     * @param secondMatrix Second matrix
     * @param resultMatrix The result of multiplying two matrices
     * @param startIndex   Start index
     * @param endIndex    End index
     */
    public MatrixMultiplication(int[][] firstMatrix, int[][] secondMatrix, int[][] resultMatrix,
                                             int startIndex, int endIndex) {
        this.firstMatrix  = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * Calculating a value in a cell
     * @param row Row number
     * @param col Column number
     */
    private void calcValue(int row, int col) {
        int sum = 0;
        for (int i = 0; i < secondMatrix.length; ++i)
            sum += firstMatrix[row][i] * secondMatrix[i][col];
        resultMatrix[row][col] = sum;
    }

    /** Thread func */
    @Override
    public void run() {
        System.out.println(getName() + " started.");
        for (int index = startIndex; index < endIndex; ++index)
            calcValue(index / secondMatrix[0].length, index % secondMatrix[0].length);
        System.out.println(getName() + " finished.");
    }

    /**
     * Multithreaded matrix multiplication
     * @param firstMatrix First matrix
     * @param secondMatrix Second matrix
     * @param threadCount Number of threads
     * @return result resulting matrix
     */
    public static int[][] multiplyMatrix(int[][] firstMatrix,int[][] secondMatrix, int threadCount) {

        int rowCount = firstMatrix.length;
        int colCount = secondMatrix[0].length;
        int[][] result = new int[rowCount][colCount];

        int cellsForThread = (rowCount * colCount) / threadCount;
        int startIndex = 0;
        MatrixMultiplication[] multiplierThreads = new MatrixMultiplication[threadCount];

        for (int threadIndex = threadCount - 1; threadIndex >= 0; threadIndex--) {
            int endIndex = startIndex + cellsForThread;
            if (threadIndex == 0) {
                endIndex = rowCount * colCount;
            }
            multiplierThreads[threadIndex] = new MatrixMultiplication(firstMatrix, secondMatrix, result, startIndex, endIndex);
            multiplierThreads[threadIndex].start();
            startIndex = endIndex;
        }

        try {
            for (final MatrixMultiplication multiplierThread : multiplierThreads)
                multiplierThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
