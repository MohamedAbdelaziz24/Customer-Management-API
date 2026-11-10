package org.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import jakarta.persistence.EntityManagerFactory
import java.util.*
import javax.sql.DataSource


@EnableTransactionManagement
@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories("org.example.repository")
class JpaConfig {

    // Connect to MySQL Database
    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName( "com.mysql.cj.jdbc.Driver")
        dataSource.url = "jdbc:mysql://localhost:3306/customer_db"
        dataSource.username = "root"
        dataSource.password = "Root"
        return dataSource
    }

    // create EntityManager
    @Bean
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.setDataSource(dataSource)
        factory.setPackagesToScan("org.example.entity")
        factory.jpaVendorAdapter = HibernateJpaVendorAdapter()

        val properties = Properties()
        properties["hibernate.hbm2ddl.auto"] = "update"
        properties["hibernate.show_sql"] = "false"
        properties["hibernate.format_sql"] = "false"
        factory.setJpaProperties(properties)
        return factory
    }

    // manage transaction
    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val manager = JpaTransactionManager()
        manager.entityManagerFactory = entityManagerFactory
        return manager
    }
}