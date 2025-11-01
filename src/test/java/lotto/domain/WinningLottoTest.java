package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    @Test
    void 당첨번호와_보너스번호_정상적으로_저장(){
        Lotto winningNumbers=new Lotto(List.of(1,2,3,4,5,6));
        int bonusNumber=7;

        WinningLotto winningLottoSet=new WinningLotto(winningNumbers,bonusNumber);

        Assertions.assertThat(winningLottoSet.getWinningLotto().getNumbers()).containsExactly(1,2,3,4,5,6);
        Assertions.assertThat(winningLottoSet.getBonusNumber()).isEqualTo(7);
    }

    @Test
    void 동일한_Lotto_인스턴스를_참조하는지_테스트(){
        Lotto lotto=new Lotto(List.of(1,2,3,4,5,6));
        WinningLotto winningLotto=new WinningLotto(lotto,7);

        Assertions.assertThat(winningLotto.getWinningLotto()).isSameAs(lotto);
    }
}