package kr.co.tictoccroc.domain.dto.verify;

import kr.co.tictoccroc.domain.model.Parent;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class VerifyBookerRes {


  private final List<Booker> bookers;


  public VerifyBookerRes(Map<Long, Parent> parentMap) {
    this.bookers = parentMap.values().stream().map(Booker::new).collect(Collectors.toList());
  }


  @Getter
  @ToString
  public static class Booker {

    private final String name;
    private final String email;


    public Booker(Parent parent) {
      this.name = parent.getName();
      this.email = parent.getEmail();
    }
  }

}
