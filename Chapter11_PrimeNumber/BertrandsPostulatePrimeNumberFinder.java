package Chapter11_PrimeNumber;

import java.util.*;

public class BertrandsPostulatePrimeNumberFinder {

    /*
    베르트랑 공준은 임의의 자연수 n에 대하여, n보다 크고, 2n보다 작거나 같은 소수는 적어도 하나 존재한다는 내용을 담고 있다.
    이 명제는 조제프 베르트랑이 1845년에 추측했고, 파프누티 체비쇼프가 1850년에 증명했다.
    예를 들어, 10보다 크고, 20보다 작거나 같은 소수는 4개가 있다. (11, 13, 17, 19) 또, 14보다 크고, 28보다 작거나 같은 소수는 3개가 있다. (17,19, 23)
    n이 주어졌을 때, n보다 크고, 2n보다 작거나 같은 소수의 개수를 구하는 프로그램을 작성하시오.
     */
    //프라임넘버의 아닌 수의 공배수는 소수가 아니다
    //에라토스테네스의 체
    //Boolean list를 이용한 방식도 사용할 수 있다.
    public static void solve(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> inputNumbers = new ArrayList<>();

        while(true) {
            int inputNum = sc.nextInt();
            if(inputNum == 0)
                break;
            else
                inputNumbers.add(inputNum);
        }

        int maxInput = inputNumbers.stream().mapToInt(x->x).max().getAsInt();

        List<Boolean> primeFinderList = makePrimeNumberFinder(maxInput*2);

        for (Integer inputNum : inputNumbers) {
            int primeCnt = 0;
            for(int i = inputNum + 1 ; i <= inputNum *2 ; i++) {
                if(primeFinderList.get(i)) {
                    primeCnt++;
                }
            }
            System.out.println(primeCnt);
        }

    }

    private static int validateInput(int inputNum) {
        if (inputNum > 123456 && inputNum < 0)
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
