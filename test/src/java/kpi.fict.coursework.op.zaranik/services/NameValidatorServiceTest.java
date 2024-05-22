package src.java.kpi.fict.coursework.op.zaranik.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kpi.fict.coursework.op.zaranik.dao.Postgres.QueueDao;
import kpi.fict.coursework.op.zaranik.services.dao.impl.QueueDaoServiceImpl;
import kpi.fict.coursework.op.zaranik.services.namevalidator.NameValidatorService;
import kpi.fict.coursework.op.zaranik.services.namevalidator.impl.NameValidatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NameValidatorServiceTest {

  @InjectMocks
  private NameValidatorServiceImpl nameValidatorService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testValidName(){
    assertTrue(nameValidatorService.isValidName("MyQueue"));
    assertTrue(nameValidatorService.isValidName("queue"));
    assertTrue(nameValidatorService.isValidName("queue 1"));

  }

  @Test
  void testValidNullName() {
    assertFalse(nameValidatorService.isValidName(null));
  }

  @Test
  void testInvalidNameEmptyString() {
    assertFalse(nameValidatorService.isValidName(""));
  }

  @Test
  void testInvalidNameWhitespaceString() {
    assertFalse(nameValidatorService.isValidName("   "));
  }
}
