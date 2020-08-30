package br.com.kaikeventura.bdb.util;

import br.com.kaikeventura.bdb.dto.VotingDTO;
import br.com.kaikeventura.bdb.model.Voting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotingUtil {

    private final JWTUtil jwtUtil;

    public Voting toVoting(VotingDTO votingDTO, String bigDebug, String bigDeveloperBrazil, String token) {

        return new Voting(
                bigDeveloperBrazil,
                bigDebug,
                jwtUtil.getUsernameFromToken(token.replace("Bearer ", "")),
                votingDTO.getTechnologyBigDebugId()
        );
    }
}
