package cn.siques.mango.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtils<K,V> {


    private static final long EXPIRATION = 12 * 60 * 60 * 1000;


    private final RedisTemplate<K,V> redisTemplate;




    public RedisUtils(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public  RedisUtils init(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return this;
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
