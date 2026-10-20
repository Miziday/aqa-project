package utils;

import api.schemes.client.ClientRequestSchema;

import static utils.FakeData.*;

public class ClientsData {

    public static ClientRequestSchema getRandomClintRequestBody() {

        ClientRequestSchema schema = new ClientRequestSchema();
        schema.setId(getRandomUUID());
        schema.setFullName(getRandomFullName());
        schema.setPhone(getRandomPhoneNumber());
        schema.setEmail(getRandomEmail());

        return schema;
    }
}
