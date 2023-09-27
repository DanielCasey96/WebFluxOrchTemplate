package restassuredtemplate.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class SingleDataResponse {

    @NotNull
    private final String basicValue;

    @JsonCreator
    public SingleDataResponse(@JsonProperty("basicValue") @NotNull String basicValue) {

        this.basicValue = basicValue;
    }

    public String getSingleData() {

        return this.basicValue;
    }
}
