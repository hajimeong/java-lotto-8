package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoResultCalculatorTest {
    private LottoResultCalculator calculator;
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        //당첨번호:1,2,3,4,5,6 + 보너스번호:7
        Lotto winning = new Lotto(List.of(1,2,3,4,5,6));
        int bonusNumber=7;

        winningLotto=new WinningLotto(winning,bonusNumber);
    }

    @Test
    void 모든_로또_낙점_테스트(){
        calculator=new LottoResultCalculator();

        List<Lotto> purchaseLottos=List.of(
                new Lotto(List.of(10,11,12,13,14,15)),
                new Lotto(List.of(30,31,32,33,34,35))
        );

        LottoResult result=calculator.calculateResult(purchaseLottos,winningLotto);

        Assertions.assertThat(result.getCount(LottoRank.MISS)).isEqualTo(2);
        Assertions.assertThat(result.calculateTotalPrize()).isEqualTo(0);
    }

    @Test
    void 예제_등수_결과_테스트(){
        calculator=new LottoResultCalculator();
        List<Lotto>purchaseLottos=List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );

        LottoResult result=calculator.calculateResult(purchaseLottos,winningLotto);

        Assertions.assertThat(result.getCount(LottoRank.MISS)).isEqualTo(7);
        Assertions.assertThat(result.getCount(LottoRank.FIFTH)).isEqualTo(1);
        Assertions.assertThat(result.getCount(LottoRank.FOURTH)).isEqualTo(0);
        Assertions.assertThat(result.getCount(LottoRank.THIRD)).isEqualTo(0);
        Assertions.assertThat(result.getCount(LottoRank.SECOND)).isEqualTo(0);
        Assertions.assertThat(result.getCount(LottoRank.FIRST)).isEqualTo(0);

        Assertions.assertThat(result.calculateTotalPrize()).isEqualTo(5000);
    }

    @Test
    void 당첨_1등_테스트(){
        calculator=new LottoResultCalculator();
        List<Lotto>purchaseLottos=List.of(
                new Lotto(List.of(1,2,3,4,5,6))
        );

        LottoResult result=calculator.calculateResult(purchaseLottos,winningLotto);

        Assertions.assertThat(result.getCount(LottoRank.FIRST)).isEqualTo(1);
        Assertions.assertThat(result.calculateTotalPrize()).isEqualTo(LottoRank.FIRST.getPrize());
    }

    @Test
    void 당첨_2등_3등_테스트(){
        calculator=new LottoResultCalculator();
        List<Lotto>purchaseLottos=List.of(
                new Lotto(List.of(1,2,3,4,5,7)), //2등 당첨
                new Lotto(List.of(1,2,3,4,5,10)) //3등 당첨
        );

        LottoResult result=calculator.calculateResult(purchaseLottos,winningLotto);

        Assertions.assertThat(result.getCount(LottoRank.SECOND)).isEqualTo(1);
        Assertions.assertThat(result.getCount(LottoRank.THIRD)).isEqualTo(1);
    }
}