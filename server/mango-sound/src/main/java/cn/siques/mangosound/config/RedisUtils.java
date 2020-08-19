package cn.siques.mangosound.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisUtils<K,V> {


    private static final long EXPIRATION = 12 * 60 * 60;


    private RedisTemplate<K,V> redisTemplate ;



    public RedisUtils(RedisTemplate<K, V> redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
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


    public V getValue(K key){
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteKey(Collection<K> collection){

        redisTemplate.delete(collection);

    }


    public void deleteKey(K key){

        redisTemplate.delete(key);

    }





}
