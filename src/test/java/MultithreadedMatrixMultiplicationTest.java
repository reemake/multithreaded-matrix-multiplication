import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MultithreadedMatrixMultiplicationTest {

    MatrixUtils matrixUtils;
    int[][] firstMatrix;
    int[][] secondMatrix;

    @BeforeEach
    void setUp() {
        matrixUtils = new MatrixUtils();
        firstMatrix = new int[matrixUtils.getN()][matrixUtils.getP()];
        secondMatrix = new int[matrixUtils.getP()][matrixUtils.getM()];
        matrixUtils.randomMatrix(firstMatrix);
        matrixUtils.randomMatrix(secondMatrix);
    }

    @Test
    void multithreadedMatrixMultiplicationTest() throws IOException {
        matrixUtils.writeMatrixToFile(firstMatrix,new File("src/main/resources/FirstMatrix.scv"));
        matrixUtils.writeMatrixToFile(secondMatrix,new File("src/main/resources/SecondMatrix.scv"));

        int[][] result = MatrixMultiplication.multiplyMatrix(firstMatrix, secondMatrix, Runtime.getRuntime().availableProcessors());
        matrixUtils.writeMatrixToFile(result, new File("src/main/resources/ResultMatrix.scv"));

        assertNotNull(result);
    }

}