package guru.qa.jtastandalone.util;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Jndi {

  private static InitialContext context;

  public static synchronized InitialContext context() throws NamingException {
    if (context == null) {
      context = new InitialContext();
    }
    return context;
  }
}
