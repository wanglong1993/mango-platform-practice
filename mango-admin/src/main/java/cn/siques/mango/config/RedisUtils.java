package cn.siques.mango.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtils<K,V> {


    private static final long EXPIRATION = 1800L;


    private final RedisTemplate<K,V> redisTemplate;

    public RedisUtils(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }




    /**
     * 设置key值
     * @param key
     * @param value
     */
    public void setKey(K key,V value){
        ValueOperations<K, V> ops = redisTemplate.opsForValue();
        ops.set(key,value,EXPIRATION,TimeUnit.SECONDS);
    }

    /**
     * 设置key值
     * @param key
     * @param value
     */
    public  void setKey(K key,V value,Long expiration){
        ValueOperations<K, V> ops = redisTemplate.opsForValue();
        ops.set(key,value, expiration,TimeUnit.SECONDS);
    }






}
