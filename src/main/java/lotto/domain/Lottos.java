package lotto.domain;

import java.util.List;

//생성된 로또들 보관
public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos(){
        return lottos;
    }

}
