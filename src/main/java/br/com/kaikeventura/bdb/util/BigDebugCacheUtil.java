package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.error.exception.BigDebugNotAvailableException;
import br.com.kaikeventura.bdb.model.BigDebug;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class BigDebugCacheUtil {

    private static final String DOCUMENT_NAME = "big_debug";
    private static final String ATTRIBUTE_NAME = "actual_big_debug";

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, BigDebug> hashOperations;

    public BigDebugCacheUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void initHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void putBigDebug(BigDebug bigDebug) {
        hashOperations.put(DOCUMENT_NAME, ATTRIBUTE_NAME, bigDebug);
    }

    public BigDebug getBigDebug() {
        Optional<BigDebug> bigDebug = Optional.ofNullable(hashOperations.get(DOCUMENT_NAME, ATTRIBUTE_NAME));
        if (bigDebug.isEmpty()) {
            throw new BigDebugNotAvailableException();
        }
        return bigDebug.get();
    }

    public void deleteBigDebug() {
        hashOperations.delete(DOCUMENT_NAME, ATTRIBUTE_NAME);
    }
}
