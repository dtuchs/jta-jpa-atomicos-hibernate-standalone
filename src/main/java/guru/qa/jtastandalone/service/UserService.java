package guru.qa.jtastandalone.service;


import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import guru.qa.jtastandalone.entity.auth.AuthUserEntity;
import guru.qa.jtastandalone.entity.ud.UserEntity;
import guru.qa.jtastandalone.util.Jndi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.UserTransaction;
import org.apache.commons.lang3.StringUtils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {

  private final EntityManagerFactory authEMF;
  private final EntityManagerFactory userdataEMF;
  private final UserTransaction userTransaction;

  private static final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

  private static DataSource dataSource(String jdbcUrl) {
    return dataSources.computeIfAbsent(
        jdbcUrl,
        key -> {
          final String resourceName = StringUtils.substringAfter(key, "5432/");
          AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
          ds.setUniqueResourceName(resourceName);
          ds.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
          Properties p = new Properties();
          p.setProperty("user", "postgres");
          p.setProperty("password", "secret");
          p.setProperty("URL", key);
          ds.setXaProperties(p);
          ds.setPoolSize(10);
          try {
            InitialContext context = Jndi.context();
            context.bind("java:comp/env/jdbc/" + resourceName , ds);
          } catch (NamingException e) {
            throw new RuntimeException(e);
          }
          return ds;
        }
    );
  }

  static {
    dataSource("jdbc:postgresql://127.0.0.1:5432/niffler-auth");
    dataSource("jdbc:postgresql://127.0.0.1:5432/niffler-userdata");
  }

  public UserService() {
    this.userdataEMF = Persistence.createEntityManagerFactory("niffler-userdata");
    this.authEMF = Persistence.createEntityManagerFactory("niffler-auth");
    this.userTransaction = new UserTransactionImp();
  }

  public void createUser(AuthUserEntity authUserEntity, UserEntity userEntity) throws Exception {
    userTransaction.begin();
    EntityManager authEM = authEMF.createEntityManager();
    EntityManager userdataEM = userdataEMF.createEntityManager();
    try {
      authEM.joinTransaction();
      userdataEM.joinTransaction();

      authEM.persist(authUserEntity);
      userdataEM.persist(userEntity);

      userTransaction.commit();
    } catch (Exception e) {
      userTransaction.rollback();
      throw e;
    } finally {
      authEM.close();
      userdataEM.close();
    }
  }
}
