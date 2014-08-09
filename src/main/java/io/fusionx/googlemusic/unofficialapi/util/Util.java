package io.fusionx.googlemusic.unofficialapi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import java8.util.stream.Collectors;
import retrofit.client.Response;

public class Util {

    public static boolean isEmpty(final CharSequence sequence) {
        return sequence == null || sequence.length() == 0;
    }

    public static boolean isEmpty(final String string) {
        return string == null || string.isEmpty();
    }

    public static List<String> parseRetrofitResponse(final Response response) {
        try {
            final InputStream inputStream = response.getBody().in();
            if (inputStream == null) {
                throw new IOException();
            }
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return StreamUtil.lines(reader).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}