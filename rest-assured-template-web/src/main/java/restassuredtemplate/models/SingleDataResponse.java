package restassuredtemplate.models;

public class SingleDataResponse {

    private String basicValue;

    public SingleDataResponse() {

    }

    public SingleDataResponse(String basicValue) {

        this.basicValue = basicValue;
    }

    public String getSingleData() {

        return this.basicValue;
    }
}
