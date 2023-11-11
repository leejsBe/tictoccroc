package kr.co.tictoccroc.domain.repository;

import kr.co.tictoccroc.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {


  List<Store> findByDelAtIsNull();

}
