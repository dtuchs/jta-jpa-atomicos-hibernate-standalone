package guru.qa.jtastandalone;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.sql.DataSource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import guru.qa.jtastandalone.util.Jndi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;
import org.apache.commons.lang3.StringUtils;

public class JTAStandaloneExampleAtomikos  {




  public static void main(String[] args) {
//    try {
//      // Create an in-memory jndi
//      NamingServer namingServer = new NamingServer();
//      NamingContext.setLocal(namingServer);
//      Main namingMain = new Main();
//      namingMain.setInstallGlobalService(true);
//      namingMain.setPort(-1);
//      namingMain.start();
//
//      Properties props = new Properties();
//      props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
//      props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
//
//      InitialContext ictx = new InitialContext( props );
//
//      AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
//      ds.setUniqueResourceName("sqlserver_ds");
//      ds.setXaDataSourceClassName("com.microsoft.sqlserver.jdbc.SQLServerXADataSource");
//      Properties p = new Properties();
//      p.setProperty ( "user" , "sa" );
//      p.setProperty ( "password" , "" );
//      p.setProperty ( "serverName" , "myserver" );
//      ds.setXaProperties ( p );
//      ds.setPoolSize(5);
//      bind("java:/MyDatasource", ds, ds.getClass(), ictx);
//
//      TransactionManagerLookup _ml = new TransactionManagerLookup();
//      UserTransaction userTransaction = new com.atomikos.icatch.jta.UserTransactionImp();
//
//      bind("java:/TransactionManager", _ml.getTransactionManager(null), _ml.getTransactionManager(null).getClass(), ictx);
//      bind("java:comp/UserTransaction", userTransaction, userTransaction.getClass(), ictx);
//
//      HibernateEntityManagerFactory emf =  (HibernateEntityManagerFactory) Persistence.createEntityManagerFactory("helloworld");
//
//      // begin a new Transaction
//      userTransaction.begin();
//      EntityManager em = emf.createEntityManager();
//
//      A a = new A();
//      a.name= "firstvalue";
//      em.persist(a);
//      em.flush();     // do manually flush here as apparently FLUSH_BEFORE_COMPLETION seems not work, bug ?
//
//      System.out.println("Calling userTransaction.commit() (Please check if the commit is effectively executed!)");
//      userTransaction.commit();
//
//      emf.close();
//
//    } catch (Exception e) {
//      e.printStackTrace();
//      System.exit(1);
//    }
//    System.exit(0);
  }
}