package io.fusionx.googlemusic.callback;

import java.util.List;

@FunctionalInterface
public interface InternalCallback {

    public void call(final List<String> responseLines);
}