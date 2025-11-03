package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.service.LottoGenerator;
import lotto.service.LottoResultCalculator;
import lotto.util.InputParser;
import lotto.util.validator.BonusNumberValidator;
import lotto.util.validator.InputValidator;
import lotto.util.validator.PurchaseValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final LottoGenerator lottoGenerator;
    private static final String PURCHASE="구입 금액";
    private static final String BONUS="보너스 번호";

    public LottoController(){
        this.inputView = new InputView();
        this.lottoGenerator = new LottoGenerator();
    }

    public void run(){
        //구입금액 입력 및 검증
        int purchaseAmount=inputPurchaseAmount();

        //로또 구입 수량 및 수량에 따른 로또 생성
        int amount=lottoGenerator.getAmount(purchaseAmount);
        List<Lotto> purchaseLottos=lottoGenerator.generator(purchaseAmount);

        //구입수량 및 생성된 로또 번호 리스트 출력
        OutputView.printAmount(amount);
        OutputView.printLottoNumbers(purchaseLottos);

        //당첨번호 입력 및 검증
        Lotto winningLotto=inputWinningLotto();

        //보너스 번호 입력 및 검증
        int bonusNumber=inputBonusNumber(winningLotto);

        //당첨 번호와 보너스 번호에 따른 당첨 결과 계산
        WinningLotto winning=new WinningLotto(winningLotto,bonusNumber);
        LottoResult result=new LottoResultCalculator().calculateResult(purchaseLottos,winning);

        //당첨 결과 출력
        OutputView.printLottoResult(result, purchaseAmount);
    }

    //구입금액 입력 검증 ->구입 금액 검증-> 검증 오류 시 재입력/검증 통과 시 구입금액 integer로 변환
    private int inputPurchaseAmount(){
        while(true){
            try{
                String input=inputView.getPurchasePrice();
                InputValidator.validateDefault(input, PURCHASE);
                int purchasePrice = InputParser.ParsingToInteger(input);
                PurchaseValidator.validatePurchase(purchasePrice);
                return purchasePrice;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    //당첨 번호 입력 -> 입력 형식 검증 -> Lotto 형식 검증 -> 검증 오류 시 재입력/검증 통과 시 Lotto로 변환
    private Lotto inputWinningLotto(){
        while(true){
            try{
                String input=inputView.getWinningLotto();
                InputValidator.validateWinningLottoInput(input);
                List<Integer> inputLotto = InputParser.parsingToIntegerList(input);
                Lotto winningLotto=new Lotto(inputLotto);
                return winningLotto;
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    //보너스 번호 입력 -> input 검증 -> integer로 변환 -> 보너스 번호 검증 -> 보너스번호 반환
    private int inputBonusNumber(Lotto lottoNumbers){
        while(true){
            try{
                String input=inputView.getBonusNumber();
                InputValidator.validateDefault(input, BONUS);
                int bonusNumber = InputParser.ParsingToInteger(input);
                BonusNumberValidator.validateBonusNumber(bonusNumber,lottoNumbers);
                return bonusNumber;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
