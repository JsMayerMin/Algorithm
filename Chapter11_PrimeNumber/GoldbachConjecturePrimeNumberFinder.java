package Chapter11_PrimeNumber;

import java.util.*;

public class GoldbachConjecturePrimeNumberFinder {

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

    /*
    골드바흐의 추측은 유명한 정수론의 미해결 문제로,
    2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다는 것이다.
    이러한 숫자를 골드바흐 숫자라고 한다.
    또, 짝수를 두 소수의 합으로 나타내는 표현을 그 숫자의 골드바흐 파티션이라고 한다.
     예를 들면, 4 = 2 + 2, 6 = 3 + 3, 8 = 3 + 5, 10 = 5 + 5, 12 = 5 + 7, 14 = 3 + 11, 14 = 7 + 7이다.
     10000보다 작은 모든 짝수 n에 대한 골드바흐 파티션은 존재한다.
    */
    public static void solve(String[] args) {
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
                    GoldbachPartition goldbachPartition = new GoldbachPartition(i, inputNum-i);
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
