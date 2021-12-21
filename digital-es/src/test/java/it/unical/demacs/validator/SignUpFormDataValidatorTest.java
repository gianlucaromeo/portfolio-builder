package it.unical.demacs.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.SignUpFormValidator;

@RunWith(Parameterized.class)
public class SignUpFormDataValidatorTest {
	
	private String expected;
	private String actual;

	public SignUpFormDataValidatorTest(String actual, String expected) {
		this.expected = expected;
		this.actual = actual;
	}
	
	@Parameters
	public static Collection<Object[]> getParameters() {
		
		String expectedOK = Protocol.OK;
		String expectedERROR = Protocol.ERROR;
		
		return Arrays.asList(new Object[][] {
			
			/* FIRST NAME CHECK */
			{"Gianluca", expectedOK},
			{"Gianluca1", expectedERROR},
			{"Cristian Domenico", expectedOK},
			{"Luigi", expectedOK},
			{"Luigigino10", expectedERROR},
			{"Tino 18 Ciao", expectedERROR},
			
			/* LAST NAME CHECK */
			{"Romeo", expectedOK},
			{"Dramisino ", expectedERROR},
			{"12234", expectedERROR},
			{"1Montalbano", expectedERROR},
			{"De  Francesco", expectedERROR},
			{"cognomeMinuscola", expectedERROR},
			{"Pino Pino Piono Piono Pino1", expectedERROR},
			
		});
		
	}
	
	@Test
	public void checkFirstNameWorks() {		
		assertEquals(expected, SignUpFormValidator.checkName(actual));
	}
	
	
}
