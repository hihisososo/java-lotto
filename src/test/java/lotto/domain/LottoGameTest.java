package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoGameTest {

  @ParameterizedTest
  @DisplayName("로또 게임이 잘 생성되는지 확인")
  @ValueSource(strings = {"1,5,10,15,20,2", "5,11,18,20,35,45", "1,9,12,26,35,38"})
  void generate(String numbers) {
    LottoGame lottoGame = new LottoGame(numbers);

    assertThat(lottoGame).usingRecursiveComparison().isEqualTo(new LottoGame(numbers));
  }

  @ParameterizedTest
  @DisplayName("로또 추첨 결과 잘 가져오는지 확인")
  @CsvSource(value = {"1,5,10,15,20,2|1,2,10,40,44,45|3", "5,11,18,20,35,45|5,11,18,20,35,45|6",
      "1,9,12,26,35,38|38,35,11,12,9,1|5"}, delimiter = '|')
  void draw(String winNumbers, String lottoNumbers, int matchCount) {
    LottoGame lottoGame = new LottoGame(lottoNumbers);
    LottoDrawResult lottoDrawResult = lottoGame.draw(new LottoNumbers(winNumbers), null);

    assertThat(lottoDrawResult.isMatchCountEqual(matchCount)).isTrue();
    assertThat(lottoDrawResult.getReword()).isEqualTo(LottoReword.getWinMoney(matchCount,
        false));
    assertThat(lottoDrawResult.isMatchCountEqual(matchCount + 1)).isFalse();
  }

  @ParameterizedTest
  @DisplayName("보너스 번호가 포함된 로또 추첨 결과 잘 가져오는지 확인")
  @CsvSource(value = {"1,5,10,15,20,2|1,2,10,40,44,45|20|3",
      "5,11,18,20,35,45|5,11,18,20,35,45|44|6",
      "1,9,12,26,35,38|38,35,11,12,9,1|26|5"}, delimiter = '|')
  void draw(String winNumbers, String lottoNumbers, String bonusNumber, int matchCount) {
    LottoGame lottoGame = new LottoGame(lottoNumbers);
    LottoDrawResult lottoDrawResult = lottoGame
        .draw(new LottoNumbers(winNumbers), new LottoNumber(bonusNumber));

    assertThat(lottoDrawResult.isMatchCountEqual(matchCount)).isTrue();
    assertThat(lottoDrawResult.getReword()).isEqualTo(LottoReword.getWinMoney(matchCount,
        true));
    assertThat(lottoDrawResult.isMatchCountEqual(matchCount + 1)).isFalse();
  }
}