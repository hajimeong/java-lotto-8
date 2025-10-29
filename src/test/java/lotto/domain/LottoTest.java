package lotto.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정상_로또번호_저장(){
        List<Integer>numbers= Arrays.asList(8, 21, 23, 41, 42, 43);
        Lotto lotto = new Lotto(numbers);

        Assertions.assertEquals(numbers, lotto.getNumbers());
        Assertions.assertEquals("[8, 21, 23, 41, 42, 43]", lotto.toString());
    }

    @Test
    void 번호_개수가_6개가_아닌_경우_예외(){
        List<Integer>numbers= Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(numbers);
        });

        Assertions.assertEquals("[ERROR] 로또 번호는 6개여야 합니다.", exception.getMessage());
    }

    @Test
    void 문자열_출력시_정렬_확인(){
        List<Integer>numbers= Arrays.asList(42, 8, 23, 43, 21, 41);
        Lotto lotto = new Lotto(numbers);

        Assertions.assertEquals("[8, 21, 23, 41, 42, 43]", lotto.toString());
    }
}
