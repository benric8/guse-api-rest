package pe.gob.pj.guse.infraestructure.db;

import java.io.IOException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	
	private static Properties getHibernatePropertiesPostgresql() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");
		hibernateProperties.put("hibernate.show_sql", false);
		return hibernateProperties;
	}
	
	private static Properties getHibernatePropertiesOracle() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.put("hibernate.show_sql", false);
		return hibernateProperties;
	}
	
	/* Creación de conexión con base de datos seguridad */
	@Bean(name = "cxSeguridadDS")
	public DataSource jndiConexionSeguridad() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:jboss/datasources/usuarios-servicios-externosAPISeguridad");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.setCache(true);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}	
		
	@Bean(name = "sessionSeguridad")
	public SessionFactory getSessionFactorySeguridad(@Qualifier("cxSeguridadDS") DataSource seguridadDS) throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("pe.gob.pj.guse.infraestructure.db.entity.security");
		sessionFactoryBean.setHibernateProperties(getHibernatePropertiesPostgresql());
		sessionFactoryBean.setDataSource(seguridadDS);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean(name = "txManagerSeguridad")
	public HibernateTransactionManager getTransactionManagerSeguridad(@Qualifier("sessionSeguridad") SessionFactory sessionSeguridad) throws IOException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionSeguridad);
		return transactionManager;
	}
	
	/* Creación de conexión con la base de datos Sidenum */
	@Bean(name = "sidenumDS")
	public DataSource jndiConexionSicape() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:jboss/datasources/usuarios-servicios-externosAPISidenum");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.setCache(true);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}	
			
	@Bean(name = "sessionSidenum")
	public SessionFactory getSessionFactorySicape(@Qualifier("sidenumDS") DataSource sidenumDS) throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("pe.gob.pj.guse.infraestructure.db.entity.sidenum");
		sessionFactoryBean.setHibernateProperties(getHibernatePropertiesOracle());
		sessionFactoryBean.setDataSource(sidenumDS);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean(name = "txManagerSidenum")
	public HibernateTransactionManager getTransactionManagerSicape(@Qualifier("sessionSidenum") SessionFactory sessionSidenum) throws IOException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionSidenum);
		return transactionManager;
	} 
	
	/* Creación de conexión con base de datos Casillero */
	@Bean(name = "cxCasilleroDS")
	public DataSource jndiConexionCasillero() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:jboss/datasources/usuarios-servicios-externosAPICasillero");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.setCache(true);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}	
		
	@Bean(name = "sessionCasillero")
	public SessionFactory getSessionFactoryCasillero(@Qualifier("cxCasilleroDS") DataSource seguridadDS) throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("pe.gob.pj.guse.infraestructure.db.entity.casillero");
		sessionFactoryBean.setHibernateProperties(getHibernatePropertiesPostgresql());
		sessionFactoryBean.setDataSource(seguridadDS);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean(name = "txManagerCasillero")
	public HibernateTransactionManager getTransactionManagerCasillero(@Qualifier("sessionCasillero") SessionFactory sessionSeguridad) throws IOException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionSeguridad);
		return transactionManager;
	}
	
}
