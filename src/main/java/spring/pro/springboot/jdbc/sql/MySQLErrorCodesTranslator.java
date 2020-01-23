package spring.pro.springboot.jdbc.sql;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

@Deprecated
public class MySQLErrorCodesTranslator{}
//        extends SQLErrorCodeSQLExceptionTranslator {
//
//    protected DataAccessException customTranslate(String task, String sql, SQLException sqlex) {
//        if (sqlex.getErrorCode() == -12345){
//            return new DeadlockLoserDataAccessException(task, sqlex);
//        }
//        return null;
//    }
//}
