/* 22112144_류효정
 *
 * 파일명: "PrimeNumber.java"
 * 프로그램의 목적 및 기본 기능:
 *   - 입력받은 문자열의 부분 문자열로 만들 수 있는 모든 소수 출력
 * 프로그램 작성자: 류효정 (2024년 09월 12일)
 * 최종 수정 및 보완: 류효정 (2024년 09월 14일)
 * =================================================================================================
 * 프로그램 수정/보완 이력
 * =================================================================================================
 * 프로그램 수정/보완작업자        일자                수정/보완 내용
 * 류효정                    2024.09.12         파일 입력 및 출력 완료
 * 류효정                    2024.09.14         출력 방식 변화 (소수 개수 출력 추가)
 * =================================================================================================
 */


import java.util.*;

public class PrimeNumber {

    int count = 0;
    HashSet<Integer> subset = new HashSet<>();
    Iterator<Integer> sub = subset.iterator();
    List<Integer> sortedSubset = new ArrayList<Integer>();

    //입력받은 문자열으로 생성 가능한 모든 숫자 부분집합(permutation)을 재귀함수를 통해 구성하고 subset에 저장함
    public void selectSubset (String use, String unuse){
        if (!use.isEmpty()){
            subset.add(Integer.valueOf(use)); //subset을 HashSet으로 생성해 중복 제거 (HashSet의 경우 HashMap과 달리 입력받은 값을 key로 받으므로 값 자체 중복 불가)
        }
        for (int i =0; i<unuse.length(); i++)
            selectSubset(use+unuse.charAt(i), unuse.substring(0,i)+unuse.substring(i+1));
    }

    public void findPrimeInSubset (HashSet<Integer> subset){
        for (int number : subset) {
            if (isPrime(number)) {
                sortedSubset.add(number);
                count++;
            }
        }
        Collections.sort(sortedSubset);
        //return sortedSubset;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false; // 1과 0은 소수가 아님
        if (n <= 3) return true;  // 2와 3은 소수임

        // 2와 3으로 나눠지는 경우 소수가 아님
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        // i부터 squrt(n)까지(에라토스테네스의 체), 예외 처리를 한 0123과 2로 나눠지는 경우인 4를 제외하고
        // 5부터 시작하여 6단위로 증가하면서 검사 (6k+2, 6k+5인 경우만 판별)
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {

        PrimeNumber driver = new PrimeNumber();

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter the number string: ");
        String numbers = scanner.nextLine();
        scanner.close();

        driver.selectSubset("",numbers);
        driver.findPrimeInSubset(driver.subset);

        System.out.println("Primes: " + driver.count + "개 " + driver.sortedSubset);
    }
}
