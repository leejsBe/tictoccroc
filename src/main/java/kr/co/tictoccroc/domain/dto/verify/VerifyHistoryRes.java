package kr.co.tictoccroc.domain.dto.verify;

import kr.co.tictoccroc.domain.model.Book;
import lombok.Getter;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class VerifyHistoryRes {


  private final List<BookStatus> status;


  public VerifyHistoryRes(List<Book> books) {
    this.status = books.stream().map(BookStatus::new).collect(Collectors.toList());
  }


  @Getter
  @ToString
  public static class BookStatus {

    private final String bookNo;
    private final String status;
    private final int count;
    private final String createAt;
    private final String modAt;


    public BookStatus(Book book) {
      this.bookNo = String.valueOf(book.getId());
      this.status = book.getBookStatus().name();
      this.count = book.getCount();
      this.createAt = book.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      this.modAt = Objects.isNull(book.getModAt()) ? "" : book.getModAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
  }

}
