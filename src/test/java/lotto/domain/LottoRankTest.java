package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoRankTest {
    @Test
    void 당첨번호_6개_일치_1등_테스트(){
        LottoRank rank=LottoRank.result(6,false);
        Assertions.assertThat(rank).isEqualTo(LottoRank.FIRST);
        Assertions.assertThat(rank.getPrize()).isEqualTo(2000000000);
    }

    @Test
    void 당첨번호_5개_일치_및_보너스번호_일치_테스트(){
        LottoRank rank=LottoRank.result(5,true);
        Assertions.assertThat(rank).isEqualTo(LottoRank.SECOND);
        Assertions.assertThat(rank.getPrize()).isEqualTo(30000000);
    }

    @Test
    void 당첨번호_5개_일치_및_보너스번호_불일치_테스트(){
        LottoRank rank=LottoRank.result(5,false);
        Assertions.assertThat(rank).isEqualTo(LottoRank.THIRD);
        Assertions.assertThat(rank.getPrize()).isEqualTo(1500000);
    }

    @Test
    void 당첨번호_4개_일치_테스트(){
        LottoRank rank=LottoRank.result(4,false);
        Assertions.assertThat(rank).isEqualTo(LottoRank.FOURTH);
        Assertions.assertThat(rank.getPrize()).isEqualTo(50000);
    }

    @Test
    void 당첨번호_3개_일치_테스트(){
        LottoRank rank=LottoRank.result(3,false);
        Assertions.assertThat(rank).isEqualTo(LottoRank.FIFTH);
        Assertions.assertThat(rank.getPrize()).isEqualTo(5000);
    }

    @Test
    void 낙점_테스트(){
        LottoRank rank1=LottoRank.result(2,false);
        LottoRank rank2=LottoRank.result(0,false);

        Assertions.assertThat(rank1).isEqualTo(LottoRank.MISS);
        Assertions.assertThat(rank2).isEqualTo(LottoRank.MISS);
        Assertions.assertThat(rank1.getPrize()).isEqualTo(0);
    }

}