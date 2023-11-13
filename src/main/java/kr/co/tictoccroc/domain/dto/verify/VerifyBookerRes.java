package kr.co.tictoccroc.domain.dto.verify;

import kr.co.tictoccroc.domain.model.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class VerifyBookerRes {


  private final List<StoreLessonInfo> storeLessons = new ArrayList<>();


  public VerifyBookerRes(List<Book> books, Map<Long, Parent> parentMap) {
    Map<Long, StoreLesson> storeLessonMap = getStoreLesson(books);
    Map<Long, List<Parent>> parentByStoreLessonMap = getParentMap(books, parentMap);

    Map<LocalDate, List<StoreLesson>> storeLessonDateMap = storeLessonMap.values().stream().collect(Collectors.groupingBy(StoreLesson::getLessonDay));
    storeLessonDateMap.forEach((key, value) -> storeLessons.add(new StoreLessonInfo(key, value, parentByStoreLessonMap)));
  }


  @Getter
  @ToString
  public static class StoreLessonInfo {

    private final String lessonDay;
    private final List<StoreInfo> stores = new ArrayList<>();


    public StoreLessonInfo(LocalDate localDate, List<StoreLesson> storeLessons, Map<Long, List<Parent>> parentByStoreLessonMap) {
      this.lessonDay = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

      Map<Store, List<StoreLesson>> storeListStoreMap = storeLessons.stream().collect(Collectors.groupingBy(StoreLesson::getStore));
      storeListStoreMap.forEach((key, value) -> stores.add(new StoreInfo(key, value, parentByStoreLessonMap)));
    }

  }

  @Getter
  @ToString
  public static class StoreInfo {

    private final String storeName;
    private final List<LessonInfo> lesson = new ArrayList<>();

    public StoreInfo(Store store, List<StoreLesson> storeLessons, Map<Long, List<Parent>> parentByStoreLessonMap) {
      this.storeName = store.getName();

      Map<Lesson, List<StoreLesson>> storeListLessonMap = storeLessons.stream().collect(Collectors.groupingBy(StoreLesson::getLesson));
      storeListLessonMap.forEach((key, value) -> lesson.add(new LessonInfo(key, value, parentByStoreLessonMap)));
    }
  }

  @Getter
  @ToString
  public static class LessonInfo {

    private final String lessonName;
    private final List<Booker> bookers = new ArrayList<>();

    public LessonInfo(Lesson lesson, List<StoreLesson> storeLessons, Map<Long, List<Parent>> parentByStoreLessonMap) {
      this.lessonName = lesson.getName();

      storeLessons.forEach(storeLesson -> bookers.addAll(parentByStoreLessonMap.get(storeLesson.getId()).stream().map(Booker::new).toList()));
    }
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


  /**
   * 매장별 수업 정보 추출
   */
  private Map<Long, StoreLesson> getStoreLesson(List<Book> books) {
    return books.stream().collect(Collectors.groupingBy(Book::getStoreLesson)).keySet().stream().collect(Collectors.toMap(StoreLesson::getId, Function.identity()));
  }


  private Map<Long, List<Parent>> getParentMap(List<Book> books, Map<Long, Parent> parentMap) {
    return books.stream().collect(Collectors.groupingBy(book -> book.getStoreLesson().getId(), Collectors.mapping(book -> parentMap.get(book.getParent().getId()), Collectors.toList())));
  }

}
