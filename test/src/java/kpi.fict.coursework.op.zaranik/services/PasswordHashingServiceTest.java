package src.java.kpi.fict.coursework.op.zaranik.services;

import kpi.fict.coursework.op.zaranik.services.passwordhashing.impl.PasswordHashingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordHashingServiceTest {

  @InjectMocks
  PasswordHashingServiceImpl passwordHashingService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void HashingTest() {
    //https://www.md5.cz/
    assertThat(passwordHashingService.hashPassword("max123")).isEqualTo("d1696816bc1a7afe92f1c8787ac222c3");
    assertThat(passwordHashingService.hashPassword("yana123")).isEqualTo("f120b1fbce5e71f228b8764c574455da");
    assertThat(passwordHashingService.hashPassword("admin")).isEqualTo("21232f297a57a5a743894a0e4a801fc3");
    assertThat(passwordHashingService.hashPassword("Max")).isEqualTo("6a061313d22e51e0f25b7cd4dc065233");
    assertThat(passwordHashingService.hashPassword("Max11")).isEqualTo("6c02971b090cccfcec5e0f2a88225358");
  }

  @Test
  void DeHashingTest() {
    //https://www.md5.cz/
    assertThat(passwordHashingService.checkPassword("max123", "d1696816bc1a7afe92f1c8787ac222c3")).isTrue();
    assertThat(passwordHashingService.checkPassword("yana123", "f120b1fbce5e71f228b8764c574455da")).isTrue();
    assertThat(passwordHashingService.checkPassword("admin", "21232f297a57a5a743894a0e4a801fc3")).isTrue();
    assertThat(passwordHashingService.checkPassword("Max", "6a061313d22e51e0f25b7cd4dc065233")).isTrue();
    assertThat(passwordHashingService.checkPassword("Max11", "6c02971b090cccfcec5e0f2a88225358")).isTrue();
    assertThat(passwordHashingService.checkPassword("Max11", "6c12971b090cccfcec5e0f2a88225358")).isFalse();
    assertThat(passwordHashingService.checkPassword("yana123", "f120b1fbce5e71f228b8764c574455yana")).isFalse();
  }
}
