package com.idb.tpbank.Services;

import com.idb.tpbank.JpaRepo.ApiJpaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiService {
    @Autowired
    private ApiJpaRepo repo;

    public Boolean impSource(String json, String user, String fullname, String source) {
        Boolean isSuccess = false;

        try {
            repo.impCustomerToSourceCall(json, user, fullname, source);

            isSuccess = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        return isSuccess;
    }
}
