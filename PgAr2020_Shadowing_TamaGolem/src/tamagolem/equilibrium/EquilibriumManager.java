package tamagolem.equilibrium;

import java.util.concurrent.ThreadLocalRandom;

import tamagolem.Element;

public class EquilibriumManager {
    private static final int NUMBER_OF_ELEMENTS = Element.values().length;
    private static final int V = 10;

    private static int[][] equilibriumMatrix = new int[NUMBER_OF_ELEMENTS][NUMBER_OF_ELEMENTS];

    private static void initializeMatrix() {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                equilibriumMatrix[i][j] = 0;
            }
        }
    }

    private static void showMatrix() {
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                System.out.print(equilibriumMatrix[i][j] + "\t");
            }

            System.out.print("\n\n");
        }
    }

    public static void calcEquilibrium() {
        initializeMatrix();

        for (int i = 0; i < NUMBER_OF_ELEMENTS - 1; i++) {
            int totalCount = 0;

            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                int maxAttack = (V / 2);

                if (i == j) {
                    continue;
                }

                if (equilibriumMatrix[i][j] != 0) {
                    totalCount += equilibriumMatrix[i][j];
                    continue;
                }

                if (j == NUMBER_OF_ELEMENTS - 1) {
                    equilibriumMatrix[i][j] = -totalCount;
                    equilibriumMatrix[j][i] = totalCount;
                    break;
                }

                int value = ThreadLocalRandom.current().nextInt(1, maxAttack);
                int choice = ThreadLocalRandom.current().nextInt(0, 2);

                if (choice == 0 && totalCount + value >= V) {
                    choice = 1;
                }

                if (choice == 1 && totalCount - value <= -V) {
                    choice = 0;
                }

                if (j == NUMBER_OF_ELEMENTS - 2) {
                    int multiplier = choice == 0 ? 1 : -1;

                    if (i == NUMBER_OF_ELEMENTS - 3) {
                        int columnSum = getColumnSum(j);

                        if (columnSum < 0) {
                            choice = 0;
                            multiplier = 1;
                        } else {
                            choice = 1;
                            multiplier = -1;
                        }

                        /*
                         * while (totalCount + (multiplier * value) == 0 || columnSum + (multiplier *
                         * value) == 0) { value = ThreadLocalRandom.current().nextInt(1, maxAttack); }
                         */
                        for (int x = maxAttack - 1; x > 0; x--) {
                            if (totalCount + (multiplier * x) != 0 && columnSum + (multiplier * x) != 0) {
                                value = x;
                            }
                        }

                    }

                    while (totalCount + (multiplier * value) == 0) {
                        value = ThreadLocalRandom.current().nextInt(1, maxAttack);
                    }
                }

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

        // showMatrix();
    }

    private static int getColumnSum(int c) {
        int sum = 0;

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            sum += equilibriumMatrix[i][c];
        }

        return sum;
    }

    public static int getDamage(Element el1, Element el2) {
        return equilibriumMatrix[el1.ordinal()][el2.ordinal()];
    }

    public static int getSum() {
        int sum = 0;

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                sum += equilibriumMatrix[i][j];
            }
        }

        return sum;
    }

    public static int numberOfZeros() {
        int num = 0;

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                if (equilibriumMatrix[i][j] == 0) {
                    num++;
                }
            }
        }

        return num;
    }

    private static int max() {
        int max = 0;

        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENTS; j++) {
                if (equilibriumMatrix[i][j] > max) {
                    max = equilibriumMatrix[i][j];
                }
            }
        }

        return max;
    }

    public static void runTests(int numberOfTest) {
        int succesful = 0;
        int failed = 0;

        for (int i = 0; i < numberOfTest; i++) {

            calcEquilibrium();
            if (getSum() == 0 && numberOfZeros() == NUMBER_OF_ELEMENTS && max() <= V) {
                succesful++;
            } else {
                showMatrix();
                failed++;
                break;
            }

            if (i == numberOfTest / 4) {
                System.out.println("25%");
            }
            if (i == numberOfTest / 2) {
                System.out.println("50%");
            }
            if (i == (numberOfTest / 4) * 3) {
                System.out.println("75%");
            }
        }

        System.out.printf("%d succesful, %d failed", succesful, failed);
    }

    public static void main(String[] args) {
        calcEquilibrium();
        showMatrix();

        System.out.println(Element.WATER.getDamage(Element.ROCK));

        // runTests(1000000000);
    }
}