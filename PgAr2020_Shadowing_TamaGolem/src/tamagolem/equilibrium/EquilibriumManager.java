package tamagolem.equilibrium;

import java.util.concurrent.ThreadLocalRandom;

import tamagolem.Element;

public class EquilibriumManager {
    private static final int NUMBER_OF_ELEMENTS = Element.values().length;
    private static final int V = 10;

    // The matrix which saves the values of the interactions between the elements
    private static int[][] equilibriumMatrix = new int[NUMBER_OF_ELEMENTS][NUMBER_OF_ELEMENTS];

    /**
     * Initializes the equilibrium matrix with all 0's
     */
    private static void initializeMatrix() {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                equilibriumMatrix[i][j] = 0;
            }
        }
    }

    /**
     * Shows on the CMD the equilibrium matrix
     */
    public static void showMatrix() {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                System.out.print(equilibriumMatrix[i][j] + "\t");
            }

            System.out.print("\n\n");
        }
    }

    /**
     * Calculates the equilibrium and the values of the interactions between the
     * elements
     */
    public static void calcEquilibrium() {
        initializeMatrix();

        for (int i = 0; i < NUMBER_OF_ELEMENTS - 1; i++) {
            int totalCount = 0;

            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                // Set the max attack at half of health
                int maxAttack = (V / 2);

                // The value on the main diagonal must be 0
                if (i == j) {
                    continue;
                }

                /**
                 * If the value in the specified cell is already setted, the algorithm don't
                 * touch it and saves its value
                 */
                if (equilibriumMatrix[i][j] != 0) {
                    totalCount += equilibriumMatrix[i][j];
                    continue;
                }

                /**
                 * On the last column the algorithm set a value which is the complementary of
                 * the others, a number which make the total sum of the row equals to 0
                 */
                if (j == NUMBER_OF_ELEMENTS - 1) {
                    equilibriumMatrix[i][j] = -totalCount;
                    equilibriumMatrix[j][i] = totalCount;

                    if (totalCount >= V || totalCount <= -V) {
                        calcEquilibrium();
                    }

                    break;
                }

                // Generates a random value of attack
                int value = ThreadLocalRandom.current().nextInt(1, maxAttack);
                int choice = ThreadLocalRandom.current().nextInt(0, 2);

                // If the value is too big change its sign
                if (choice == 0 && totalCount + value >= V) {
                    choice = 1;
                }

                // If the value is too small change its sign
                if (choice == 1 && totalCount - value <= -V) {
                    choice = 0;
                }

                // On the second last columns does more controls on the generated value
                if (j == NUMBER_OF_ELEMENTS - 2) {
                    int multiplier = choice == 0 ? 1 : -1;

                    if (i == NUMBER_OF_ELEMENTS - 3) {
                        int columnSum = getColumnSum(j);

                        // If the sum of the column is positive, the value must be negative
                        if (columnSum < 0) {
                            choice = 0;
                            multiplier = 1;
                        } else {
                            choice = 1;
                            multiplier = -1;
                        }

                        // Search for the smallest suitable value
                        for (int x = maxAttack - 1; x > 0; x--) {
                            if (totalCount + (multiplier * x) != 0 && columnSum + (multiplier * x) != 0) {
                                value = x;
                            }
                        }

                    }

                    /**
                     * The generated value can't added with the sum of the row can't be 0, otherwise
                     * there will be a 0 on the last column
                     */
                    while (totalCount + (multiplier * value) == 0) {
                        value = ThreadLocalRandom.current().nextInt(1, maxAttack);
                    }
                }

                // Add the generated value in the equilibrium matrix
                if (choice == 0) {
                    equilibriumMatrix[i][j] = value;
                    equilibriumMatrix[j][i] = -value;

                    totalCount += value;
                } else {
                    equilibriumMatrix[i][j] = -value;
                    equilibriumMatrix[j][i] = value;

                    totalCount -= value;
                }
            }
        }
    }

    /**
     * Get the sum of the values on the specified column
     * 
     * @return the sum
     */
    private static int getColumnSum(int c) {
        int sum = 0;

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            sum += equilibriumMatrix[i][c];
        }

        return sum;
    }

    /**
     * Get the value of the interaction between two elements. If el1 is stronger a
     * positive value will be returned, a negative one otherwise
     * 
     * @param el1
     * @param el2
     * @return the damage (value of the interaction)
     */
    public static int getDamage(Element el1, Element el2) {
        if (equilibriumMatrix[0][1] == 0) {
            calcEquilibrium();
        }

        return equilibriumMatrix[el1.ordinal()][el2.ordinal()];
    }
}