package com.idb.tpbank.JpaRepo;

import com.idb.tpbank.Entities.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiJpaRepo extends JpaRepository<Repo, Integer> {
    @Query(value = "call tls_imp_source_v2 (:json, :user, :fullname, :source);", nativeQuery = true)
    void impCustomerToSourceCall(@Param("json") String json, @Param("user") String user, @Param("fullname") String fullname, @Param("source") String source);
}
