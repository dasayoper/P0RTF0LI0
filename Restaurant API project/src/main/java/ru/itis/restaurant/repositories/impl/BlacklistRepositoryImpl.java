package ru.itis.restaurant.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.restaurant.repositories.BlacklistRepository;

@RequiredArgsConstructor
@Repository
public class BlacklistRepositoryImpl implements BlacklistRepository {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String token) {
        redisTemplate.opsForSet().add("BlackList", token);
    }

    @Override
    public boolean exists(String token) {
        Boolean hasToken = redisTemplate.opsForSet().isMember("BlackList", token);
        return hasToken != null && hasToken;
    }
}
