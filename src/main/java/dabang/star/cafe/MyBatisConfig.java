package dabang.star.cafe;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class MyBatisConfig {

    /*
        SqlSessionFactory
        : 이름 그대로 SqlSession 이라는 것을 만들어 낸다.
        SqlSession
        : 세션을 한 번 생성하면 커밋이나 롤백을 하기 위해 세션을 사용할 수 있다. 필요하지 않으면 세션을 닫는다.
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml");
        sessionFactoryBean.setMapperLocations(resources);

        return sessionFactoryBean.getObject();
    }

    /*
        SqlSessionTemplate
        : SqlSession 을 구현하고 코드에서 SqlSession 을 대체하는 역할을 한다.
          쓰레드에 안전하고 여러 개의 매퍼에서 공유할 수 있다.
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
