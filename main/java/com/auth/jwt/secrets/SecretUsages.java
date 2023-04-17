package com.auth.jwt.secrets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SecretUsages {

    public final String apiSecret = "Jh3kLm7p0o9cVbDfGAb7cXq3bKjFDp6qOs4Zr5t8Gw7hJmYxE";
    public final String issuerName = "USER";




}
