package g61689.atl.model;

public abstract class Validator {
    private String description;

    public Validator(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void removeAddition() {
        this.description = description.substring(0, description.length() - 9);
    }

    public abstract boolean validate(int code);

    public abstract String categorizeCode(int secretCode);

    int getDigit(int code, int digitNumber) {
        int[] codeArray = new int[3];
        codeArray[0] = code / 100;
        codeArray[1] = (code / 10) % 10;
        codeArray[2] = code % 10;
        return codeArray[digitNumber];
    }

    int[] convertToArray(int code) {
        int[] codeArray = new int[3];
        codeArray[0] = code / 100;
        codeArray[1] = (code / 10) % 10;
        codeArray[2] = code % 10;
        return codeArray;
    }
}
