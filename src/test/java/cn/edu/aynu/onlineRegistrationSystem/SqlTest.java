package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/3 0003
 */
@SpringBootTest
public class SqlTest {
    SqlSession sqlSession=null;
    SqlSessionFactory sqlSessionFactory=null;
    @Before
    public void beging() throws IOException {
        sqlSessionFactory= SqlFactory.getSqlFactory();
        sqlSession=sqlSessionFactory.openSession();
    }
    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 插入个人信息
     */
    @Test
    public void insertMemInfo(){
        memInfoMapper mem=sqlSession.getMapper(memInfoMapper.class);
        mem.insert(new memInfo(174804155,"岳阳","15993343299@163.com","M","123456"));
    }
}
class SqlFactory{
    public static SqlSessionFactory getSqlFactory() throws IOException {
        InputStream in=Resources.getResourceAsStream("mapper/memInfoMapper.xml");
        return new SqlSessionFactoryBuilder().build(in);
    }
}
