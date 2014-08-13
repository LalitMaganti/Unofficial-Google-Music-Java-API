package io.fusionx.googlemusic.unofficialapi.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseContainer<T> {

    @JsonProperty("kind")
    private String mKind;

    @JsonProperty("data")
    private InnerData<T> mData;

    public String getKind() {
        return mKind;
    }

    public List<T> getItems() {
        return mData.getItems();
    }

    private static class InnerData<T> {

        @JsonProperty("items")
        private List<T> mItems;

        public List<T> getItems() {
            return mItems;
        }
    }
}