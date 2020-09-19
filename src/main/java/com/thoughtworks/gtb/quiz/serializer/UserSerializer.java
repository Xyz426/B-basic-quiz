package com.thoughtworks.gtb.quiz.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.thoughtworks.gtb.quiz.domain.User;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    protected UserSerializer() {
        super(User.class);
    }

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(user.getId());
    }
}
