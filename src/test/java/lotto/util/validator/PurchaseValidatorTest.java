package lotto.util.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseValidatorTest {
    PurchaseValidator validator = new PurchaseValidator();

    @Test
    void 구입금액_정상_입력_테스트(){
        Assertions.assertDoesNotThrow(()->validator.validatePurchase(14000));
        Assertions.assertDoesNotThrow(()->validator.validatePurchase(8000));
        Assertions.assertDoesNotThrow(()->validator.validatePurchase(1000));
    }

    @Test
    void 구입금액_1000원단위가_아닌_경우_예외_테스트(){
        IllegalArgumentException exception=Assertions.assertThrows(IllegalArgumentException.class, ()->{
            validator.validatePurchase(1500);
        });

        Assertions.assertEquals("[ERROR] 구입 금액은 1000원 단위여야 합니다.",exception.getMessage());
    }

}