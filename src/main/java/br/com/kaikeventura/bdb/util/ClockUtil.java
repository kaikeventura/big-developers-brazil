package br.com.kaikeventura.bdb.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ClockUtil {

    private final Clock clock;

    public Instant instantNow() {
        return Instant.parse(LocalDateTime.now(clock).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
    }
}
