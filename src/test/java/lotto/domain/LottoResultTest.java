package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoResultTest {

    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        lottoResult = new LottoResult();
    }

    @Test
    void 등수별_결과_정상_추가_테스트(){
        lottoResult.addResult(LottoRank.FIRST);
        lottoResult.addResult(LottoRank.FIFTH);
        lottoResult.addResult(LottoRank.FIFTH);
        lottoResult.addResult(LottoRank.THIRD);

        Assertions.assertThat(lottoResult.getCount(LottoRank.FIRST)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getCount(LottoRank.THIRD)).isEqualTo(1);
        Assertions.assertThat(lottoResult.getCount(LottoRank.FIFTH)).isEqualTo(2);
    }

    @Test
    void 당첨금_계산_테스트(){
        lottoResult.addResult(LottoRank.FIRST);//2,000,000,000
        lottoResult.addResult(LottoRank.FIFTH);//5000
        lottoResult.addResult(LottoRank.FIFTH);//5000

        long totalPrize= lottoResult.calculateTotalPrize();

        Assertions.assertThat(totalPrize).isEqualTo(2000010000);
    }

    @Test
    void 수익률_정상_계산_테스트(){
        lottoResult.addResult(LottoRank.FIFTH);
        int purchaseAmount=5000;

        double rateResult = lottoResult.calculateRate(purchaseAmount);

        Assertions.assertThat(rateResult).isEqualTo(100.0);
    }

    @Test
    void 수익률_정상_계산_테스트2(){
        lottoResult.addResult(LottoRank.FIFTH);
        int purchaseAmount=8000;

        double rateResult = lottoResult.calculateRate(purchaseAmount);

        Assertions.assertThat(rateResult).isEqualTo(62.5);
    }

    @Test
    void 수익률이_없을때_수익률_계산(){
        lottoResult.addResult(LottoRank.MISS);
        int purchaseAmount=1000;
        double rateResult = lottoResult.calculateRate(purchaseAmount);

        Assertions.assertThat(rateResult).isEqualTo(0.0);
    }
}