package gitbal.backend.domain.school;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class School {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "school_id")
  private Long id;


  @NotNull
  @Column(length = 40)
  private String schoolName;

  @ColumnDefault(value = "0")
  private Long score;


  @ColumnDefault(value= "0")
  private Long changedScore;

  @ColumnDefault(value = "0")
  private int schoolRank;

  @NotNull
  private String topContributor;

  @NotNull
  private Long contributorScore;

  public School(String schoolName, Long score, String topContributor, Long contributorScore) {
    this.schoolName = schoolName;
    this.score = score;
    this.topContributor = topContributor;
    this.contributorScore = contributorScore;
  }

  // TODO: test 용도여서 나중에 실제로 값 넣으면 변경해야함.
  public static School of() {
    return new School("광운대학교", 0L, "khyojun", 0L);
  }

  public void addScore(Long score) {
    this.score += score;
  }

  public void updateContributerInfo(String nickname, Long score) {
    if (this.contributorScore == null || this.contributorScore < score) {
      this.topContributor = nickname;
      this.contributorScore = score;
    }
  }

  public void setSchoolRank(int rank) {
    this.schoolRank=rank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof School school)) {
      return false;
    }
    return Objects.equals(schoolName, school.schoolName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schoolName);
  }
}