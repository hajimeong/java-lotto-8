package lotto.util.validator;

public class InputValidator {
    private static final String SEPARATOR=",";

    //숫자인지, 공백인지 검증(구입금액, 보너스번호)
    public static void validateDefault(String input, String inputVariable){
        validateNotBlank(input, inputVariable);
        validateNumberInput(input, inputVariable);
    }

    //당첨 번호 입력 조건 검증 ex) [1,2,3,4,5,6]
    public static void validateWinningLottoInput(String input){
        if(!input.matches("([0-9]+)(,[0-9]+)*")){
            throw new IllegalArgumentException("[ERROR] 당첨 번호 입력 형식에 맞지 않습니다.");
        }
    }

    //입력이 비어있는지 검증
    private static void validateNotBlank(String input, String inputVariable){
        if(input==null||input.isBlank()){
            throw new IllegalArgumentException("[ERROR] "+inputVariable+"을(를) 입력하지 않았습니다.");
        }
    }

    //입력에 숫자가 아닌 문자가 들어있는지 검증
    private static void validateNumberInput(String input, String inputVariable){
        if(!isNum(input)){
            throw new IllegalArgumentException("[ERROR] "+inputVariable+"에 숫자가 아닌 문자가 포함되어 있습니다.");
        }
    }

    //숫자인지 확인
    private static boolean isNum(String input){
        return input.matches("[0-9]+");
    }
}