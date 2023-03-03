package com.example.unitTest.service;

import com.amazonaws.services.sns.model.NotFoundException;
import com.example.unitTest.decorator.ImageData;
import com.example.unitTest.decorator.ImageUtils;
import com.example.unitTest.model.User;
import com.example.unitTest.repository.StorageRepository;
import com.example.unitTest.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.mappers.ModelMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
private  final StorageRepository repository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, StorageRepository repository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.repository = repository;
    }

    @Override
    public Map<String, List<User>> getUsersByCity() {
        return userRepository.findAllBySoftDeleteFalse().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(userInfo -> userInfo.getCity()));
    }


    @Override
    public Map<Pair<String, String>, List<User>> getUsersByStateAndCity() {
        return userRepository.findAllBySoftDeleteFalse().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(userInfo ->
                                new Pair<>(userInfo.getCity(),
                                        userInfo.getState())));
    }

    @Override
    public Map<Triple<String, String, String>, List<User>> getUsersByCountryStateAndCity() {
        return userRepository.findAllBySoftDeleteFalse().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(user -> new ImmutableTriple<>(user.getCity(), user.getState(), user.getCountry())));
    }

    @Override
    public Set<String> getAllCity() {
        Set<String> cityNames = userRepository.findAllBySoftDeleteFalse()
                .stream()
                .filter(Objects::nonNull)
                .map(User::getCity)
                .collect(Collectors.toSet());
        return cityNames;


    }



@Override
public List<User> sortByBirthAndFirstName() {
    List<User> sortedUser = new ArrayList<>();
    List<User> nullUser = new ArrayList<>();
    List<User> users = userRepository.findAllBySoftDeleteFalse();
    users.forEach(user -> {
        if (user.getBirthDate() != null && user.getFirstName() != null) {
            sortedUser.add(user);
        } else {
            nullUser.add(user);
        }
    });
    sortedUser.stream().sorted(Comparator.comparing(User::getBirthDate).
            thenComparing(User::getFirstName)).collect(Collectors.toList());
    sortedUser.addAll(nullUser);
    return sortedUser;
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
                .filter(Objects::nonNull)
                .filter(user -> user.getBirthDate()!=null && Period.between(user.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() >= 18)
                .collect(Collectors.toList());
    }



    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
            if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
           }
          return null;
    }


    @Override
    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }


    @Override
    public List<User> addEmail() {
        Set<String> emails = new HashSet<>();
        emails.add("dency@gmail.com");
        emails.add("dency05@gmail.com");
        emails.add("dency09@gmail.com");
        List<User> users = userRepository.findAll();
        Iterator<String> emailIterator = emails.iterator();
        for (User user : users) {
            if (StringUtils.isEmpty(user.getEmail())) {
                if (emailIterator.hasNext()) {
                    String email = emailIterator.next();
                    user.setEmail(email);
                    userRepository.save(user);
                }
           }

        }
        return users;
    }





    public List<User> getAllUser(){
     List<User> users= userRepository.findAllBySoftDeleteFalse();
     if (!users.isEmpty()){
         return users;
     }

        return users;
    }


}


