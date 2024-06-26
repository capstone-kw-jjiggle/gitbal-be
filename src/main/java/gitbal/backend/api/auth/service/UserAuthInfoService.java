package gitbal.backend.api.auth.service;


import gitbal.backend.api.auth.dto.UserInfoDto;
import gitbal.backend.domain.user.User;
import gitbal.backend.domain.user.UserService;
import gitbal.backend.global.util.AuthenticationChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthInfoService {

    private final UserService userService;
    private final AuthenticationChecker authenticationChecker;


    @Transactional(readOnly = true)
    public ResponseEntity<UserInfoDto> getUserInfoByUserName(Authentication authentication) {
        String username = authenticationChecker.checkAndRetrieveNickname(authentication);
        User user = userService.findByUserName(username);
        UserInfoDto userInfoDto = UserInfoDto.of(user,
            userService.createUserRegionSchoolNameDto(user));
        return ResponseEntity.ok(userInfoDto);
    }


}
