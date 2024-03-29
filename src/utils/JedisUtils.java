package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.swing.text.Utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtils {
    private static JedisPool jedisPool;
    //静态代码块,读取配置文件
    static {
        //加载文件,获取输入流,class.getResourceAsStream
       InputStream inputStream = JedisUtils.class.getResourceAsStream("jedis.properties");
        //创建properties对象
        Properties properties = new Properties();
            //加载配置文件
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //使用JedisPoolConfig读取配置并设置jedis连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxTotal")));
        /*jedisPool = new JedisPool(jedisPoolConfig, String.valueOf(Integer.parseInt((String) properties.get("port"))),
                Integer.parseInt((String) properties.get("port")));*/
        //对jedisPool赋值, jedisPoolConfig,host,port
        jedisPool = new JedisPool(jedisPoolConfig, (String) properties.get("host"), Integer.parseInt(properties.get("port").toString()));
    }
    /*
    * 获取连接池工具类
    *
    * */
    public static Jedis getJedisPool(){
        return jedisPool.getResource();
    }
}
