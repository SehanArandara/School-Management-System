package org.schoolmanagement.librarymanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.schoolmanagement.librarymanagement.Repo.UserRepo;
import org.schoolmanagement.librarymanagement.exception.NotFoundException;
import org.schoolmanagement.librarymanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User addUser(User user){
        return userRepo.save(user);
    }

    public User getSingleUser(Long id){
        Optional<User> users= userRepo.findById(id);
        return users.map(this::mapUsers)
                .orElseThrow(()-> new NotFoundException("user not found"));
    }

    public List<User> getAllUsers(){
        List<User> userList = userRepo.findAll();
        return userList.stream().map(this::mapUsers).toList();
    }

    public void updateUser(Long id,User user){
        User existingUser = userRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("there is no user"));

        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUserId(user.getUserId());
        existingUser.setPhoneNumber(user.getPhoneNumber());

        userRepo.save(existingUser);
        log.info("User is updated");
    }

    public void deleteUser(Long id){
        User existingUser = userRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("there is no user"));

        userRepo.delete(existingUser);
        log.info("user deleted");
    }

    private User mapUsers(User user) {
        return User.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }
}
