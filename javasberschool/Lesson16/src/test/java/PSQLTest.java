import org.junit.Test;
import ru.anani.lesson16.source.PSQL;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Arrays;

public class PSQLTest {
    private final PSQL psql = new PSQL();

    public PSQLTest() throws SQLException {
    }

    @Test
    public void testContains() throws SQLException {
        assert true == psql.contains(1);
        assert true == psql.contains(2);
        assert true == psql.contains(3);
        assert false == psql.contains(5);
        psql.closeConnection();
    }

    @Test
    public void getAllValuesUpToInclusive() throws SQLException {
        assert Arrays.asList(0, 1).equals(psql.getAllValuesUpToInclusive(2));
        assert Arrays.asList(0, 1, 1).equals(psql.getAllValuesUpToInclusive(3));
        psql.closeConnection();
    }

    @Test
    @Transactional
    public void saveAll() {
        psql.saveAll(Arrays.asList(8));
        psql.closeConnection();
    }

}
