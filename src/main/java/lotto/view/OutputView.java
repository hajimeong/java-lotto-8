package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    //구입한 로또의 수량 출력
    public void printAmount(int amount){
        System.out.println(amount+"개를 구매했습니다.");
    }

    //생성된 로또 리스트 출력
    public void printLottoNumbers(List<Lotto> lottoNumbers){
        for(Lotto lotto:lottoNumbers){
            List<Integer> numbers = lotto.getNumbers();
            String formattedNumbers=numbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("["+formattedNumbers+"]");
        }
    }

   //당첨 통계 출력
    public void printLottoResult(LottoResult  lottoResult, int purchaseAmount){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        Map<LottoRank, Integer> result = lottoResult.getResult();

        for(LottoRank rank:LottoRank.values()){
            if(rank==LottoRank.MISS){
                continue;
            }

            int count=result.get(rank);
            StringBuilder resultLine=new StringBuilder();

            resultLine.append(rank.getMatchCount())
                    .append("개 일치");

            if(rank==LottoRank.SECOND){
                resultLine.append(", 보너스 볼 일치");
            }

            String formattedPrize=String.format("%,d",rank.getPrize());

            resultLine.append(" (")
                    .append(formattedPrize)
                    .append("원) - ")
                    .append(count)
                    .append("개");

            System.out.println(resultLine);
        }

        double profitRate=lottoResult.calculateRate(purchaseAmount);
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}
