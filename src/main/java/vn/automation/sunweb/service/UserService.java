package vn.automation.sunweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.automation.sunweb.entity.User;
import vn.automation.sunweb.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
@Autowired private UserRepository userRepository;

public List<User> findAll(Integer limit){
    return Optional.ofNullable(limit).map(value -> userRepository.findAll(PageRequest.of(0,value)).getContent()).orElseGet(()->userRepository.findAll());
}



}

