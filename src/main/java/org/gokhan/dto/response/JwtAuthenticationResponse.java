package org.gokhan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token;

    @Override
    public String toString() {
        return "gökanın ilk tokenı{" +
                "token='" + token + '\'' +
                '}';
    }
}
