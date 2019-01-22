package com.example.demo.databaseconfig;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.demo.model.InvoiceDetail;
import com.example.demo.model.PaymentReceipt;
import com.example.demo.model.RefType;
public class ConfigDatabase {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getInstance() {
		return sessionFactory;		
	}
	
	public static SessionFactory  createSessionFactory(boolean traceWithActiveSpanOnly,String nameDB,String auto) {
		  Configuration configuration = new Configuration();
		  configuration.addAnnotatedClass(PaymentReceipt.class);
		  configuration.addAnnotatedClass(RefType.class);
		  configuration.addAnnotatedClass(InvoiceDetail.class);
		  configuration.setProperty("hibernate.connection.driver_class",
			      "org.mariadb.jdbc.Driver");
			  configuration.setProperty("hibernate.connection.url",
			      "jdbc:mariadb://localhost:3306/"+nameDB+"?createDatabaseIfNotExist=" + traceWithActiveSpanOnly);
			  configuration.setProperty("hibernate.connection.username", "root");
			  configuration.setProperty("hibernate.connection.password", "123456");
			  configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
			  configuration.setProperty("hibernate.hbm2ddl.auto","update");
			  configuration.setProperty("hibernate.show_sql", "true");
			  configuration.setProperty("hibernate.connection.pool_size", "10");
			  configuration.setProperty("hibernate.connection.characterEncoding", "utf8");
			  configuration.setProperty("hibernate.connection.useUnicode", "true");
			  configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		  StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		      .applySettings(configuration.getProperties());
		  sessionFactory= configuration.buildSessionFactory(builder.build());
		  return sessionFactory;
	}
}
