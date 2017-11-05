package Chapter11_PrimeNumber;

import java.util.*;

public class PrimeNumberFinder2 {

    private static Set<Integer> findPrimeNumbers = new HashSet<>();

    /*
    문제 : 자연수 M과 N이 주어질 때 M이상 N이하의 자연수 중 소수인 것을 모두 골라 이들 소수의 합과 최소값을 찾는 프로그램을 작성하시오.
    예를 들어 M=60, N=100이 경우 60이상 100이하의 자연수 중 소수는 61, 67, 71, 73, 79, 83, 89, 97 총 8개가 있으므로, 이들 소수의 합은 620이고, 최소값은 61이 된다.

    입력 : 입력의 첫째 줄에 M이, 둘째 줄에 N이 주어진다.
    M과 N은 10,000이하의 자연수이며, M은 N보다 같거나 작다.

    출력 : M이상 N이하의 자연수 중 소수인 것을 모두 찾아 첫째 줄에 그 합을, 둘째 줄에 그 중 최소값을 출력한다.
    단, M이상 N이하의 자연수 중 소수가 없을 경우는 첫째 줄에 -1을 출력한다.
     */
    public void solveProblem() {
        Scanner sc = new Scanner(System.in);
        int firstNumber = validateInput(sc.nextInt());
        int secondNumber = validateInput(sc.nextInt());
        if (firstNumber > secondNumber)
            throw new IllegalArgumentException();

        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = firstNumber; i <= secondNumber; i++) {
            if (isPrimeNumber(i))
                primeNumbers.add(i);
        }

        if (primeNumbers.size() == 0) {
            System.out.println(-1);
        } else {
            int sum = primeNumbers.stream().mapToInt(x -> x).sum();
            int min = primeNumbers.stream().mapToInt(x -> x).min().getAsInt();

            System.out.println(sum);
            System.out.println(min);
        }
    }

    private int validateInput(int inputNum) {
        if (inputNum > 10000 && inputNum < 0)
            throw new IllegalArgumentException();
        return inputNum;
    }

    private boolean isPrimeNumber(int num) {
        if (findPrimeNumbers.contains(num))
            return true;

        if (num <= 1)
            return false;

        if (num % 2 == 0)
            return (num == 2);

        for (int i = 3; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        if (!findPrimeNumbers.contains(num))
            findPrimeNumbers.add(num);

        return true;
    }

}
