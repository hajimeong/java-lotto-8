package lotto.domain;

//로또의 일치 개수와 보너스 일치 여부에 따른 당첨 등수를 판별하는 클래스
public enum LottoRank {
    MISS(0, false,0),
    FIFTH(3,false,5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5,true, 30000000),
    FIRST(6,false,2000000000);

    private final int matchCount; //맞은 개수
    private final boolean matchBonus; //보너스 맞춤 유무
    private final int prize; //상금

    LottoRank(int matchCount, boolean matchBonus, int prize) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
    }

    public static LottoRank result(int matchCount, boolean matchBonus) {
        if(matchCount==6)return FIRST;
        if(matchCount==5&& matchBonus)return SECOND;
        if(matchCount==5)return THIRD;
        if(matchCount==4)return  FOURTH;
        if(matchCount==3)return  FIFTH;
        return MISS;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
