package com.example.easykiosk.repository;

import com.example.easykiosk.model.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface FileOperationsRepo extends CrudRepository<UserDetails,Long> {
}
