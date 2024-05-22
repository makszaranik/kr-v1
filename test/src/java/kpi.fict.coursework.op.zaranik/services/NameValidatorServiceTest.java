package src.java.kpi.fict.coursework.op.zaranik.services;

import kpi.fict.coursework.op.zaranik.services.namevalidator.impl.NameValidatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class NameValidatorServiceTest {

  @InjectMocks
  private NameValidatorServiceImpl nameValidatorService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testValidName() {
    assertThat(nameValidatorService.isValidName("MyQueue")).isTrue();
    assertThat(nameValidatorService.isValidName("queue")).isTrue();
    assertThat(nameValidatorService.isValidName("queue 1")).isTrue();
  }

  @Test
  void testValidNullName() {
    assertThat(nameValidatorService.isValidName(null)).isFalse();
  }

  @Test
  void testInvalidNameEmptyString() {
    assertThat(nameValidatorService.isValidName("")).isFalse();
  }

  @Test
  void testInvalidNameWhitespaceString() {
    assertThat(nameValidatorService.isValidName("   ")).isFalse();
  }
}
