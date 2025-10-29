package lotto.service;

import lotto.domain.Lotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LottoGeneratorTest {

    @Test
    void 금액에_맞는_수량의_로또_생성_테스트(){
        LottoGenerator lottoGenerator = new LottoGenerator();

        List<Lotto> lottos1= lottoGenerator.generator(14000);
        List<Lotto> lottos2 = lottoGenerator.generator(8000);

        Assertions.assertThat(lottos1).hasSize(14);
        Assertions.assertThat(lottos2).hasSize(8);
    }

    @Test
    void 생성된_로또번호가_6개의_숫자를_가지는지_테스트(){
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> lottos=lottoGenerator.generator(8000);

        for(Lotto lotto:lottos){
            Assertions.assertThat(lotto.getNumbers()).hasSize(6);
        }
    }

    @Test
    void 생성된_로또번호가_1과_45사이의_숫자인지_테스트(){
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> lottos=lottoGenerator.generator(14000);

        for(Lotto lotto:lottos){
            for(int number:lotto.getNumbers()){
                Assertions.assertThat(number).isBetween(1,45);
            }
        }
    }

    @Test
    void 생성된_로또번호가_중복되지는_않는지_테스트(){
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> lottos=lottoGenerator.generator(8000);

        for(Lotto lotto:lottos){
            List<Integer>numbers=lotto.getNumbers();
            long distinctCount=numbers.stream().distinct().count();

            Assertions.assertThat(distinctCount).isEqualTo(lotto.getNumbers().size());
        }
    }
}