package spring.pro.springboot.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Slf4j
//@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@PropertySource("classpath:mariadb.properties")

@SpringBootApplication
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


//
//	@Bean
//	public DataSource dataSource(){
//		try{
//			SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//			Class<? extends Driver> driver =(Class<? extends Driver>) Class.forName(driverClassName);
////			dataSource.setDriverClass((Class<? extends Driver>) Class.forName(driverClassName));
//			dataSource.setDriverClass(driver);
//			dataSource.setUrl(url);
//			dataSource.setUsername("root");
//			dataSource.setPassword("root");
//			log.info("Datasource config ready!!" + dataSource);
//			return dataSource;
//
//			//Embedded(in memory) dataSource/
////			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
////				return dbBuilder.setType(EmbeddedDatabaseType.H2)
////						.addScripts("classpath:sql/schema.sql",
////								"classpath:sql/test-data.sql") .build();
//
//			//JNDI dataSource/
////			JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           // create JNDI data source
////			bean.setJndiName("java:/comp/env/dbSource");  // jndiDataSource is name of JNDI data source
////			bean.setProxyInterface(DataSource.class);
////			bean.setLookupOnStartup(false);
////			bean.afterPropertiesSet();
////			return (DataSource) bean.getObject();
//		}catch(Exception ex){
//			log.error("EmЬedded DataSource bean cannot bе created!", ex);
//			return null;
//		}
//	}
//
//	@Bean
//	public SessionFactory sessionFactory() throws IOException {
//		log.debug("set sessionFactory");
//		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource());
//		sessionFactoryBean.setPackagesToScan("spring.pro.springboot.jdbc.dao.entities"); // может он и не нужен?
//		sessionFactoryBean.setHibernateProperties(hibernateProperties());
//		sessionFactoryBean.afterPropertiesSet();
//		return sessionFactoryBean.getObject();
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() throws IOException{
//		return new HibernateTransactionManager(sessionFactory());
//	}
//
//	private Properties hibernateProperties () {
//		log.debug("set Hibernate props");
//		Properties hibernateProp = new Properties();
//
//		hibernateProp.put("hibernate.connection.useUnicode",true);
//		hibernateProp.put("hibernate.connection.characterEncoding","UTF-8");
//		hibernateProp.put("hibernate.connection.charSet", "UTF-8");
//
//		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect");
////		hibernateProp.put("hibernate.format_sql", true); // для примера задали в Application.propertiea
//		hibernateProp.put("hibernate.use_sql_comments", true);
//		hibernateProp.put("hibernate.show_sql", true);
//		hibernateProp.put("hibernate.max_fetch_depth", 3);
//		hibernateProp.put("hibernate.jdbc.batch size", 10);
//		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//		hibernateProp.put("hibernate.c3p0.min_size", 5);
//		hibernateProp.put("hibernate.c3p0.max_size", 10);
//		hibernateProp.put("hibernate.c3p0.timeout", 300);
//		log.error("properties", hibernateProp);
//		return hibernateProp;
//	}
}
