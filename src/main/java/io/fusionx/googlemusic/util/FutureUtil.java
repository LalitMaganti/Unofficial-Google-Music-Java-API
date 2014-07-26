package io.fusionx.googlemusic.util;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java8.util.function.Consumer;

public class FutureUtil {

    public static <T> void addCallback(final ListenableFuture<T> future,
            final Consumer<T> success, final Consumer<Throwable> error) {
        Futures.addCallback(future, new FutureCallback<T>() {
            @Override
            public void onSuccess(final T result) {
                if (success != null) {
                    success.accept(result);
                }
            }

            @Override
            public void onFailure(final Throwable t) {
                if (error != null) {
                    error.accept(t);
                }
            }
        });
    }
}