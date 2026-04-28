package utils;

import api.models.user.UserRequest;

import static utils.RandomData.*;

public class UserData {

    public static UserRequest getRandomUserBodyRequestWithId(String id) {
        return UserRequest.builder().id(id).name(getRandomString(10)).email(getRandEmail()).build();
    }

}
