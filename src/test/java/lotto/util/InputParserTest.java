package lotto.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class InputParserTest {
    @Test
    void 문자열_정수_변환_테스트(){
        Assertions.assertEquals(8000, InputParser.ParsingToInteger("8000"));
        Assertions.assertEquals(7, InputParser.ParsingToInteger("7"));
    }

    @Test
    void 당첨번호_정수_리스트_변환_테스트(){
        List<Integer> result = InputParser.parsingToIntegerList("1,2,3,4,5,6");
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5, 6), result);
    }
}