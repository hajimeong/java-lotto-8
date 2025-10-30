package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int MIN_NUMBER=1;
    private static final int MAX_NUMBER=45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private static void validateDuplicate(List<Integer>numbers){
        Set<Integer> set=new HashSet<>(numbers);
        if(set.size()!=numbers.size()){
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복되는 숫자가 존재합니다.");
        }
    }

    //로또 번호가 범위를 벗어났는지 검증
    private static void validateRange(List<Integer>numbers){
        for(Integer num:numbers){
            if(num<MIN_NUMBER||num>MAX_NUMBER){
                throw new IllegalArgumentException("[ERROR] 로또 번호가 범위를 벗어났습니다.(1~45)");
            }
        }
    }

    @Override
    public String toString() {
        return numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", ","[","]"));
    }
}
