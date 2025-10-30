package lotto.util;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser {

    //String을 Integer로 변환(구입금액, 보너스번호)
    public static int ParsingToInteger(String input) {
        return Integer.parseInt(input);
    }

    //입력받은 당첨 번호 문자열을 정수 리스트로 변환
    //정규식을 활용해 쉼표 기준으로 split하고, 숫자로 변환
    public static List<Integer> parsingToIntegerList(String lottoNumbers){
        return Pattern.compile("\\s*,\\s*")
                .splitAsStream(lottoNumbers.trim())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
