package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    private static final int LOTTO_PRICE=1000;

    //금액에 맞는 수량의 로또 생성
    public List<Lotto> generator(int price){
        int amount = calculateAmount(price);
        List<Lotto>lottos=new ArrayList<Lotto>();

        for(int i=0;i<amount;i++){
            lottos.add(generateSingleLotto());
        }
        return lottos;
    }

    public int getAmount(int price){return calculateAmount(price);}

    private int calculateAmount(int price){
        return price/LOTTO_PRICE;
    }

    //하나의 로또 번호 생성 -> 랜덤
    private Lotto generateSingleLotto(){
        List<Integer> numbers= Randoms.pickUniqueNumbersInRange(1,45,6);
        return new Lotto(numbers);
    }
}
