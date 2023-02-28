package com.example.unitTest.service;

import com.example.unitTest.decorator.UserAddRequest;
import com.example.unitTest.decorator.UserResponse;
import com.example.unitTest.model.User;
import com.example.unitTest.repository.UserRepository;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public Map<String, List<User>> getUsersByCity() {
        List<User> users = userRepository.findAllBySoftDeleteFalse();
        return users.stream()
                .collect(Collectors.groupingBy(userInfo -> userInfo.getCity()));
    }


    @Override
    public Map<Pair<String, String>, List<User>> getUsersByStateAndCity() {
        List<User> users = userRepository.findAllBySoftDeleteFalse();
        return users.stream()
                .collect(Collectors.groupingBy(userInfo ->
                                new Pair<>(userInfo.getCity(),
                                        userInfo.getState())));
    }

    @Override
    public Map<Triple<String, String, String>, List<User>> getUsersByCountryStateAndCity() {
        List<User> users = userRepository.findAllBySoftDeleteFalse();
        return users.stream()
                .collect(Collectors.groupingBy(user -> new ImmutableTriple<>(user.getCity(), user.getState(), user.getCountry())));
    }

    @Override
    public Set<String> getAllCity() {
        Set<String> cityNames = userRepository.findAllBySoftDeleteFalse()
                .stream()
                .map(User::getCity)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return cityNames;


    }

    @Override
    public List<User> sortByBirthAndFirstName() {
        List<User> users = userRepository.findAllBySoftDeleteFalse();
        return users.stream()
                .sorted(Comparator.comparing(User::getBirthDate)
                        .thenComparing(User::getFirstName))
                           .collect(Collectors.toList());

    }

    @Override
    public Map<String, User> getUserByEmail() {
        List<User> users = userRepository.findAllBySoftDeleteFalse();
        Map<String, User> usersByEmail = new HashMap<>();
        for (User user : users) {
            if (usersByEmail.containsKey(user.getEmail())) {
                continue;
            }
            usersByEmail.put(user.getEmail(), user);
        }
        return usersByEmail;
    }


    @Override
    public List<User> getUserByAge() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .filter(user -> Period.between(user.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() >= 18)
                .collect(Collectors.toList());
    }
}


