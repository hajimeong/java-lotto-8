package lotto.util.validator;

import lotto.domain.Lotto;

public class BonusNumberValidator {
    private static final int MIN_NUMBER=1;
    private static final int MAX_NUMBER=45;

    //보너스 번호가 범위에 맞는지, 당첨 번호와 중복되지는 않는지 검증
    public static void validateBonusNumber(int bonusNumber, Lotto winningLotto){
        validateRange(bonusNumber);
        validateDuplicate(bonusNumber, winningLotto);
    }

    //보너스 번호가 로또 번호 범위에 맞는지 검증
    private static void validateRange(int bonusNumber){
        if(bonusNumber<MIN_NUMBER||bonusNumber>MAX_NUMBER){
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 로또 번호 범위를 벗어났습니다.(1~45)");
        }
    }

    //당첨 번호와 중복되지는 않는지 검증
    private static void validateDuplicate(int bonusNumber, Lotto winningLotto){
        if(winningLotto.getNumbers().contains(bonusNumber)){
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
