package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class OutputViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private OutputView outputView;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        outputView = new OutputView();
    }

    @Test
    void 로또_번호_출력_테스트(){
        List<Lotto> lotts=List.of(
                new Lotto(List.of(1,2,3,4,5,6)),
                new Lotto(List.of(7,8,9,10,11,12))
        );

        outputView.printLottoNumbers(lotts);

        String output=outContent.toString().trim();

        Assertions.assertThat(output).contains("[1, 2, 3, 4, 5, 6]");
        Assertions.assertThat(output).contains("[7, 8, 9, 10, 11, 12]");
    }

    @Test
    void 당첨_통계_출력_테스트(){
        LottoResult lottoResult=new LottoResult();
        //lottoResult.addResult(LottoRank.FIRST);
        lottoResult.addResult(LottoRank.FIFTH);

        int purchaseAmount=8000;

        OutputView.printLottoResult(lottoResult,purchaseAmount);

        String output=outContent.toString();

        Assertions.assertThat(output).contains("당첨 통계");
        Assertions.assertThat(output).contains("---");
        Assertions.assertThat(output).contains("3개 일치 (5,000원) - 1개");
        Assertions.assertThat(output).contains("4개 일치 (50,000원) - 0개");
        Assertions.assertThat(output).contains("5개 일치 (1,500,000원) - 0개");
        Assertions.assertThat(output).contains("5개 일치, 보너스 볼 일치 (30,000,000원) - 0개");
        Assertions.assertThat(output).contains("6개 일치 (2,000,000,000원) - 0개");
        Assertions.assertThat(output).contains("총 수익률은");
    }

}