package lotto.domain;

//당첨 번호와 보너스 번호를 관리하는 클래스
public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
    public int getBonusNumber() {
        return bonusNumber;
    }
}
