package com.ssafy.side.common.uitl;

import static com.ssafy.side.common.exception.ErrorMessage.UNAUTHORIZED;

import com.ssafy.side.common.exception.UnAuthorizedException;
import java.security.Principal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberUtil {

    public static Long getUserId(Principal principal) {
        if (principal == null) {
            throw new UnAuthorizedException(UNAUTHORIZED.getMessage());
        }
        return Long.valueOf(principal.getName());
    }
}
