package io.fusionx.googlemusic.unofficialapi.request;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

public class GuavaRequest<T> {

    private final Supplier<T> mSupplier;

    private final ListeningExecutorService mService;

    public GuavaRequest(final Supplier<T> supplier, final ListeningExecutorService service) {
        mSupplier = supplier;
        mService = service;
    }

    public T sync() {
        return mSupplier.get();
    }

    public ListenableFuture<T> async() {
        return mService.submit(mSupplier::get);
    }
}