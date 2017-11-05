package Chapter11_PrimeNumber;

import java.util.*;

public class EratosthenesPrimeNumberFinder {

    //프라임넘버의 아닌 수의 공배수는 소수가 아니다
    //에라토스테네스의 체
    //Boolean list를 이용한 방식도 사용할 수 있다.
    public static void solve(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> inputNumbers = new ArrayList<>();

        int firstNumber = validateInput(sc.nextInt());
        int secondNumber = validateInput(sc.nextInt());
        if (firstNumber > secondNumber)
            throw new IllegalArgumentException();

        List<Boolean> primeFinderList = makePrimeNumberFinder(secondNumber);
        for (int i = firstNumber; i <= secondNumber; i++) {
            if (primeFinderList.get(i)) {
                System.out.println(i);
            }
        }
    }


    private static int validateInput(int inputNum) {
        if (inputNum > 1000000 && inputNum < 0)
            throw new IllegalArgumentException();
        return inputNum;
    }

    public static List<Boolean> makePrimeNumberFinder(int endNum) {

        List<Boolean> isPrimeNumberList = new ArrayList<>();

        for (int i = 0; i <= endNum; i++) {
            if (i <= 1)
                isPrimeNumberList.add(false);
            else
                isPrimeNumberList.add(true);
        }

        for (int i = 2; i <= endNum; i++) {
            if (isPrimeNumberList.get(i)) {
                for (int j = 2; i * j <= endNum; j++) {
                    isPrimeNumberList.set(j * i, false);
                }
            }
        }
        return isPrimeNumberList;
    }
}
