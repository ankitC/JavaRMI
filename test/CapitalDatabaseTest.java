import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CapitalDatabaseTest {

    @Test
    public void test() {
        assertEquals("Washington, D.C.", CapitalDatabase.getNationCapital("United States"));

        CapitalDatabase.State ohio = CapitalDatabase.getStateCapital("Ohio");

        assertEquals("Columbus", ohio.getCapital());
    }
}
