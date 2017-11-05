
import java.util.*;

public class Main {

    public static class GoldbachPartition {
        int firstPrimePart;
        int secondPrimePart;

        public int getAbsDiff() {
            return Math.abs(firstPrimePart - secondPrimePart);
        }

        public GoldbachPartition (int firstPrime, int secondPrime) {
            this.firstPrimePart = firstPrime;
            this.secondPrimePart = secondPrime;
        }

        @Override
        public String toString() {
            return firstPrimePart + " " + secondPrimePart;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> inputNumbers = new ArrayList<>();
        int problemNum = sc.nextInt();


        for(int i = 0; i < problemNum; i++) {
            inputNumbers.add(validateInput(sc.nextInt()));
        }

        int maxInput = inputNumbers.stream().mapToInt(x->x).max().getAsInt();
        List<Boolean> primeFinderList = makePrimeNumberFinder(maxInput);
        Map<Integer, GoldbachPartition> answers = new HashMap<>();

        for (Integer inputNum : inputNumbers) {
            for(int i = 2; i <= inputNum / 2; i++) {
                if(primeFinderList.get(i) && primeFinderList.get(inputNum - i)) {
                    GoldbachPartition goldbachPartition =
                            new GoldbachPartition(i, inputNum-i);
                    GoldbachPartition oldPartition = answers.get(inputNum);
                    if (oldPartition == null) {
                        answers.put(inputNum, goldbachPartition);
                    } else if (goldbachPartition.getAbsDiff() < oldPartition.getAbsDiff()) {
                        answers.put(inputNum, goldbachPartition);
                    }
                }
            }
            System.out.println(answers.get(inputNum).toString());
        }


    }

    private static int validateInput(int inputNum) {
        if (inputNum > 10000 && inputNum < 4)
            throw new IllegalArgumentException();
        return inputNum;
    }

    public static List<Boolean> makePrimeNumberFinder(int endNum) {

        List<Boolean> isPrimeNumberList = new ArrayList<>();

        for(int i = 0; i <= endNum; i++) {
            if( i <= 1)
                isPrimeNumberList.add(false);
            else
                isPrimeNumberList.add(true);
        }

        for(int i = 2; i <= endNum; i++) {
            if(isPrimeNumberList.get(i)) {
                for(int j = 2; i*j <= endNum; j++) {
                    isPrimeNumberList.set(j * i, false);
                }
            }
        }
        return isPrimeNumberList;
    }
}
