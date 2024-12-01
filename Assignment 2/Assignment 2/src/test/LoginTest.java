import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class LoginAppTest {

    private LoginApp loginApp;

    @BeforeEach
    public void setUp() {
        loginApp = new LoginApp();
    }

    @Test
    public void testValidLogin() {
        String email = "johndoe@example.com";
        String password = "password123";

        // Simulate valid database entry
        assertEquals("John Doe", loginApp.authenticateUser(email, password));
    }

    @Test
    public void testInvalidEmail() {
        String email = "invalid@example.com";
        String password = "correctPassword";

        // Simulate invalid email
        assertNull(loginApp.authenticateUser(email, password));
    }

    @Test
    public void testInvalidPassword() {
        String email = "valid@example.com";
        String password = "wrongPassword";

        // Simulate invalid password
        assertNull(loginApp.authenticateUser(email, password));
    }

    @Test
    public void testEmptyEmail() {
        String email = "";
        String password = "password";

        // Simulate empty email
        assertNull(loginApp.authenticateUser(email, password));
    }

    @Test
    void testSQLInjection() {
        // Setup: SQL Injection attempt in the email field
        String sqlInjectionEmail = "' OR 1=1 --";  // SQL Injection
        String password = "anything";  // Password doesn't matter here

        // Action
        String result = loginApp.authenticateUser(sqlInjectionEmail, password);

        // Assert: SQL injection should not authenticate any user, result should be null
        assertNull(result, "SQL injection should not authenticate any user.");
    }
}