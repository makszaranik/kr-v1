package src.java.kpi.fict.coursework.op.zaranik.services;

import java.util.stream.Stream;
import kpi.fict.coursework.op.zaranik.services.namevalidator.impl.NameValidatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class NameValidatorServiceTest {

  @InjectMocks
  private NameValidatorServiceImpl nameValidatorService;

  @ParameterizedTest
  @MethodSource("nameMethodSource")
  void testValidName(String name) {
    assertThat(nameValidatorService.isValidName(name)).isTrue();
  }

  public static Stream<String> nameMethodSource() {
    return Stream.of(
        "MyQueue",
        "queue",
        "queue 1"
    );
  }

  @ParameterizedTest
  @NullAndEmptySource
  void testInvalidName(String name) {
    assertThat(nameValidatorService.isValidName(name)).isFalse();
  }

  @Test
  void testInvalidNameWhitespaceString() {
    assertThat(nameValidatorService.isValidName("   ")).isFalse();
  }

}
