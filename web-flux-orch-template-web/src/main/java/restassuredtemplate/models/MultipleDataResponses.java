package restassuredtemplate.models;

import java.util.List;

public class MultipleDataResponses {

    private List multiValue;

    public MultipleDataResponses() {

    }

    public MultipleDataResponses(List multiValue) {
        this.multiValue = multiValue;
    }

    public List MultipleDataResponses() {
        return this.multiValue;
    }
}
