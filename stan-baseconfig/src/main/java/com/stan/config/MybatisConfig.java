package com.stan.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by sdc on 2019/4/6.
 */

@Configuration
public class MybatisConfig {

    private Log logger = LogFactory.getLog(MybatisConfig.class);

    @Autowired
    private DataSource dataSource;

    // 提供SqlSeesion
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage("com.stan.seckill.pojo");

            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/com/stan/*/dao/*.xml");
            sessionFactoryBean.setMapperLocations(resources);
            //设置mybatis-config.xml配置文件位置
            //sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

//            //添加分页插件、打印sql插件
//            Interceptor[] plugins = new Interceptor[]{pageHelper(),sqlPrintInterceptor()};
//            sessionFactoryBean.setPlugins(plugins);
            logger.info("sqlSessionFactoryBean init success...");
            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("mybatis resolver mapper*xml is error",e);
            return null;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error",e);
            return null;
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
