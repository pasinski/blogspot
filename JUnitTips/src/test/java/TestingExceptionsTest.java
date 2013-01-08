import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: mpasinski
 * Date: 08.01.13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class TestingExceptionsTest {

    private static final String EXCEPTION_MESSAGE = "Test exception message";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNullPointerExceptionSimpleMessageCheck() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(EXCEPTION_MESSAGE);

        throw new NullPointerException(EXCEPTION_MESSAGE);
    }

    @Test
    public void testExceptionWithHamcrestMatcher() {
        thrown.expect(isNullPointerWithTestExceptionMessage());

        throw new NullPointerException(EXCEPTION_MESSAGE);
    }

    private static Matcher<Throwable> isNullPointerWithTestExceptionMessage() {
        return new BaseMatcher<Throwable>() {
            @Override
            public boolean matches(Object o) {
                if (o instanceof NullPointerException) {
                    NullPointerException npe = (NullPointerException) o;
                    return EXCEPTION_MESSAGE.equals(npe.getMessage());
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
