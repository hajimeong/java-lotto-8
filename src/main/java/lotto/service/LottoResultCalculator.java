package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;

import java.util.List;

public class LottoResultCalculator {

    public LottoResult calculateResult(List<Lotto> purchaseLottos, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();

        for(Lotto lotto : purchaseLottos) {
            int matchCount=getMatchResult(lotto, winningLotto);
            boolean matchBonus=lotto.getNumbers().contains(winningLotto.getBonusNumber());

            LottoRank rank= LottoRank.result(matchCount, matchBonus);
            lottoResult.addResult(rank);
        }

        return lottoResult;
    }

    private int getMatchResult(Lotto lotto, WinningLotto winningLotto) {
        return (int) lotto.getNumbers().stream()
                .filter(winningLotto.getWinningLotto().getNumbers()::contains)
                .count();
    }
}
