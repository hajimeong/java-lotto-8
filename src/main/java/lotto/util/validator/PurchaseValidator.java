package lotto.util.validator;

public class PurchaseValidator {
    private static final int LOTTO_PRICE=1000;

    //구입금액이 1000원 단위인지 검증
    public static void validatePurchase(int purchasePrice){
        validateUnit(purchasePrice);
    }

    private static void validateUnit(int amount){
        if(amount%LOTTO_PRICE!=0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
