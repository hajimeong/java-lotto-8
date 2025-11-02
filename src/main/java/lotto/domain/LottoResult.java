package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    //등수별 당첨 결과 저장
    private final Map<LottoRank, Integer>result=new EnumMap<>(LottoRank.class);

    //생성자 -> 모든 등수 초기화
    public LottoResult(){
        for(LottoRank rank:LottoRank.values()){
            result.put(rank,0);
        }
    }

    //특정 등수의 당첨 수를 1증가시키는 메서드
    public void addResult(LottoRank rank){
        result.put(rank, result.get(rank)+1);
    }

    //특정 등수의 당첨 개수 반환
    public int getCount(LottoRank rank){
        return result.getOrDefault(rank,0);
    }

    //전체 당첨 결과
    public Map<LottoRank,Integer> getResult(){
        return result;
    }

    //총 당첨금 합계 계산
    public long calculateTotalPrize(){
        long totalPrize=0;
        for(LottoRank rank:result.keySet()){
            totalPrize+=(long)rank.getPrize()*result.get(rank);
        }
        return totalPrize;
    }

    //수익률 계산
    public double calculateRate(int purchaseAmount){
        long totalPrize=calculateTotalPrize();

        if(purchaseAmount==0){
            return 0.0;
        }

        double rate=((double) totalPrize/purchaseAmount)*100;

        return Math.round(rate*100)/100.0;
    }
}
