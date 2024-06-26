package gitbal.backend.api.univcert.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class UnivCertStartDto {
  private String email;
  private String univName;
}
