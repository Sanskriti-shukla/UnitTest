package com.example.unitTest.controller;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.example.unitTest.common.DataResponse;
import com.example.unitTest.common.Response;
import com.example.unitTest.common.ResponseConstant;
import com.example.unitTest.model.User;
import com.example.unitTest.service.UserService;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("userInfo")
    public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }


        @RequestMapping(name = "getUsersByCity",value = "/get/users/city", method = RequestMethod.GET)
        public DataResponse<Map<String, List<User>>> getUsersByCity()  {
            DataResponse<Map<String, List<User>>> dataResponse = new DataResponse<>();
            dataResponse.setData(userService.getUsersByCity());
            dataResponse.setStatus(Response.getOkResponse(ResponseConstant.OK));
            return dataResponse;
        }
        @RequestMapping(name = "getUsersByStateAndCity",value = "/get/users/state/city", method = RequestMethod.GET)
        public DataResponse<Map<Pair<String,String>, List<User>>> getUsersByStateAndCity()  {
            DataResponse<Map<Pair<String,String>, List<User>>> dataResponse = new DataResponse<>();
            dataResponse.setData(userService.getUsersByStateAndCity());
            dataResponse.setStatus(Response.getOkResponse(ResponseConstant.OK));
            return dataResponse;
        }

        @RequestMapping(name = "getUsersByCountryStateAndCity",value = "/get/users/country/city", method = RequestMethod.GET)
        public DataResponse<Map<Triple<String,String,String>, List<User>>> getUsersByCountryStateAndCity()  {
            DataResponse<Map<Triple<String,String,String>, List<User>>> dataResponse = new DataResponse<>();
            dataResponse.setData(userService.getUsersByCountryStateAndCity());
            dataResponse.setStatus(Response.getOkResponse(ResponseConstant.OK));
            return dataResponse;
        }


        @RequestMapping(name = "getAllCity",value = "/get/all/city", method = RequestMethod.GET)
        public DataResponse<Set<String>> getAllCity()  {
            DataResponse<Set<String>> dataResponse = new DataResponse<>();
            dataResponse.setData(userService.getAllCity());
            dataResponse.setStatus(Response.getOkResponse(ResponseConstant.OK));
            return dataResponse;
        }

        @RequestMapping(name = "sortByBirthAndFirstName",value = "/sort/birth/firstName", method = RequestMethod.GET)
        public DataResponse<List<User>> sortByBirthAndFirstName()  {
            DataResponse<List<User>> dataResponse = new DataResponse<>();
            dataResponse.setData(userService.sortByBirthAndFirstName());
            dataResponse.setStatus(Response.getOkResponse(ResponseConstant.OK));
            return dataResponse;
        }

 @RequestMapping(name = "getUserByEmail",value = "/get/user/email", method = RequestMethod.GET)
    public DataResponse<Map<String, User>>  getUserByEmail()  {
        DataResponse<Map<String, User>>  dataResponse = new DataResponse<>();
      dataResponse.setData(userService.getUserByEmail());
        dataResponse.setStatus(Response.getOkResponse(ResponseConstant.OK));
        return dataResponse;
    }


        @RequestMapping(name = "getUserByAge",value = "/get/user/age", method = RequestMethod.GET)
        public DataResponse<List<User>>  getUserByAge()  {
            DataResponse<List< User>>  dataResponse = new DataResponse<>();
            dataResponse.setData(userService.getUserByAge());
            dataResponse.setStatus(Response.getOkResponse(ResponseConstant.OK));
            return dataResponse;
        }


    }


