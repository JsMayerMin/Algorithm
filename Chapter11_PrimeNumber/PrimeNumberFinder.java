package Chapter11_PrimeNumber;

import java.util.*;

public class PrimeNumberFinder {

    private static Set<Integer> findPrimeNumbers = new HashSet<>();

    /*
    문제 : 주어진 숫자들 중 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.
    입력 : 첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.
    출력 : 주어진 수들 중 소수의 개수를 출력한다.
     */
    public void solveProblem() {
        Scanner sc = new Scanner(System.in);
        int totalNum = sc.nextInt();
        if(totalNum > 100 || totalNum < 0)
            throw new IllegalArgumentException();

        List<Integer> checkNumbers = new ArrayList<>();
        for (int i = 0; i < totalNum; i++) {
            int checkNum = sc.nextInt();
            if(checkNum > 1000 || checkNum < 0)
                throw new IllegalArgumentException();
            checkNumbers.add(checkNum);
        }

        int primeNumCnt = 0;
        for (Integer checkNum : checkNumbers) {
            if (isPrimeNumber(checkNum))
                primeNumCnt++;
        }

        System.out.println(primeNumCnt);
    }

    //에라토스 테네스의 체

    private boolean isPrimeNumber(int num) {
        if(findPrimeNumbers.contains(num))
            return true;

        if (num <= 1)
            return false;

        if (num % 2 == 0)
            return (num == 2);

        for (int i = 3; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        if(!findPrimeNumbers.contains(num))
            findPrimeNumbers.add(num);

        return true;
    }

}
