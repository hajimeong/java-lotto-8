package lotto.util.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BonusNumberValidatorTest {
    @Test
    void 보너스번호_정상_처리_테스트(){
        List<Integer> winningLotto=List.of(1,2,3,4,5,6);

        Assertions.assertDoesNotThrow(()->BonusNumberValidator.validateBonusNumber(7,winningLotto));
        Assertions.assertDoesNotThrow(()->BonusNumberValidator.validateBonusNumber(45,winningLotto));
        Assertions.assertDoesNotThrow(()->BonusNumberValidator.validateBonusNumber(11,winningLotto));
    }

    @Test
    void 보너스번호_범위_벗어났을때_예외_처리(){
        List<Integer> winningLotto=List.of(1,2,3,4,5,6);

        IllegalArgumentException exception1=Assertions.assertThrows(IllegalArgumentException.class,
                ()->BonusNumberValidator.validateBonusNumber(0,winningLotto));
        Assertions.assertEquals("[ERROR] 보너스 번호가 로또 번호 범위를 벗어났습니다.(1~45)", exception1.getMessage());

        IllegalArgumentException exception2 =Assertions.assertThrows(IllegalArgumentException.class,
                ()->BonusNumberValidator.validateBonusNumber(46,winningLotto));
        Assertions.assertEquals("[ERROR] 보너스 번호가 로또 번호 범위를 벗어났습니다.(1~45)", exception2.getMessage());
    }

    @Test
    void 보넌스번호_당첨번호와_중복(){
        List<Integer> winningLotto=List.of(1,2,3,4,5,6);

        IllegalArgumentException exception=Assertions.assertThrows(IllegalArgumentException.class,
                ()->BonusNumberValidator.validateBonusNumber(1,winningLotto));

        Assertions.assertEquals("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.", exception.getMessage());
    }

}