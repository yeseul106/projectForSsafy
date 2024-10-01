package com.ssafy.side.common.util;

import static com.ssafy.side.common.exception.ErrorMessage.ERR_UNAUTORIZED;

import com.ssafy.side.common.exception.UnAuthorizedException;
import java.security.Principal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberUtil {

    public static Long getUserId(Principal principal) {
        if (principal == null) {
            throw new UnAuthorizedException(ERR_UNAUTORIZED);
        }
        return Long.valueOf(principal.getName());
    }
}
