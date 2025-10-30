package lotto.util.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void 정상_입력_테스트(){
        //구입금액
        Assertions.assertDoesNotThrow(()->InputValidator.validateDefault("1000", "구입금액"));
        //당첨번호
        Assertions.assertDoesNotThrow(()->InputValidator.validateWinningLottoInput("1,2,3,4,5,6"));
        //보너스번호
        Assertions.assertDoesNotThrow(()->InputValidator.validateDefault("7","보너스 번호"));
    }

    @Test
    void 입력이_비어있을때_예외_테스트(){
        //구입금액이 공백
        IllegalArgumentException exception1 =Assertions.assertThrows(IllegalArgumentException.class,
                ()->InputValidator.validateDefault(" ","구입금액"));
        Assertions.assertEquals("[ERROR] 구입금액을(를) 입력하지 않았습니다.", exception1.getMessage());

        //보너스 번호가 null
        IllegalArgumentException exception2 =Assertions.assertThrows(IllegalArgumentException.class,
                ()->InputValidator.validateDefault(null,"보너스 번호"));
        Assertions.assertEquals("[ERROR] 보너스 번호을(를) 입력하지 않았습니다.", exception2.getMessage());
    }

    @Test
    void 숫자가_아닌_문자입력_예외_테스트(){
        //구입금액
        IllegalArgumentException exception1 =Assertions.assertThrows(IllegalArgumentException.class,
                ()->InputValidator.validateDefault("12a4","구입금액"));
        Assertions.assertEquals("[ERROR] 구입금액에 숫자가 아닌 문자가 포함되어 있습니다.", exception1.getMessage());

        //보너스번호
        IllegalArgumentException exception2=Assertions.assertThrows(IllegalArgumentException.class,
                ()->InputValidator.validateDefault("a","보너스 번호"));
        Assertions.assertEquals("[ERROR] 보너스 번호에 숫자가 아닌 문자가 포함되어 있습니다.", exception2.getMessage());
    }

    @Test
    void 당첨번호_입력형식_예외_테스트(){
        //숫자가 아닌 문자가 들어간 경우
        IllegalArgumentException exception1 =Assertions.assertThrows(IllegalArgumentException.class,
                ()->InputValidator.validateWinningLottoInput("1,a,4,c,6,7"));
        Assertions.assertEquals("[ERROR] 당첨 번호 입력 형식에 맞지 않습니다.", exception1.getMessage());

        //쉼표 사이에 숫자가 없는 경우
        IllegalArgumentException exception2 =Assertions.assertThrows(IllegalArgumentException.class,
                ()->InputValidator.validateWinningLottoInput("1,,4,5,6,7"));
        Assertions.assertEquals("[ERROR] 당첨 번호 입력 형식에 맞지 않습니다.", exception2.getMessage());

        //쉼표가 아닌 다른 구분자를 사용한 경우
        IllegalArgumentException exception3 =Assertions.assertThrows(IllegalArgumentException.class,
                ()->InputValidator.validateWinningLottoInput("1.2,3,4,5,6"));
        Assertions.assertEquals("[ERROR] 당첨 번호 입력 형식에 맞지 않습니다.", exception3.getMessage());

    }
}